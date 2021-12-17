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

package com.bka.ssi.controller.verification.company.services.scripts.acapy.utilities;

import com.bka.ssi.controller.verification.company.aop.configuration.agents.ACAPYConfiguration;
import com.bka.ssi.controller.verification.company.services.scripts.ProofUtility;
import com.bka.ssi.controller.verification.company.services.scripts.acapy.exceptions.ACAPYException;
import io.github.my_digi_id.acapy_client.api.CredentialDefinitionApi;
import io.github.my_digi_id.acapy_client.api.PresentProofV10Api;
import io.github.my_digi_id.acapy_client.api.SchemaApi;
import io.github.my_digi_id.acapy_client.invoker.ApiException;
import io.github.my_digi_id.acapy_client.model.CredentialDefinition;
import io.github.my_digi_id.acapy_client.model.Schema;
import io.github.my_digi_id.acapy_client.model.V10PresentationCreateRequestRequest;
import io.github.my_digi_id.acapy_client.model.V10PresentationExchange;
import org.slf4j.Logger;
import org.springframework.stereotype.Service;

/**
 * The type Acapy connectionless proof utility.
 */
@Service
public class ACAPYConnectionlessProofUtility implements
    ProofUtility<CredentialDefinition, Schema, V10PresentationExchange,
        V10PresentationCreateRequestRequest> {

    private final PresentProofV10Api proofApi;
    private final CredentialDefinitionApi credentialDefinitionApi;
    private final ACAPYConfiguration acapyConfiguration;
    private final SchemaApi schemaApi;
    private final Logger logger;

    /**
     * Instantiates a new Acapy connectionless proof utility.
     *
     * @param logger             the logger
     * @param acapyConfiguration the acapy configuration
     */
    ACAPYConnectionlessProofUtility(Logger logger, ACAPYConfiguration acapyConfiguration) {
        this.logger = logger;
        this.acapyConfiguration = acapyConfiguration;
        this.proofApi = new PresentProofV10Api(this.acapyConfiguration.getApiClient());
        this.credentialDefinitionApi =
            new CredentialDefinitionApi(this.acapyConfiguration.getApiClient());
        this.schemaApi = new SchemaApi(this.acapyConfiguration.getApiClient());
    }

    @Override
    public CredentialDefinition getCredentialDefinition(String credentialDefinitionId)
        throws Exception {
        /* TODO - BKAACMGT-73 - Logging */

        CredentialDefinition credentialDefinition = null;
        try {
            credentialDefinition =
                this.credentialDefinitionApi
                    .credentialDefinitionsCredDefIdGet(credentialDefinitionId)
                    .getCredentialDefinition();
        } catch (ApiException ex) {
            /* TODO - BKAACMGT-73 - Log internal error, remove ex.printStackTrace() */
            ex.printStackTrace();
            /* TODO - This class is a Placeholder - change it, once first specific exception
                defined in service layer scripts */
            throw new ACAPYException();
        }
        return credentialDefinition;
    }

    @Override
    public Schema getSchema(String schemaId) throws Exception {
        /* TODO - BKAACMGT-73 - Logging */

        Schema schema = null;
        try {
            schema = this.schemaApi.schemasSchemaIdGet(schemaId).getSchema();
        } catch (ApiException ex) {
            /* TODO - BKAACMGT-73 - Log internal error, remove ex.printStackTrace() */
            ex.printStackTrace();
            /* TODO - This class is a Placeholder - change it, once first specific exception
                defined in service layer scripts */
            throw new ACAPYException();
        }
        return schema;
    }

    @Override
    public V10PresentationExchange getProofRequest(V10PresentationCreateRequestRequest proofRequest)
        throws Exception {
        /* TODO - BKAACMGT-73 - Logging */
        logger.info("start: getProofRequest");

        V10PresentationExchange v10PresentationExchange = null;
        try {
            v10PresentationExchange = this.proofApi.presentProofCreateRequestPost(proofRequest);
        } catch (ApiException ex) {
            /* TODO - BKAACMGT-73 - Log internal error, remove ex.printStackTrace() */
            logger.error(ex.toString());
            /* TODO - This class is a Placeholder - change it, once first specific exception
                defined in service layer scripts */
            throw new ACAPYException();
        }
        return v10PresentationExchange;
    }
}
