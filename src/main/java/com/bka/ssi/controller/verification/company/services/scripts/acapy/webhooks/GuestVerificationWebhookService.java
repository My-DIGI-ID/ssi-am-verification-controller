package com.bka.ssi.controller.verification.company.services.scripts.acapy.webhooks;

import com.bka.ssi.controller.verification.company.services.scripts.acapy.GuestVerificationService;
import com.bka.ssi.controller.verification.company.services.scripts.acapy.dto.input.ACAPYPresentProofDto;
import org.slf4j.Logger;
import org.springframework.stereotype.Service;

@Service
public class GuestVerificationWebhookService implements WebhookService {

    private final Logger logger;
    private final GuestVerificationService guestVerificationService;

    public GuestVerificationWebhookService(Logger logger,
        GuestVerificationService guestVerificationService) {
        this.logger = logger;
        this.guestVerificationService = guestVerificationService;
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
                this.guestVerificationService.handlePresentationAcknowledged(inputDto);
                break;
            case "verified":
                logger.debug("PresentProof state: " + inputDto.getState());
                this.guestVerificationService.handleProofVerified(inputDto);
                break;
            default:
                logger.debug("Unknown PresentProof state: " + inputDto.getState());
                break;
        }
    }
}
