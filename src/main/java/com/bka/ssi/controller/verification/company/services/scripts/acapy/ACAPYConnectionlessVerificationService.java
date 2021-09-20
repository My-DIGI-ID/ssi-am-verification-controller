package com.bka.ssi.controller.verification.company.services.scripts.acapy;

import com.bka.ssi.controller.verification.acapy_client.api.PresentProofV10Api;
import com.bka.ssi.controller.verification.acapy_client.model.IndyProofReqAttrSpec;
import com.bka.ssi.controller.verification.acapy_client.model.IndyProofRequestedProofRevealedAttrGroup;
import com.bka.ssi.controller.verification.acapy_client.model.RawEncoded;
import com.bka.ssi.controller.verification.acapy_client.model.V10PresentationCreateRequestRequest;
import com.bka.ssi.controller.verification.acapy_client.model.V10PresentationExchange;
import com.bka.ssi.controller.verification.acapy_client.model.V10PresentationExchange.VerifiedEnum;
import com.bka.ssi.controller.verification.company.aop.configuration.agents.ACAPYConfiguration;
import com.bka.ssi.controller.verification.company.aop.configuration.agents.CredentialsConfiguration;
import com.bka.ssi.controller.verification.company.services.VerificationService;
import com.bka.ssi.controller.verification.company.services.enums.VerificationState;
import com.bka.ssi.controller.verification.company.services.exceptions.ScriptException;
import com.bka.ssi.controller.verification.company.services.models.Verification;
import com.bka.ssi.controller.verification.company.services.models.common.Base64Payload;
import com.bka.ssi.controller.verification.company.services.models.common.BasisId;
import com.bka.ssi.controller.verification.company.services.models.common.ConnectionlessProofRequest;
import com.bka.ssi.controller.verification.company.services.models.common.EmptyDTO;
import com.bka.ssi.controller.verification.company.services.models.common.GuestCredential;
import com.bka.ssi.controller.verification.company.services.models.common.ProofRequestService;
import com.bka.ssi.controller.verification.company.services.models.common.ProofRequestThread;
import com.bka.ssi.controller.verification.company.services.models.common.RequestPresentationAttach;
import com.bka.ssi.controller.verification.company.services.repositories.VerificationRepository;
import com.bka.ssi.controller.verification.company.services.scripts.acapy.builders.ACAPYConnectionlessProofRequestV10;
import com.bka.ssi.controller.verification.company.services.scripts.acapy.dto.input.ACAPYPresentProofDto;
import com.bka.ssi.controller.verification.company.services.scripts.acapy.exceptions.ACAPYCustomException;
import com.bka.ssi.controller.verification.company.services.scripts.acapy.utilities.ACAPYConnectionlessProofUtility;
import com.bka.ssi.controller.verification.company.services.system.accreditation.AccreditationClient;
import com.bka.ssi.controller.verification.company.services.system.accreditation.dto.output.GuestAccreditationPrivateOutputDto;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;

import java.net.URI;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.security.SecureRandom;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import javax.ws.rs.NotFoundException;

@Service
public class ACAPYConnectionlessVerificationService
    implements VerificationService<URI, ACAPYPresentProofDto, ACAPYPresentProofDto> {

    private final ACAPYConnectionlessProofUtility acapyConnectionlessProofUtility;
    private final VerificationRepository verificationRepository;
    private final Logger logger;
    private final SecureRandom secureRandom;
    private final CredentialsConfiguration credentialsConfiguration;
    private final ACAPYConfiguration acapyConfiguration;
    private final AccreditationClient accreditationClient;

    @Autowired
    private PresentProofV10Api presentProofApi;
    
    public ACAPYConnectionlessVerificationService(
        ACAPYConnectionlessProofUtility acapyConnectionlessProofUtility,
        Logger logger,
        CredentialsConfiguration credentialsConfiguration,
        ACAPYConfiguration acapyConfiguration,
        AccreditationClient accreditationClient,
        @Qualifier("verificationMongoDBFacade") VerificationRepository verificationRepository) {
        this.acapyConnectionlessProofUtility = acapyConnectionlessProofUtility;
        this.verificationRepository = verificationRepository;
        this.logger = logger;
        this.secureRandom = new SecureRandom();
        this.credentialsConfiguration = credentialsConfiguration;
        this.acapyConfiguration = acapyConfiguration;
        this.accreditationClient = accreditationClient;
    }

    @Override
    public URI handleProofRequest(String locationId, String terminalId) throws Exception {
        logger.info("start: handleProofRequest");

        // Create nonce
        String nonce = String.valueOf(secureRandom.nextInt());

        // Set requested attributes Basis ID
        Map<String, String> basisIdRestrictionItem = new HashMap<>();
        basisIdRestrictionItem
            .put("cred_def_id", this.credentialsConfiguration
                .getBasisIdCredentialDefinitionId());
        IndyProofReqAttrSpec indyBasisIdProofReqAttrSpec = new IndyProofReqAttrSpec();
        indyBasisIdProofReqAttrSpec.addNamesItem("firstName");
        indyBasisIdProofReqAttrSpec.addNamesItem("familyName");
        indyBasisIdProofReqAttrSpec.addNamesItem("dateOfBirth");
        indyBasisIdProofReqAttrSpec.addNamesItem("dateOfExpiry");
        indyBasisIdProofReqAttrSpec.addRestrictionsItem(basisIdRestrictionItem);

        // Set requested attributes Guest Credential
        Map<String, String> guestCredRestrictionItem = new HashMap<>();
        guestCredRestrictionItem
            .put("cred_def_id", this.credentialsConfiguration
                .getGuestCredentialDefinitionId());
        IndyProofReqAttrSpec indyGuestCredProofReqAttrSpec = new IndyProofReqAttrSpec();

        indyGuestCredProofReqAttrSpec.addNamesItem("firstName");
        indyGuestCredProofReqAttrSpec.addNamesItem("lastName");
        indyGuestCredProofReqAttrSpec.addNamesItem("titel");
        indyGuestCredProofReqAttrSpec.addNamesItem("email");
        indyGuestCredProofReqAttrSpec.addNamesItem("primaryPhoneNumber");
        indyGuestCredProofReqAttrSpec.addNamesItem("secondaryPhoneNumber");
        indyGuestCredProofReqAttrSpec.addNamesItem("companyName");
        indyGuestCredProofReqAttrSpec.addNamesItem("typeOfVisit");
        indyGuestCredProofReqAttrSpec.addNamesItem("location");
        indyGuestCredProofReqAttrSpec.addNamesItem("validFromTime");
        indyGuestCredProofReqAttrSpec.addNamesItem("validFromDate");
        indyGuestCredProofReqAttrSpec.addNamesItem("validUntilDate");
        indyGuestCredProofReqAttrSpec.addNamesItem("validUntilTime");
        indyGuestCredProofReqAttrSpec.addNamesItem("invitedBy");
        indyGuestCredProofReqAttrSpec.addNamesItem("dateOfBirth");
        indyGuestCredProofReqAttrSpec.addNamesItem("licensePlateNumber");
        indyGuestCredProofReqAttrSpec.addNamesItem("companyStreet");
        indyGuestCredProofReqAttrSpec.addNamesItem("companyCity");
        indyGuestCredProofReqAttrSpec.addNamesItem("companyPostCode");
        indyGuestCredProofReqAttrSpec.addNamesItem("referenceBasisId");
        indyGuestCredProofReqAttrSpec.addNamesItem("referenceEmployeeId");
        indyGuestCredProofReqAttrSpec.addNamesItem("companyProofOfOwnership");
        indyGuestCredProofReqAttrSpec.addNamesItem("dataEncryptionAlgorithm");

        indyGuestCredProofReqAttrSpec.addRestrictionsItem(guestCredRestrictionItem);

        V10PresentationCreateRequestRequest presentationCreateRequestRequest = ACAPYConnectionlessProofRequestV10.newBuilder()
            .withComment("Test")
            .withTrace(false)
            .addIndyProofRequest()
            .withName("Basis-Id proof request")
            .withNonce(nonce)
            .withVersion("1.0")
            .addRequestedAttributes()
            .withRequestedAttribute("basisIdAttributeName", indyBasisIdProofReqAttrSpec)
            .withRequestedAttribute("guestCredAttributeName", indyGuestCredProofReqAttrSpec)
            .done()
            .addRequestedPredicates()
            .done()
            .done()
            .build();
        logger.info(presentationCreateRequestRequest.toString());

        V10PresentationExchange presentationExchange =
            this.acapyConnectionlessProofUtility.getProofRequest(presentationCreateRequestRequest);

        logger.info(presentationExchange.toString());
        // Store initial entries for thread_id in DB entry
        Verification verification = new Verification(presentationExchange.getThreadId(),
            locationId, terminalId);
        Verification savedVerification = verificationRepository.save(verification);
        logger.info(savedVerification.getThreadId());

        // Build URI from presentation exchange
        ConnectionlessProofRequest connectionlessProofRequest = new ConnectionlessProofRequest();
        connectionlessProofRequest.setId(presentationExchange.getThreadId());
        connectionlessProofRequest.setType(this.acapyConfiguration.getAriesMessageType());
        RequestPresentationAttach requestPresentationAttach = new RequestPresentationAttach();
        requestPresentationAttach.setId(this.acapyConfiguration.getAriesAttachId());
        requestPresentationAttach.setMimeType(MediaType.APPLICATION_JSON_VALUE);

        Base64Payload base64Payload = new Base64Payload();
        base64Payload.setBase64(presentationExchange.getPresentationRequestDict()
            .getRequestPresentationsTildeAttach().get(0).getData().getBase64());
        requestPresentationAttach.setData(base64Payload);

        RequestPresentationAttach[] requestPresentationAttaches = new RequestPresentationAttach[1];
        requestPresentationAttaches[0] = requestPresentationAttach;
        connectionlessProofRequest.setRequestPresentationAttach(requestPresentationAttaches);

        ProofRequestService proofRequestService = new ProofRequestService();
        String[] keys = new String[1];
        keys[0] = this.acapyConfiguration.getVerKey();
        proofRequestService.setRecipientKeys(keys);
        String[] routingKeys = new String[0];
        proofRequestService.setRoutingKeys(routingKeys);
        proofRequestService.setServiceEndpoint(this.acapyConfiguration.getEndpoint());
        proofRequestService.setEndpointName(this.acapyConfiguration.getName());
        connectionlessProofRequest.setService(proofRequestService);

        ProofRequestThread proofRequestThread = new ProofRequestThread();
        EmptyDTO empty = new EmptyDTO();
        proofRequestThread.setReceivedOrders(empty);
        proofRequestThread.setThreadId(presentationExchange.getThreadId());
        connectionlessProofRequest.setThread(proofRequestThread);

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
    public void handlePresentationAcknowledged(ACAPYPresentProofDto proofReceipt) throws Exception {
    	String threadId = proofReceipt.getThreadId();
    	
    	Verification verification = verificationRepository.findByThreadId(threadId).orElseThrow(() ->
    		new NotFoundException());
    	
    	verification.setProofState(proofReceipt.getState());
    	
    	verificationRepository.save(verification);
    }

    @Override
    public void handleProofVerified(ACAPYPresentProofDto proofVerified) throws Exception {
    	
    	String presentationExchangeId = proofVerified.getPresentationExchangeId();
    	
    	V10PresentationExchange presentationExchange = presentProofApi.presentProofRecordsPresExIdGet(presentationExchangeId);
    	
    	Map<String, Map<String,String>> rawAttrGroupValues = getRevealedAttributes(presentationExchange);
    	
    	Map<String,String> basisIdAttributes = rawAttrGroupValues.get("basisIdAttributeName");
    	Map<String,String> guestCredAttributes = rawAttrGroupValues.get("guestCredAttributeName");
    	
    	Verification verification = verificationRepository.findByThreadId(proofVerified.getThreadId())
	    		.orElseThrow(() -> new NotFoundException());
	    	
    	DateFormat format = new SimpleDateFormat("EEE MMM dd HH:mm:ss Z yyyy");
    	
    	BasisId basisId = new BasisId();
		basisId.setFirstName(basisIdAttributes.get("firstName"));
		basisId.setFamilyName(basisIdAttributes.get("familyName"));
		basisId.setDateOfBirth(basisIdAttributes.get("dateOfBirth"));
		basisId.setDateOfExpiry(basisIdAttributes.get("dateOfExpiry"));
		verification.setBasisId(basisId);
		
		GuestCredential guestCredential = new GuestCredential();
		guestCredential.setFirstName(guestCredAttributes.get("firstName"));
		guestCredential.setLastName(guestCredAttributes.get("lastName"));
		guestCredential.setTitel(guestCredAttributes.get("titel"));
		guestCredential.setEmail(guestCredAttributes.get("email"));
		guestCredential.setPrimaryPhoneNumber(guestCredAttributes.get("primaryPhoneNumber"));
		guestCredential.setSecondaryPhoneNumber(guestCredAttributes.get("secondaryPhoneNumber"));
		guestCredential.setCompanyName(guestCredAttributes.get("companyName"));
		guestCredential.setTypeOfVisit(guestCredAttributes.get("typeOfVisit"));
		guestCredential.setLocation(guestCredAttributes.get("location"));
		
		guestCredential.setValidFromDate(format.parse(guestCredAttributes.get("validFromDate")));
		guestCredential.setValidFromTime(format.parse(guestCredAttributes.get("validFromTime")));
		guestCredential.setValidUntilDate(format.parse(guestCredAttributes.get("validUntilDate")));
		guestCredential.setValidUntilTime(format.parse(guestCredAttributes.get("validUntilTime")));
		
		guestCredential.setInvitedBy(guestCredAttributes.get("invitedBy"));
		guestCredential.setDateOfBirth(guestCredAttributes.get("dateOfBirth"));
		guestCredential.setLicensePlateNumber(guestCredAttributes.get("licensePlateNumber"));
		guestCredential.setCompanyCity(guestCredAttributes.get("companyCity"));
		guestCredential.setCompanyStreet(guestCredAttributes.get("companyStreet"));
		guestCredential.setCompanyPostCode(guestCredAttributes.get("companyPostCode"));
		guestCredential.setReferenceBasisId(guestCredAttributes.get("referenceBasisId"));
		guestCredential.setReferenceEmployeeId(guestCredAttributes.get("referenceEmployeeId"));
		guestCredential.setCompanyProofOfOwnership(guestCredAttributes.get("companyProofOfOwnership"));
		guestCredential.setDataEncryptionAlgorithm(guestCredAttributes.get("dataEncryptionAlgorithm"));
		
		verification.setGuestCredential(guestCredential);
		
		verification.setProofState(proofVerified.getState());
		
		boolean basisIdVerified = presentationExchange.getVerified() != null &&
			VerifiedEnum.TRUE == presentationExchange.getVerified();
    	
		if (acapyConfiguration.getBasisIdVerificationDevMode()) {
            logger.warn("Warning! Fallback to basisId verification dev mode");
            basisIdVerified = true;
        } else {
            logger.debug("Proceeding with basisId verification in production mode");
        }
		
    	if(basisIdVerified) {
    		
    		if(!validateTimeframe(guestCredential)) {
	    		verification.setState(VerificationState.FAIL_DATE_TIME);
	    		verificationRepository.save(verification);
	    		return;
	    	}
	    	
	    	GuestAccreditationPrivateOutputDto guestAccreditation = 
	    		accreditationClient.getUniqueAccreditation(guestCredAttributes.get("referenceBasisId"), 
	    		guestCredAttributes.get("firstName"), guestCredAttributes.get("lastName"),
	    		guestCredAttributes.get("dateOfBirth"), guestCredAttributes.get("companyName"),
	    		format.parse(guestCredAttributes.get("validFromDate")),
	    		format.parse(guestCredAttributes.get("validUntilDate")), 
	    		guestCredAttributes.get("invitedBy"));

	    	if(guestAccreditation == null) {
	    		throw new NotFoundException();
	    	}
	    	
	    	verification.setAccreditationId(guestAccreditation.getId());
	    	
	    	Verification previousVerification = verificationRepository.findByAccreditationId(guestAccreditation.getId())
	    		.orElse(null);
	    	
	    	// Check-in
	    	if(previousVerification == null || previousVerification.getCheckInDateTime() == null) {
	    		
	    		verification.setCheckInDateTime(new Date());
	    		
	    		verification.setState(VerificationState.CHECK_IN);
	    		
	    		verificationRepository.save(verification);
	    		
	    		presentProofApi.presentProofRecordsPresExIdDelete(presentationExchangeId);
	    		
	    	} else { // Check-out
	    		
	    		verification.setCheckInDateTime(previousVerification.getCheckInDateTime());
	    		verification.setCheckOutDateTime(new Date());
	    		
	    		verification.setState(VerificationState.CHECK_OUT);

	    		verificationRepository.save(verification);
	    		
	    		accreditationClient.cleanupAccreditation(verification.getAccreditationId());
	    		
	    		verificationRepository.deleteById(previousVerification.getId());
	    		
	    		presentProofApi.presentProofRecordsPresExIdDelete(presentationExchangeId);
	    	}
	    	
    	} else {
    		logger.info("Guest " + guestCredAttributes.get("firstName") + " " + guestCredAttributes.get("lastName") + " failed verification.");

    		verification.setState(VerificationState.FAIL_VERIFY_CREDENTIAL);
    		
    		verificationRepository.save(verification);
    	}
    }

    @Override
    public List<Object> handleVerificationProcessComplete(String locationId,
        String terminalId) throws Exception {
    	
    	Iterable<Verification> result = this.verificationRepository.findAllByLocationIdAndTerminalId(locationId, terminalId);
    	
        return StreamSupport.stream(result.spliterator(), false)
        		.collect(Collectors.toList());
    }
    
    private Map<String, Map<String, String>> getRevealedAttributes(V10PresentationExchange presentationExchange) {
    	
    	Map<String, IndyProofRequestedProofRevealedAttrGroup> revealedAttrGroups =
	    		presentationExchange.getPresentation().getRequestedProof().getRevealedAttrGroups();
	    	
    	Map<String, Map<String,String>> rawAttrGroupValues = new HashMap<>();
    	
    	for (Map.Entry<String, IndyProofRequestedProofRevealedAttrGroup> attrGroupEntry : revealedAttrGroups.entrySet()) {
    		Map<String, RawEncoded> attrGroupValues = attrGroupEntry.getValue().getValues();
    		
    		Map<String, String> rawAttrValues = new HashMap<>();
    		
    		for (Map.Entry<String, RawEncoded> entry : attrGroupValues.entrySet()) {
			
    			rawAttrValues.put(entry.getKey(), entry.getValue().getRaw());
			}
    		
    		rawAttrGroupValues.put(attrGroupEntry.getKey(), rawAttrValues);
		}
    	
    	return rawAttrGroupValues;
    }
    
    private boolean validateTimeframe(GuestCredential guestCredential) {
    	Date currentDate = new Date();
    	
    	Date validFromTime = guestCredential.getValidFromTime();
    	Date validUntilTime = guestCredential.getValidUntilTime();
    	
    	return currentDate.after(validFromTime)
    		&& currentDate.before(validUntilTime);
    }
}