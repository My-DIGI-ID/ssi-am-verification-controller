package com.bka.ssi.controller.verification.company.api.v2.rest.controllers;

import com.bka.ssi.controller.verification.company.api.mappers.EmployeeVerificationOutputDtoMapper;
import com.bka.ssi.controller.verification.company.api.v2.rest.dto.output.EmployeeVerificationOutputDto;
import com.bka.ssi.controller.verification.company.services.models.EmployeeVerification;
import com.bka.ssi.controller.verification.company.services.scripts.acapy.EmployeeVerificationService;
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
@RestController("employeeProofRequestControllerV2")
@RequestMapping("/api/v2/employee")
public class EmployeeProofRequestController {
    private final EmployeeVerificationService employeeVerificationService;
    private final EmployeeVerificationOutputDtoMapper mapper;
    private final Logger logger;

    EmployeeProofRequestController(EmployeeVerificationService employeeVerificationService,
        EmployeeVerificationOutputDtoMapper employeeVerificationOutputDtoMapper,
        Logger logger) {
        this.employeeVerificationService = employeeVerificationService;
        this.mapper = employeeVerificationOutputDtoMapper;
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

        URI uri = employeeVerificationService.handleProofRequest(locationId, terminalId);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setLocation(uri);

        logger.info("end: onProofRequest");
        return new ResponseEntity<Void>(httpHeaders, HttpStatus.TEMPORARY_REDIRECT);
    }

    @Operation(summary = "Get List of verifications",
        security = @SecurityRequirement(name = "oauth2_verification_api"))
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Retrieved all verifications")
    })
    @GetMapping("/verification-process-completion")
    @SSOProtectedTransaction(scope = "scope:view", resource = "res:verification")
    public ResponseEntity<List<EmployeeVerificationOutputDto>> onVerificationProcessCompletion(
        @RequestParam(name = "locationId") String locationId,
        @RequestParam(name = "terminalId") String terminalId) throws Exception {
        logger.info("start: onVerificationProcessCompletion");

        List<EmployeeVerification> verifications =
            employeeVerificationService.handleVerificationProcessComplete(locationId,
                terminalId);

        List<EmployeeVerificationOutputDto> outputDto = verifications.stream()
            .map(verification -> mapper.entityToDto(verification))
            .collect(Collectors.toList());

        logger.info("end: onVerificationProcessCompletion");
        return ResponseEntity.ok(outputDto);
    }
}
