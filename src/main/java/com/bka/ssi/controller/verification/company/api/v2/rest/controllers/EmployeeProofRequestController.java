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

/**
 * The type Employee proof request controller.
 */
@Tag(name = "Proof request controller v2", description = "Handle proof requests from scanned QR codes")
@RestController("employeeProofRequestControllerV2")
@RequestMapping("/api/v2/employee")
public class EmployeeProofRequestController {
    private final EmployeeVerificationService employeeVerificationService;
    private final EmployeeVerificationOutputDtoMapper mapper;
    private final Logger logger;

    /**
     * Instantiates a new Employee proof request controller.
     *
     * @param employeeVerificationService         the employee verification service
     * @param employeeVerificationOutputDtoMapper the employee verification output dto mapper
     * @param logger                              the logger
     */
    EmployeeProofRequestController(EmployeeVerificationService employeeVerificationService,
        EmployeeVerificationOutputDtoMapper employeeVerificationOutputDtoMapper,
        Logger logger) {
        this.employeeVerificationService = employeeVerificationService;
        this.mapper = employeeVerificationOutputDtoMapper;
        this.logger = logger;
    }

    /**
     * Get URI for redirect.
     *
     * @param locationId the location id
     * @param terminalId the terminal id
     * @return the response entity
     * @throws Exception the exception
     */
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

    /**
     * Get List of verifications.
     *
     * @param locationId the location id
     * @param terminalId the terminal id
     * @return the response entity
     * @throws Exception the exception
     */
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
