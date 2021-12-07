package com.bka.ssi.controller.verification.company.services.scripts.acapy.utilities;

import com.bka.ssi.controller.verification.company.aop.configuration.agents.ACAPYConfiguration;
import com.bka.ssi.controller.verification.company.services.exceptions.ScriptException;
import com.bka.ssi.controller.verification.company.services.scripts.ProofUtility;
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

@Service
public class ACAPYConnectionlessProofUtility implements
    ProofUtility<CredentialDefinition, Schema, V10PresentationExchange,
        V10PresentationCreateRequestRequest> {

    private final PresentProofV10Api proofApi;
    private final CredentialDefinitionApi credentialDefinitionApi;
    private final ACAPYConfiguration acapyConfiguration;
    private final SchemaApi schemaApi;
    private final Logger logger;

    /* TODO - Bind agentURL and apiKey through @Value */
    ACAPYConnectionlessProofUtility(Logger logger, ACAPYConfiguration acapyConfiguration) {
        this.logger = logger;
        this.acapyConfiguration = acapyConfiguration;
        proofApi = new PresentProofV10Api();
        credentialDefinitionApi = new CredentialDefinitionApi();
        schemaApi = new SchemaApi();

        /* TODO - Configure default client */
        /*
        ApiClient defaultClient = Configuration.getDefaultApiClient();
        defaultClient.setBasePath(agentURL);
        ApiKeyAuth ApiKeyHeader = (ApiKeyAuth) defaultClient.getAuthentication("ApiKeyHeader");
        ApiKeyHeader.setApiKey(apiKey);
         */
    }

    @Override
    public CredentialDefinition getCredentialDefinition(String credentialDefinitionId)
        throws Exception {
        /* TODO - BKAACMGT-73 - Logging */

        CredentialDefinition credentialDefinition = null;
        try {
            credentialDefinition =
                credentialDefinitionApi.credentialDefinitionsCredDefIdGet(credentialDefinitionId)
                    .getCredentialDefinition();
        } catch (ApiException ex) {
            /* TODO - BKAACMGT-73 - Log internal error, remove ex.printStackTrace() */
            ex.printStackTrace();
            /* TODO - This class is a Placeholder - change it, once first specific exception
                defined in service layer scripts */
            throw new ScriptException();
        }
        return credentialDefinition;
    }

    @Override
    public Schema getSchema(String schemaId) throws Exception {
        /* TODO - BKAACMGT-73 - Logging */

        Schema schema = null;
        try {
            schema = schemaApi.schemasSchemaIdGet(schemaId).getSchema();
        } catch (ApiException ex) {
            /* TODO - BKAACMGT-73 - Log internal error, remove ex.printStackTrace() */
            ex.printStackTrace();
            /* TODO - This class is a Placeholder - change it, once first specific exception
                defined in service layer scripts */
            throw new ScriptException();
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
            v10PresentationExchange = proofApi.presentProofCreateRequestPost(proofRequest);
        } catch (ApiException ex) {
            /* TODO - BKAACMGT-73 - Log internal error, remove ex.printStackTrace() */
            //ex.printStackTrace();
            logger.error(ex.toString());
            /* TODO - This class is a Placeholder - change it, once first specific exception
                defined in service layer scripts */
            throw new ScriptException();
        }
        return v10PresentationExchange;
    }

}
