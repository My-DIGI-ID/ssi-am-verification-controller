package com.bka.ssi.controller.verification.company.api.v2.rest.controllers;

import com.bka.ssi.controller.verification.company.api.mappers.VerificationOutputDtoMapper;
import com.bka.ssi.controller.verification.company.api.v2.rest.dto.output.VerificationOutputDto;
import com.bka.ssi.controller.verification.company.services.VerificationService;
import com.bka.ssi.controller.verification.company.services.models.Verification;

import com.bka.ssi.controller.verification.company.services.security.facade.ProtectedTransaction;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
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
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Tag(name = "Proof request controller v2", description = "Handle proof requests from scanned QR codes")
@RestController("proofRequestControllerV2")
@RequestMapping("/api/v2")
public class ProofRequestController {

    /* TODO - VerificationService beans in a @Configuration class in order to instantiate via
        Autowired if multiple implementations available */
    private final VerificationService verificationService;
    private final VerificationOutputDtoMapper verificationOutputDtoMapper;
    private final Logger logger;

    ProofRequestController(VerificationService verificationService,
    	VerificationOutputDtoMapper verificationOutputDtoMapper,
        Logger logger) {
        this.verificationService = verificationService;
        this.verificationOutputDtoMapper = verificationOutputDtoMapper;
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
        /* TODO - BKAACMGT-73 - Logging */
        logger.info("API call: /request-proof");

        URI uri = verificationService.handleProofRequest(locationId, terminalId);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setLocation(uri);

        return new ResponseEntity<Void>(httpHeaders, HttpStatus.TEMPORARY_REDIRECT);
    }

    @Operation(summary = "Get List of verifications")
    @GetMapping("/verification-process-completion")
    @ProtectedTransaction(scope = "scope:view", resource = "verification")
    public ResponseEntity<List<VerificationOutputDto>> onVerificationProcessCompletion(
        @RequestParam(name = "locationId") String locationId,
        @RequestParam(name = "terminalId") String terminalId) throws Exception {

        logger.info("start: onVerificationProcessCompletion");
        
        List<Verification> vericationList = verificationService.handleVerificationProcessComplete(locationId, terminalId);
        
        List<VerificationOutputDto> verificationOutputDto = vericationList.stream()
        		.map(verification -> verificationOutputDtoMapper.toVerificationOutputDto(verification))
        		.collect(Collectors.toList());
        
        logger.info("end: onVerificationProcessCompletion");
        return ResponseEntity.ok(verificationOutputDto);
    }
}
