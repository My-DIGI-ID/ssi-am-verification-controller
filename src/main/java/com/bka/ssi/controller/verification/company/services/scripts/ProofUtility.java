package com.bka.ssi.controller.verification.company.services.scripts;

public interface ProofUtility<T, S, U, V> {

    /* Get credential definition: across Aries agents there exist a credential definition but
    types may differ */
    /* E.g. implementation can be hardcoded, a request per client library (potentially cached) or
     a database lookup. */
    T getCredentialDefinition(String credentialDefinitionId) throws Exception;

    /* Get schema for credential: across Aries agents there exist a schema but types may differ */
    /* E.g. implementation can be hardcoded, a request per client library (potentially cached) or
     a database lookup. */
    S getSchema(String schemaId) throws Exception;

    /* Process connectionless/ connection-oriented proof request with/without proposal */
    /* E.g. implementation per request through client library to agent. */
    U getProofRequest(V proofRequest) throws Exception;
}
