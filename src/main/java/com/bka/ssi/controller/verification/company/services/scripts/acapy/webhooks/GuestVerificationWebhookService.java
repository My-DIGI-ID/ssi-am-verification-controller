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

import com.bka.ssi.controller.verification.company.services.scripts.acapy.GuestVerificationService;
import com.bka.ssi.controller.verification.company.services.scripts.acapy.dto.input.ACAPYPresentProofDto;
import org.slf4j.Logger;
import org.springframework.stereotype.Service;

/**
 * The type Guest verification webhook service.
 */
@Service
public class GuestVerificationWebhookService implements WebhookService {

    private final Logger logger;
    private final GuestVerificationService guestVerificationService;

    /**
     * Instantiates a new Guest verification webhook service.
     *
     * @param logger                   the logger
     * @param guestVerificationService the guest verification service
     */
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
