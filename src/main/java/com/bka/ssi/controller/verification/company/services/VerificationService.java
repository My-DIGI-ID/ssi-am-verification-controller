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

package com.bka.ssi.controller.verification.company.services;

import com.bka.ssi.controller.verification.company.services.models.Verification;
import com.bka.ssi.controller.verification.company.services.scripts.acapy.dto.input.ACAPYPresentProofDto;

import java.net.URI;
import java.util.List;

/**
 * The interface Verification service.
 *
 * @param <T> the type parameter
 */
/* Transaction-Script design */
public interface VerificationService<T extends Verification> {

    /**
     * Handle proof request uri.
     *
     * @param locationId the location id
     * @param terminalId the terminal id
     * @return the uri
     * @throws Exception the exception
     */
    URI handleProofRequest(String locationId, String terminalId) throws Exception;

    /**
     * Handle presentation acknowledged.
     *
     * @param proofReceipt the proof receipt
     * @throws Exception the exception
     */
    void handlePresentationAcknowledged(ACAPYPresentProofDto proofReceipt) throws Exception;

    /**
     * Handle proof verified.
     *
     * @param proofVerified the proof verified
     * @throws Exception the exception
     */
    void handleProofVerified(ACAPYPresentProofDto proofVerified) throws Exception;

    /**
     * Handle verification process complete list.
     *
     * @param locationId the location id
     * @param terminalId the terminal id
     * @return the list
     * @throws Exception the exception
     */
    List<T> handleVerificationProcessComplete(String locationId, String terminalId)
        throws Exception;
}
