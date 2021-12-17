/*
 * Copyright 2021 Bundesrepublik Deutschland
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.bka.ssi.controller.verification.company.services.scripts.acapy;

import com.bka.ssi.controller.verification.company.aop.configuration.agents.ACAPYConfiguration;
import com.bka.ssi.controller.verification.company.aop.configuration.agents.CredentialsConfiguration;
import com.bka.ssi.controller.verification.company.services.VerificationService;
import com.bka.ssi.controller.verification.company.services.enums.EmployeeVerificationStatus;
import com.bka.ssi.controller.verification.company.services.exceptions.NotFoundException;
import com.bka.ssi.controller.verification.company.services.models.EmployeeVerification;
import com.bka.ssi.controller.verification.company.services.models.common.ConnectionlessProofRequest;
import com.bka.ssi.controller.verification.company.services.models.credentials.EmployeeCredential;
import com.bka.ssi.controller.verification.company.services.repositories.EmployeeVerificationRepository;
import com.bka.ssi.controller.verification.company.services.scripts.acapy.dto.input.ACAPYPresentProofDto;
import com.bka.ssi.controller.verification.company.services.scripts.acapy.utilities.ACAPYConnectionlessProofUtility;
import com.bka.ssi.controller.verification.company.services.scripts.acapy.utilities.ACAPYUtilities;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.github.my_digi_id.acapy_client.api.PresentProofV10Api;
import io.github.my_digi_id.acapy_client.model.V10PresentationCreateRequestRequest;
import io.github.my_digi_id.acapy_client.model.V10PresentationExchange;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.util.Base64;
import java.util.List;

/**
 * The type Employee verification service.
 */
@Service
public class EmployeeVerificationService
    implements
    VerificationService<EmployeeVerification> {

    private final ACAPYConnectionlessProofUtility acapyConnectionlessProofUtility;
    private final EmployeeVerificationRepository repository;
    private final Logger logger;
    private final ACAPYConfiguration acapyConfiguration;
    private final CredentialsConfiguration credentialsConfiguration;
    private final ACAPYUtilities acapyUtilities;
    private PresentProofV10Api presentProofApi;

    /**
     * Instantiates a new Employee verification service.
     *
     * @param acapyConnectionlessProofUtility the acapy connectionless proof utility
     * @param repository                      the repository
     * @param logger                          the logger
     * @param acapyConfiguration              the acapy configuration
     * @param credentialsConfiguration        the credentials configuration
     * @param acapyUtilities                  the acapy utilities
     */
    public EmployeeVerificationService(
        ACAPYConnectionlessProofUtility acapyConnectionlessProofUtility,
        @Qualifier("employeeVerificationMongoDbFacade") EmployeeVerificationRepository repository,
        Logger logger,
        ACAPYConfiguration acapyConfiguration,
        CredentialsConfiguration credentialsConfiguration,
        ACAPYUtilities acapyUtilities) {

        this.acapyConnectionlessProofUtility = acapyConnectionlessProofUtility;
        this.repository = repository;
        this.logger = logger;
        this.acapyConfiguration = acapyConfiguration;
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
                "Employee credential proof request",
                this.credentialsConfiguration.getEmployeeProofCredSpec());

        logger.info(presentationCreateRequestRequest.toString());

        V10PresentationExchange presentationExchange =
            this.acapyConnectionlessProofUtility.getProofRequest(presentationCreateRequestRequest);

        logger.info(presentationExchange.toString());
        // Store initial entries for thread_id in DB entry
        EmployeeVerification verification =
            new EmployeeVerification(presentationExchange.getThreadId(), locationId, terminalId);
        EmployeeVerification savedVerification = repository.save(verification);
        logger.info(savedVerification.getThreadId());

        // Build URI from presentation exchange
        ConnectionlessProofRequest connectionlessProofRequest =
            this.acapyUtilities.createConnectionlessProofRequest(presentationExchange);

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
    public void handlePresentationAcknowledged(
        ACAPYPresentProofDto inputDto) throws Exception {
        String threadId = inputDto.getThreadId();

        EmployeeVerification verification = repository.findByThreadId(threadId).orElseThrow(
            NotFoundException::new);

        verification.setProofState(inputDto.getState());

        repository.save(verification);
    }

    @Override
    public void handleProofVerified(
        ACAPYPresentProofDto inputDto) throws Exception {

        String presentationExchangeId = inputDto.getPresentationExchangeId();

        EmployeeVerification verification =
            repository.findByThreadId(inputDto.getThreadId()).orElseThrow(NotFoundException::new);

        V10PresentationExchange presentationExchange =
            presentProofApi.presentProofRecordsPresExIdGet(presentationExchangeId);

        EmployeeCredential employeeCredential =
            acapyUtilities.getEmployeeCredential(presentationExchange);
        verification.setEmployeeCredential(employeeCredential);

        verification.setProofState(inputDto.getState());

        boolean verified = presentationExchange.getVerified() != null
            && V10PresentationExchange.VerifiedEnum.TRUE == presentationExchange.getVerified();

        // TODO: checkIn currently without checkout; eliminate usage of getter and setter in
        //  service and infra layer
        verification.checkIn();
        if (verified) {
            verification.setState(EmployeeVerificationStatus.VERIFIED);
        } else {
            verification.setState(EmployeeVerificationStatus.FAIL_VERIFY_CREDENTIAL);
        }

        repository.save(verification);

        presentProofApi.presentProofRecordsPresExIdDelete(presentationExchangeId);
    }

    @Override
    public List<EmployeeVerification> handleVerificationProcessComplete(
        String locationId, String terminalId) {
        return this.repository.findAllByLocationIdAndTerminalId(locationId, terminalId);
    }
}
