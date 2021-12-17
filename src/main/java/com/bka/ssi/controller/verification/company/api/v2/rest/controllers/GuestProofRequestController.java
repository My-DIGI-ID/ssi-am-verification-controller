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

import com.bka.ssi.controller.verification.company.api.mappers.GuestVerificationOutputDtoMapper;
import com.bka.ssi.controller.verification.company.api.v2.rest.dto.output.GuestVerificationOutputDto;
import com.bka.ssi.controller.verification.company.services.models.GuestVerification;
import com.bka.ssi.controller.verification.company.services.scripts.acapy.GuestVerificationService;
import com.bka.ssi.controller.verification.company.services.security.facade.SSOProtectedTransaction;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.slf4j.Logger;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

/**
 * The type Guest proof request controller.
 */
@Tag(name = "Proof request controller v2", description = "Handle proof requests from scanned QR codes")
@RestController("proofRequestControllerV2")
@RequestMapping("/api/v2/guest")
public class GuestProofRequestController {

    private final GuestVerificationService guestVerificationService;
    private final GuestVerificationOutputDtoMapper mapper;
    private final Logger logger;

    /**
     * Instantiates a new Guest proof request controller.
     *
     * @param guestVerificationService    the guest verification service
     * @param verificationOutputDtoMapper the verification output dto mapper
     * @param logger                      the logger
     */
    GuestProofRequestController(GuestVerificationService guestVerificationService,
        GuestVerificationOutputDtoMapper verificationOutputDtoMapper,
        Logger logger) {
        this.guestVerificationService = guestVerificationService;
        this.mapper = verificationOutputDtoMapper;
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

        URI uri = guestVerificationService.handleProofRequest(locationId, terminalId);
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

    /**
     * Checkout guest.
     *
     * @param verificationId the verification id
     * @return the response entity
     * @throws Exception the exception
     */
    @Operation(summary = "Checkout guest",
        security = @SecurityRequirement(name = "oauth2_verification_api"))
    @ApiResponses(value = {
        @ApiResponse(responseCode = "200", description = "Checkout guest",
            content = {@Content(mediaType = "application/json",
                schema = @Schema(implementation = GuestVerificationOutputDto.class))})
    })
    @GetMapping("/checkout/{verificationId}")
    @SSOProtectedTransaction(scope = "scope:update", resource = "res:verification")
    public ResponseEntity<GuestVerificationOutputDto> checkout(
        @PathVariable String verificationId) throws Exception {
        logger.info("start: checkout");

        GuestVerification verification = this.guestVerificationService.checkout(verificationId);
        GuestVerificationOutputDto outputDto = mapper.entityToDto(verification);

        logger.info("end: checkout");
        return ResponseEntity.ok(outputDto);
    }
}
