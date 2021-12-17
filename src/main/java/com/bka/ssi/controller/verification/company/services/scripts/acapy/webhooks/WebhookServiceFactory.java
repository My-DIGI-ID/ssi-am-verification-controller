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

package com.bka.ssi.controller.verification.company.services.scripts.acapy.webhooks;

import com.bka.ssi.controller.verification.company.services.exceptions.NotFoundException;
import com.bka.ssi.controller.verification.company.services.models.EmployeeVerification;
import com.bka.ssi.controller.verification.company.services.models.GuestVerification;
import com.bka.ssi.controller.verification.company.services.repositories.EmployeeVerificationRepository;
import com.bka.ssi.controller.verification.company.services.repositories.GuestVerificationRepository;
import com.bka.ssi.controller.verification.company.services.scripts.acapy.dto.input.ACAPYPresentProofDto;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.Optional;

/**
 * The type Webhook service factory.
 */
@Component
public class WebhookServiceFactory {

    private final Logger logger;

    private final GuestVerificationWebhookService guestVerificationWebhookService;
    private final EmployeeVerificationWebhookService employeeVerificationWebhookService;

    private final GuestVerificationRepository guestVerificationRepository;
    private final EmployeeVerificationRepository employeeVerificationRepository;

    /**
     * Instantiates a new Webhook service factory.
     *
     * @param logger                             the logger
     * @param guestVerificationWebhookService    the guest verification webhook service
     * @param employeeVerificationWebhookService the employee verification webhook service
     * @param guestVerificationRepository        the guest verification repository
     * @param employeeVerificationRepository     the employee verification repository
     */
    public WebhookServiceFactory(Logger logger,
        GuestVerificationWebhookService guestVerificationWebhookService,
        EmployeeVerificationWebhookService employeeVerificationWebhookService,
        @Qualifier("guestVerificationMongoDbFacade")
            GuestVerificationRepository guestVerificationRepository,
        @Qualifier("employeeVerificationMongoDbFacade")
            EmployeeVerificationRepository employeeVerificationRepository) {
        this.logger = logger;
        this.guestVerificationWebhookService = guestVerificationWebhookService;
        this.employeeVerificationWebhookService = employeeVerificationWebhookService;
        this.guestVerificationRepository = guestVerificationRepository;
        this.employeeVerificationRepository = employeeVerificationRepository;
    }

    private WebhookService getWebhookServiceByThreadId(String threadId) throws NotFoundException {

        Optional<GuestVerification> guestVerification =
            this.guestVerificationRepository.findByThreadId(threadId);

        if (guestVerification.isPresent()) {
            return this.guestVerificationWebhookService;
        }

        Optional<EmployeeVerification> employeeVerification =
            this.employeeVerificationRepository.findByThreadId(threadId);

        if (employeeVerification.isPresent()) {
            return this.employeeVerificationWebhookService;
        }

        logger.error("Verification with threadId {} not found on either Guest or Employee "
            + "repositories", threadId);
        throw new NotFoundException();
    }

    /**
     * Handle on present proof.
     *
     * @param inputDto the input dto
     * @throws Exception the exception
     */
    public void handleOnPresentProof(ACAPYPresentProofDto inputDto) throws Exception {
        WebhookService webhookService = getWebhookServiceByThreadId(inputDto.getThreadId());

        webhookService.handlePresentProof(inputDto);
    }
}
