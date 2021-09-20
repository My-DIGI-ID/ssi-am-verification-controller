package com.bka.ssi.controller.verification.company.api.v1.rest.controllers;

import com.bka.ssi.controller.verification.company.services.VerificationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;

@Tag(name = "Proof request controller v1", description = "Handle proof requests from scanned QR codes")
@RestController("proofRequestControllerV1")
@RequestMapping({"/api/v1", "/api"})
public class ProofRequestController {

    /* TODO - VerificationService beans in a @Configuration class in order to instantiate via
        Autowired if multiple implementations available */
    private final VerificationService verificationService;
    private final Logger logger;

    ProofRequestController(VerificationService verificationService,
        Logger logger) {
        this.verificationService = verificationService;
        this.logger = logger;
    }

    /* TODO - verificationId needed if verification process is initiated manually */
    @Operation(summary = "Get URI for redirect")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "307",
            description = "Redirect to Aries agent for proof request",
            content = @Content)
    })
    @GetMapping("/request-proof")
    public ResponseEntity<Void> onProofRequest(
        @RequestParam(name = "locationId") String locationId,
        @RequestParam(name = "terminalId") String terminalId) throws Exception {
        logger.info("start: onProofRequest");
        /* TODO - BKAACMGT-73 - Logging */

        /* TODO - Avoid casting */
        URI uri = (URI) verificationService.handleProofRequest(locationId, terminalId);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setLocation(uri);

        logger.info("end: onProofRequest");
        return new ResponseEntity<Void>(httpHeaders, HttpStatus.TEMPORARY_REDIRECT);
    }

    @Operation(summary = "")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200",
            description = "OK",
            content = @Content)
    })
    @GetMapping("/verification-process-completion")
    public ResponseEntity<Void> onVerificationProcessCompletion(
        @RequestParam(name = "locationId") String locationId,
        @RequestParam(name = "terminalId") String terminalId) throws Exception {
        logger.info("start: onVerificationProcessCompletion");
        /* TODO - call the service to get verification completed by locationId and terminalId */

        logger.info("end: onVerificationProcessCompletion");
        return ResponseEntity.noContent().build();
    }
}
