package com.bka.ssi.controller.verification.company.services.scripts.acapy;

import com.bka.ssi.controller.verification.acapy_client.api.PresentProofV10Api;
import com.bka.ssi.controller.verification.acapy_client.model.V10PresentationCreateRequestRequest;
import com.bka.ssi.controller.verification.acapy_client.model.V10PresentationExchange;
import com.bka.ssi.controller.verification.acapy_client.model.V10PresentationExchange.VerifiedEnum;
import com.bka.ssi.controller.verification.company.aop.configuration.agents.ACAPYConfiguration;
import com.bka.ssi.controller.verification.company.aop.configuration.agents.CredentialsConfiguration;
import com.bka.ssi.controller.verification.company.services.VerificationService;
import com.bka.ssi.controller.verification.company.services.enums.GuestVerificationStatus;
import com.bka.ssi.controller.verification.company.services.exceptions.NotFoundException;
import com.bka.ssi.controller.verification.company.services.models.GuestVerification;
import com.bka.ssi.controller.verification.company.services.models.common.BasisId;
import com.bka.ssi.controller.verification.company.services.models.common.ConnectionlessProofRequest;
import com.bka.ssi.controller.verification.company.services.models.credentials.GuestCredential;
import com.bka.ssi.controller.verification.company.services.repositories.GuestVerificationRepository;
import com.bka.ssi.controller.verification.company.services.scripts.acapy.dto.input.ACAPYPresentProofDto;
import com.bka.ssi.controller.verification.company.services.scripts.acapy.utilities.ACAPYConnectionlessProofUtility;
import com.bka.ssi.controller.verification.company.services.scripts.acapy.utilities.ACAPYUtilities;
import com.bka.ssi.controller.verification.company.services.system.accreditation.AccreditationClient;
import com.bka.ssi.controller.verification.company.services.system.accreditation.dto.output.GuestAccreditationPrivateOutputDto;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.time.ZonedDateTime;
import java.util.Base64;
import java.util.List;

@Service
public class GuestVerificationService
    implements VerificationService<GuestVerification> {

    /*
        Technical Debt: apply same pattern as in accreditation controller and implment
        ACAPYClientV6 in infra layer and use here or in utilities
     */

    @Value("${guest.checkoutDelay}")
    private int checkoutDelay;

    private final ACAPYConnectionlessProofUtility acapyConnectionlessProofUtility;
    private final GuestVerificationRepository repository;
    private final Logger logger;
    private final ACAPYConfiguration acapyConfiguration;
    private final AccreditationClient accreditationClient;
    private final CredentialsConfiguration credentialsConfiguration;
    private final ACAPYUtilities acapyUtilities;
    private final PresentProofV10Api presentProofApi;

    public GuestVerificationService(
        ACAPYConnectionlessProofUtility acapyConnectionlessProofUtility,
        Logger logger,
        ACAPYConfiguration acapyConfiguration,
        AccreditationClient accreditationClient,
        @Qualifier("guestVerificationMongoDbFacade")
            GuestVerificationRepository verificationRepository,
        CredentialsConfiguration credentialsConfiguration,
        ACAPYUtilities acapyUtilities) {
        this.acapyConnectionlessProofUtility = acapyConnectionlessProofUtility;
        this.repository = verificationRepository;
        this.logger = logger;
        this.acapyConfiguration = acapyConfiguration;
        this.accreditationClient = accreditationClient;
        this.credentialsConfiguration = credentialsConfiguration;
        this.acapyUtilities = acapyUtilities;

        this.presentProofApi = new PresentProofV10Api(this.acapyConfiguration.getApiClient());
    }

    @Override
    public URI handleProofRequest(String locationId, String terminalId) throws Exception {
        logger.info("start: handleProofRequest");

        V10PresentationCreateRequestRequest presentationCreateRequestRequest =
            acapyUtilities.createPresentationRequest(
                "Test",
                false,
                "Basis-Id proof request",
                this.credentialsConfiguration.getGuestProofCredSpec());

        logger.info(presentationCreateRequestRequest.toString());

        V10PresentationExchange presentationExchange =
            this.acapyConnectionlessProofUtility.getProofRequest(presentationCreateRequestRequest);

        logger.info(presentationExchange.toString());
        // Store initial entries for thread_id in DB entry
        GuestVerification verification = new GuestVerification(presentationExchange.getThreadId(),
            locationId, terminalId);
        GuestVerification savedVerification = repository.save(verification);
        logger.info(savedVerification.getThreadId());

        // Build URI from presentation exchange
        ConnectionlessProofRequest connectionlessProofRequest =
            acapyUtilities.createConnectionlessProofRequest(presentationExchange);

        ObjectMapper mapper = new ObjectMapper();
        logger.debug(mapper.writeValueAsString(connectionlessProofRequest));
        // Base64 encode the connectionless proof request
        String encodedUrl = Base64.getEncoder()
            .encodeToString((mapper.writeValueAsString(connectionlessProofRequest)).getBytes());

        logger.info("end: handleProofRequest");
        // return a URI that is consumable by the wallet app
        return URI.create(this.acapyConfiguration.getDidcommUrl() + encodedUrl);
    }

    @Override
    public void handlePresentationAcknowledged(ACAPYPresentProofDto inputDto)
        throws NotFoundException {
        String threadId = inputDto.getThreadId();

        GuestVerification verification = repository.findByThreadId(threadId).orElseThrow(
            NotFoundException::new);

        verification.setProofState(inputDto.getState());

        repository.save(verification);
    }

    @Override
    public void handleProofVerified(ACAPYPresentProofDto inputDto) throws Exception {

        String presentationExchangeId = inputDto.getPresentationExchangeId();

        GuestVerification verification = repository.findByThreadId(inputDto.getThreadId())
            .orElseThrow(NotFoundException::new);

        V10PresentationExchange presentationExchange =
            presentProofApi.presentProofRecordsPresExIdGet(presentationExchangeId);

        BasisId basisId = acapyUtilities.getBasisId(presentationExchange);
        verification.setBasisId(basisId);

        GuestCredential guestCredential = acapyUtilities.getGuestCredential(presentationExchange);
        verification.setGuestCredential(guestCredential);

        verification.setProofState(inputDto.getState());

        boolean basisIdVerified = presentationExchange.getVerified() != null
            && VerifiedEnum.TRUE == presentationExchange.getVerified();

        if (acapyConfiguration.getBasisIdVerificationDevMode()) {
            logger.warn("Warning! Fallback to basisId verification dev mode");
            basisIdVerified = true;
        } else {
            logger.debug("Proceeding with basisId verification in production mode");
        }

        if (basisIdVerified) {

            if (!validateTimeframe(guestCredential)) {
                verification.setState(GuestVerificationStatus.FAIL_DATE_TIME);
                repository.save(verification);
                return;
            }

            GuestAccreditationPrivateOutputDto guestAccreditation =
                accreditationClient.getUniqueAccreditation(guestCredential.getReferenceBasisId(),
                    guestCredential.getFirstName(), guestCredential.getLastName(),
                    guestCredential.getDateOfBirth(), guestCredential.getCompanyName(),
                    guestCredential.getValidFrom(), guestCredential.getValidUntil(),
                    guestCredential.getInvitedBy());

            if (guestAccreditation == null) {
                throw new NotFoundException();
            }

            verification.setAccreditationId(guestAccreditation.getId());

            GuestVerification previousVerification =
                repository.findByAccreditationId(guestAccreditation.getId())
                    .orElse(null);

            // Check-in
            if (previousVerification == null || previousVerification.getCheckInDateTime() == null) {

                verification.setCheckInDateTime(ZonedDateTime.now());

                verification.setState(GuestVerificationStatus.CHECK_IN);

                repository.save(verification);

                presentProofApi.presentProofRecordsPresExIdDelete(presentationExchangeId);

            } else if (isCheckoutDelayOver(
                previousVerification.getCheckInDateTime())) { // Check-out

                verification.setCheckInDateTime(previousVerification.getCheckInDateTime());
                verification.setCheckOutDateTime(ZonedDateTime.now());

                verification.setState(GuestVerificationStatus.CHECK_OUT);

                repository.save(verification);

                accreditationClient.revokeAccreditation(verification.getAccreditationId());

                accreditationClient.cleanupAccreditation(verification.getAccreditationId());

                repository.deleteById(previousVerification.getId());

                presentProofApi.presentProofRecordsPresExIdDelete(presentationExchangeId);
            }

        } else {
            logger.info("Guest " + guestCredential.getFirstName() + " "
                + guestCredential.getLastName() + " failed verification.");

            verification.setState(GuestVerificationStatus.FAIL_VERIFY_CREDENTIAL);

            repository.save(verification);
        }
    }

    @Override
    public List<GuestVerification> handleVerificationProcessComplete(String locationId,
        String terminalId) {
        return this.repository.findAllByLocationIdAndTerminalId(locationId, terminalId);
    }

    private boolean validateTimeframe(GuestCredential guestCredential) {
        ZonedDateTime currentDate = ZonedDateTime.now();

        ZonedDateTime validFrom = guestCredential.getValidFrom();
        ZonedDateTime validUntil = guestCredential.getValidUntil();

        return currentDate.isAfter(validFrom)
            && currentDate.isBefore(validUntil);
    }

    private boolean isCheckoutDelayOver(ZonedDateTime checkInDateTime) {

        ZonedDateTime delayEndDate = checkInDateTime.plusSeconds(this.checkoutDelay);

        ZonedDateTime currentDate = ZonedDateTime.now();

        return currentDate.isAfter(delayEndDate);
    }
}