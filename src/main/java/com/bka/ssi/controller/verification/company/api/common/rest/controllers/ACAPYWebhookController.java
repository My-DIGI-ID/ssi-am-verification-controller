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

package com.bka.ssi.controller.verification.company.api.common.rest.controllers;

import com.bka.ssi.controller.verification.company.aop.configuration.agents.ACAPYConfiguration;
import com.bka.ssi.controller.verification.company.services.scripts.acapy.dto.input.ACAPYPresentProofDto;
import com.bka.ssi.controller.verification.company.services.scripts.acapy.webhooks.WebhookServiceFactory;
import com.bka.ssi.controller.verification.company.services.security.facade.APIKeyProtectedTransaction;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * The type Acapy webhook controller.
 */
@Tag(name = "ACAPY Webhook Controller", description = "Handling requests from ACAPY agent")
@RestController()
@SecurityRequirement(name = "api_key_webhook_api")
@RequestMapping(value = "/topic")
public class ACAPYWebhookController {
    /**
     * Note that this ACAPY Webhook Controller only implements endpoints for v1.0 of ACAPY.
     * It does not provide endpoints for v2.0 of ACAPY, e.g. /issue_credential_v2_0,
     * /issue_credential_v2_0_indy, /issue_credential_v2_0_dif, etc.
     */

    private final Logger logger;
    private final WebhookServiceFactory webhookServiceFactory;

    /**
     * Instantiates a new Acapy webhook controller.
     *
     * @param logger                the logger
     * @param webhookServiceFactory the webhook service factory
     */
    public ACAPYWebhookController(Logger logger, WebhookServiceFactory webhookServiceFactory) {
        this.logger = logger;
        this.webhookServiceFactory = webhookServiceFactory;
    }

    /**
     * Handle connections Acapy webhook.
     *
     * @param object the object
     * @return the response entity
     * @throws Exception the exception
     */
    @Operation(summary = "Pairwise Connection Record Updated")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204",
            description = "Nothing to return to ACAPY agent",
            content = @Content)
    })
    @PostMapping("/connections")
    @APIKeyProtectedTransaction(id = ACAPYConfiguration.API_KEY_ID)
    public ResponseEntity<Void> onConnection(
        @RequestBody Object object) throws Exception {
        throw new UnsupportedOperationException("No support planned");
    }

    /**
     * Handle basic messages Acapy webhook.
     *
     * @param object the object
     * @return the response entity
     * @throws Exception the exception
     */
    @Operation(summary = "Basic Message Received")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204",
            description = "Nothing to return to ACAPY agent",
            content = @Content)
    })
    @PostMapping("/basicmessages")
    @APIKeyProtectedTransaction(id = ACAPYConfiguration.API_KEY_ID)
    public ResponseEntity<Void> onBasicMessage(
        @RequestBody Object object) throws Exception {
        /* Out of Scope for MVP and beyond */
        throw new UnsupportedOperationException("No support planned");
    }

    /**
     * Handle forward Acapy webhook.
     *
     * @param object the object
     * @return the response entity
     * @throws Exception the exception
     */
    @Operation(summary = "Forward Message Received")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204",
            description = "Nothing to return to ACAPY agent",
            content = @Content)
    })
    @PostMapping("/forward")
    @APIKeyProtectedTransaction(id = ACAPYConfiguration.API_KEY_ID)
    public ResponseEntity<Void> onForward(
        @RequestBody Object object) throws Exception {
        /* Out of Scope for MVP and beyond */
        throw new UnsupportedOperationException("No support planned");
    }

    /**
     * Handle issue credential Acapy webhook.
     *
     * @param object the object
     * @return the response entity
     * @throws Exception the exception
     */
    @Operation(summary = "Credential Exchange Record Updated")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204",
            description = "Nothing to return to ACAPY agent",
            content = @Content)
    })
    @PostMapping("/issue_credential")
    @APIKeyProtectedTransaction(id = ACAPYConfiguration.API_KEY_ID)
    public ResponseEntity<Void> onIssueCredential(
        @RequestBody Object object) throws Exception {
        throw new UnsupportedOperationException("No support planned");
    }

    /**
     * Handle present proof Acapy webhook.
     *
     * @param inputDto the input dto
     * @return the response entity
     * @throws Exception the exception
     */
    @Operation(summary = "Presentation Exchange Record Updated")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204",
            description = "Nothing to return to ACAPY agent",
            content = @Content)
    })
    @PostMapping("/present_proof")
    @APIKeyProtectedTransaction(id = ACAPYConfiguration.API_KEY_ID)
    public ResponseEntity<Void> onPresentProof(
        @RequestBody ACAPYPresentProofDto inputDto) throws Exception {
        /* In Scope of MVP if verification of Basis-id is done in accreditation controller */
        logger.info("start: onPresentProof");

        logger.info("state: " + inputDto.getState());

        this.webhookServiceFactory.handleOnPresentProof(inputDto);

        logger.info("end: onPresentProof");
        return ResponseEntity.noContent().build();
    }

    /**
     * Handle out of band invitation Acapy webhook.
     *
     * @param object the object
     * @return the response entity
     * @throws Exception the exception
     */
    @Operation(summary = "Out-of-Band Invitation")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204",
            description = "Nothing to return to ACAPY agent",
            content = @Content)
    })
    @PostMapping("/oob_invitation")
    @APIKeyProtectedTransaction(id = ACAPYConfiguration.API_KEY_ID)
    public ResponseEntity<Void> onOutOfBandInvitation(
        @RequestBody Object object) throws Exception {
        /* Out of Scope for MVP and beyond */
        throw new UnsupportedOperationException("No support planned");
    }

    /**
     * Handle ping Acapy webhook.
     *
     * @param object the object
     * @return the response entity
     * @throws Exception the exception
     */
    @Operation(summary = "Ping (debug)")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204",
            description = "Nothing to return to ACAPY agent",
            content = @Content)
    })
    @PostMapping("/ping")
    @APIKeyProtectedTransaction(id = ACAPYConfiguration.API_KEY_ID)
    public ResponseEntity<Void> onPing(
        @RequestBody Object object) throws Exception {
        /* Out of Scope for MVP and beyond */
        throw new UnsupportedOperationException("No support planned");
    }

    /**
     * Handle issuer cred rev Acapy webhook.
     *
     * @param object the object
     * @return the response entity
     * @throws Exception the exception
     */
    @Operation(summary = "Issuer Credential Revocation")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204",
            description = "Nothing to return to ACAPY agent",
            content = @Content)
    })
    @PostMapping("/issuer_cred_rev")
    @APIKeyProtectedTransaction(id = ACAPYConfiguration.API_KEY_ID)
    public ResponseEntity<Void> onIssuerCredRev(
        @RequestBody Object object) throws Exception {
        /* Out of Scope for MVP and beyond */
        throw new UnsupportedOperationException("No support planned");
    }

    /**
     * Handle revocation registry Acapy webhook.
     *
     * @param object the object
     * @return the response entity
     * @throws Exception the exception
     */
    @Operation(summary = "Revocation Registry Record Updated")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204",
            description = "Nothing to return to ACAPY agent",
            content = @Content)
    })
    @PostMapping("/revocation_registry")
    @APIKeyProtectedTransaction(id = ACAPYConfiguration.API_KEY_ID)
    public ResponseEntity<Void> onRevocationRegistry(
        @RequestBody Object object) throws Exception {
        /* Out of Scope for MVP and beyond */
        throw new UnsupportedOperationException("No support planned");
    }

    /**
     * Handle problem report response Acapy webhook.
     *
     * @param object the object
     * @return the response entity
     * @throws Exception the exception
     */
    @Operation(summary = "Problem Report")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204",
            description = "Nothing to return to ACAPY agent",
            content = @Content)
    })
    @PostMapping("/problem_report")
    @APIKeyProtectedTransaction(id = ACAPYConfiguration.API_KEY_ID)
    public ResponseEntity<Void> onProblemReport(
        @RequestBody Object object) throws Exception {
        /* Out of Scope for MVP and beyond */
        throw new UnsupportedOperationException("No support planned");
    }
}
