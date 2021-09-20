package com.bka.ssi.controller.verification.company.api.common.rest.controllers;

import com.bka.ssi.controller.verification.company.services.VerificationService;
import com.bka.ssi.controller.verification.company.services.scripts.acapy.dto.input.ACAPYPresentProofDto;
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
    private VerificationService verificationService;

    public ACAPYWebhookController(Logger logger, VerificationService verificationService) {
        this.logger = logger;
        this.verificationService = verificationService;
    }

    @Operation(summary = "Pairwise Connection Record Updated")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204",
            description = "Nothing to return to ACAPY agent",
            content = @Content)
    })
    @PostMapping("/connections")
    public ResponseEntity<Void> onConnection(
        @RequestBody Object object) throws Exception {
        throw new UnsupportedOperationException("No support planned");
    }

    @Operation(summary = "Basic Message Received")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204",
            description = "Nothing to return to ACAPY agent",
            content = @Content)
    })
    @PostMapping("/basicmessages")
    public ResponseEntity<Void> onBasicMessage(
        @RequestBody Object object) throws Exception {
        /* Out of Scope for MVP and beyond */
        throw new UnsupportedOperationException("No support planned");
    }

    @Operation(summary = "Forward Message Received")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204",
            description = "Nothing to return to ACAPY agent",
            content = @Content)
    })
    @PostMapping("/forward")
    public ResponseEntity<Void> onForward(
        @RequestBody Object object) throws Exception {
        /* Out of Scope for MVP and beyond */
        throw new UnsupportedOperationException("No support planned");
    }

    @Operation(summary = "Credential Exchange Record Updated")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204",
            description = "Nothing to return to ACAPY agent",
            content = @Content)
    })
    @PostMapping("/issue_credential")
    public ResponseEntity<Void> onIssueCredential(
        @RequestBody Object object) throws Exception {
        throw new UnsupportedOperationException("No support planned");
    }

    @Operation(summary = "Presentation Exchange Record Updated")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204",
            description = "Nothing to return to ACAPY agent",
            content = @Content)
    })
    @PostMapping("/present_proof")
    public ResponseEntity<Void> onPresentProof(
        @RequestBody ACAPYPresentProofDto acapyPresentProofDto) throws Exception {
        /* In Scope of MVP if verification of Basis-id is done in accreditation controller */
        logger.info("start: onPresentProof");

        logger.info("state: " + acapyPresentProofDto.getState());

        switch (acapyPresentProofDto.getState()) {
            case "proposal_sent":
            case "proposal_received":
            case "request_sent":
            case "request_received":
            case "presentation_sent":
            case "presentation_acked":
                logger.debug("Ignoring PresentProof state: " + acapyPresentProofDto.getState());
                break;
            case "presentation_received":
                logger.debug("PresentProof state: " + acapyPresentProofDto.getState());
                this.verificationService.handlePresentationAcknowledged(acapyPresentProofDto);
                break;
            case "verified":
                logger.debug("PresentProof state: " + acapyPresentProofDto.getState());
                this.verificationService.handleProofVerified(acapyPresentProofDto);
                break;
            default:
                logger.debug("Unknown PresentProof state: " + acapyPresentProofDto.getState());
                break;
        }

        logger.info("end: onPresentProof");
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Out-of-Band Invitation")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204",
            description = "Nothing to return to ACAPY agent",
            content = @Content)
    })
    @PostMapping("/oob_invitation")
    public ResponseEntity<Void> onOutOfBandInvitation(
        @RequestBody Object object) throws Exception {
        /* Out of Scope for MVP and beyond */
        throw new UnsupportedOperationException("No support planned");
    }

    @Operation(summary = "Ping (debug)")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204",
            description = "Nothing to return to ACAPY agent",
            content = @Content)
    })
    @PostMapping("/ping")
    public ResponseEntity<Void> onPing(
        @RequestBody Object object) throws Exception {
        /* Out of Scope for MVP and beyond */
        throw new UnsupportedOperationException("No support planned");
    }

    @Operation(summary = "Issuer Credential Revocation")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204",
            description = "Nothing to return to ACAPY agent",
            content = @Content)
    })
    @PostMapping("/issuer_cred_rev")
    public ResponseEntity<Void> onIssuerCredRev(
        @RequestBody Object object) throws Exception {
        /* Out of Scope for MVP and beyond */
        throw new UnsupportedOperationException("No support planned");
    }

    @Operation(summary = "Revocation Registry Record Updated")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204",
            description = "Nothing to return to ACAPY agent",
            content = @Content)
    })
    @PostMapping("/revocation_registry")
    public ResponseEntity<Void> onRevocationRegistry(
        @RequestBody Object object) throws Exception {
        /* Out of Scope for MVP and beyond */
        throw new UnsupportedOperationException("No support planned");
    }

    @Operation(summary = "Problem Report")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "204",
            description = "Nothing to return to ACAPY agent",
            content = @Content)
    })
    @PostMapping("/problem_report")
    public ResponseEntity<Void> onProblemReport(
        @RequestBody Object object) throws Exception {
        /* Out of Scope for MVP and beyond */
        throw new UnsupportedOperationException("No support planned");
    }
}
