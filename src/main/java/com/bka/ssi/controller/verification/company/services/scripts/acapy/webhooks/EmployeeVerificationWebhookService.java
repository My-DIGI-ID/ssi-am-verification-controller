package com.bka.ssi.controller.verification.company.services.scripts.acapy.webhooks;

import com.bka.ssi.controller.verification.company.services.scripts.acapy.EmployeeVerificationService;
import com.bka.ssi.controller.verification.company.services.scripts.acapy.dto.input.ACAPYPresentProofDto;
import org.slf4j.Logger;
import org.springframework.stereotype.Service;

@Service
public class EmployeeVerificationWebhookService implements WebhookService {

    private final Logger logger;
    private final EmployeeVerificationService employeeVerificationService;

    public EmployeeVerificationWebhookService(Logger logger,
        EmployeeVerificationService employeeVerificationService) {
        this.logger = logger;
        this.employeeVerificationService = employeeVerificationService;
    }

    @Override
    public void handlePresentProof(
        ACAPYPresentProofDto inputDto) throws Exception {

        switch (inputDto.getState()) {
            case "proposal_sent":
            case "proposal_received":
            case "request_sent":
            case "request_received":
            case "presentation_sent":
            case "presentation_acked":
                logger.debug("Ignoring PresentProof state: " + inputDto.getState());
                break;
            case "presentation_received":
                logger.debug("PresentProof state: " + inputDto.getState());
                this.employeeVerificationService.handlePresentationAcknowledged(inputDto);
                break;
            case "verified":
                logger.debug("PresentProof state: " + inputDto.getState());
                this.employeeVerificationService.handleProofVerified(inputDto);
                break;
            default:
                logger.debug("Unknown PresentProof state: " + inputDto.getState());
                break;
        }
    }
}
