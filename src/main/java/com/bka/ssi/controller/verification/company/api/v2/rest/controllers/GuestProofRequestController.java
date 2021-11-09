package com.bka.ssi.controller.verification.company.api.v2.rest.controllers;

import com.bka.ssi.controller.verification.company.api.mappers.GuestVerificationOutputDtoMapper;
import com.bka.ssi.controller.verification.company.api.v2.rest.dto.output.GuestVerificationOutputDto;
import com.bka.ssi.controller.verification.company.services.models.GuestVerification;
import com.bka.ssi.controller.verification.company.services.scripts.acapy.GuestVerificationService;
import com.bka.ssi.controller.verification.company.services.security.facade.SSOProtectedTransaction;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
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
import java.util.List;
import java.util.stream.Collectors;

@Tag(name = "Proof request controller v2", description = "Handle proof requests from scanned QR codes")
@RestController("proofRequestControllerV2")
@RequestMapping("/api/v2/guest")
public class GuestProofRequestController {

    private final GuestVerificationService guestVerificationService;
    private final GuestVerificationOutputDtoMapper mapper;
    private final Logger logger;

    GuestProofRequestController(GuestVerificationService guestVerificationService,
        GuestVerificationOutputDtoMapper verificationOutputDtoMapper,
        Logger logger) {
        this.guestVerificationService = guestVerificationService;
        this.mapper = verificationOutputDtoMapper;
        this.logger = logger;
    }

    @Operation(summary = "Get URI for redirect")
    @ApiResponses(value = {
        @ApiResponse(responseCode = "307",
            description = "Redirect to Aries agent for proof request",
            content = @Content)
    })
    @GetMapping("/request-proof")
    /* ! Public API */
    public ResponseEntity<Void> onProofRequest(
        @RequestParam(name = "locationId") String locationId,
        @RequestParam(name = "terminalId") String terminalId) throws Exception {
        logger.info("start: onProofRequest");

        URI uri = guestVerificationService.handleProofRequest(locationId, terminalId);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setLocation(uri);

        logger.info("end: onProofRequest");
        return new ResponseEntity<Void>(httpHeaders, HttpStatus.TEMPORARY_REDIRECT);
    }

    /* TODO - BKAACMGT-165 - Currently content type of response is \*\/\* */
    @Operation(summary = "Get List of verifications",
        security = @SecurityRequirement(name = "oauth2_verification_api"))
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Retrieved all verifications",
            content = @Content)
    })
    @GetMapping("/verification-process-completion")
    @SSOProtectedTransaction(scope = "scope:view", resource = "res:verification")
    public ResponseEntity<List<GuestVerificationOutputDto>> onVerificationProcessCompletion(
        @RequestParam(name = "locationId") String locationId,
        @RequestParam(name = "terminalId") String terminalId) throws Exception {
        logger.info("start: onVerificationProcessCompletion");

        List<GuestVerification> verifications =
            guestVerificationService.handleVerificationProcessComplete(locationId,
                terminalId);

        List<GuestVerificationOutputDto> outputDto = verifications.stream()
            .map(verification -> mapper.entityToDto(verification))
            .collect(Collectors.toList());

        logger.info("end: onVerificationProcessCompletion");
        return ResponseEntity.ok(outputDto);
    }
}
