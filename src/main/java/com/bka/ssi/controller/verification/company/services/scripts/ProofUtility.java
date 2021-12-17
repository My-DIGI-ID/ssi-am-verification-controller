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

package com.bka.ssi.controller.verification.company.services.scripts;

/**
 * The interface Proof utility.
 *
 * @param <T> the type parameter
 * @param <S> the type parameter
 * @param <U> the type parameter
 * @param <V> the type parameter
 */
public interface ProofUtility<T, S, U, V> {

    /**
     * Gets credential definition.
     *
     * @param credentialDefinitionId the credential definition id
     * @return the credential definition
     * @throws Exception the exception
     */
/* Get credential definition: across Aries agents there exist a credential definition but
    types may differ */
    /* E.g. implementation can be hardcoded, a request per client library (potentially cached) or
     a database lookup. */
    T getCredentialDefinition(String credentialDefinitionId) throws Exception;

    /**
     * Gets schema.
     *
     * @param schemaId the schema id
     * @return the schema
     * @throws Exception the exception
     */
    /* Get schema for credential: across Aries agents there exist a schema but types may differ */
    /* E.g. implementation can be hardcoded, a request per client library (potentially cached) or
     a database lookup. */
    S getSchema(String schemaId) throws Exception;

    /**
     * Gets proof request.
     *
     * @param proofRequest the proof request
     * @return the proof request
     * @throws Exception the exception
     */
    /* Process connectionless/ connection-oriented proof request with/without proposal */
    /* E.g. implementation per request through client library to agent. */
    U getProofRequest(V proofRequest) throws Exception;
}
