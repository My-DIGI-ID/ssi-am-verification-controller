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

@Component
public class WebhookServiceFactory {

    private final Logger logger;

    private final GuestVerificationWebhookService guestVerificationWebhookService;
    private final EmployeeVerificationWebhookService employeeVerificationWebhookService;

    private final GuestVerificationRepository guestVerificationRepository;
    private final EmployeeVerificationRepository employeeVerificationRepository;

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

    public void handleOnPresentProof(ACAPYPresentProofDto inputDto) throws Exception {
        WebhookService webhookService = getWebhookServiceByThreadId(inputDto.getThreadId());

        webhookService.handlePresentProof(inputDto);
    }
}
