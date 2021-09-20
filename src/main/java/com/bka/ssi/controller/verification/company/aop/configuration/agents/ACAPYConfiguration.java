package com.bka.ssi.controller.verification.company.aop.configuration.agents;

import com.bka.ssi.controller.verification.acapy_client.api.PresentProofV10Api;
import com.bka.ssi.controller.verification.acapy_client.invoker.ApiClient;
import com.bka.ssi.controller.verification.acapy_client.invoker.Configuration;
import com.bka.ssi.controller.verification.acapy_client.invoker.auth.ApiKeyAuth;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;

@org.springframework.context.annotation.Configuration
public class ACAPYConfiguration {

    @Value("${verification.agent.name}")
    private String name;

    @Value("${verification.agent.endpoint}")
    private String endpoint;

    @Value("${verification.agent.host}")
    private String host;

    @Value("${verification.agent.port}")
    private String port;

    @Value("${verification.agent.api_key}")
    private String apiKey;

    @Value("${verification.agent.webhook.api_key}")
    private String webhookApiKey;

    @Value("${verification.agent.api_key_header_name}")
    private String apiKeyHeaderName;

    @Value("${verification.agent.didcomm_url}")
    private String didcommUrl;

    @Value("${verification.agent.aries_message_type}")
    private String ariesMessageType;

    @Value("${verification.agent.aries_attach_id}")
    private String ariesAttachId;

    @Value("${verification.agent.verkey}")
    private String verKey;
    
    @Value("${guest.basisIdVerificationDevMode:false}")
    private Boolean basisIdVerificationDevMode;

    private ApiClient apiClient;

    @Bean
    public ApiClient apiClient() {
        ApiClient apiClient =
            com.bka.ssi.controller.verification.acapy_client.invoker.Configuration
                .getDefaultApiClient();

        apiClient.setBasePath(this.host + ":" + this.port);
        // ToDo - format according to host <-> accreditation.agent.host <-> ACCR_AGENT_API_URL

        ((ApiKeyAuth) apiClient.getAuthentication("ApiKeyHeader")).setApiKey(this.apiKey);
        return apiClient;
    }
    
    @Bean
    public PresentProofV10Api presentProofApi() {
    	return new PresentProofV10Api(apiClient());
    }

    public ACAPYConfiguration() {
        this.apiClient = Configuration.getDefaultApiClient();
        this.apiClient.setBasePath(this.host + ":" + this.port);
        // ToDo - format according to host <-> accreditation.agent.host <-> ACCR_AGENT_API_URL

        ((ApiKeyAuth) this.apiClient.getAuthentication("ApiKeyHeader")).setApiKey(this.apiKey);
    }

    public String getName() { return name; }

    public String getEndpoint() { return endpoint; }

    public String getHost() {
        return host;
    }

    public String getPort() {
        return port;
    }

    public String getApiKey() {
        return apiKey;
    }

    public String getWebhookApiKey() {
        return webhookApiKey;
    }

    public String getApiKeyHeaderName() {
        return apiKeyHeaderName;
    }

    public String getDidcommUrl() { return didcommUrl; }

    public String getAriesMessageType() { return ariesMessageType; }

    public String getAriesAttachId() { return ariesAttachId; }

    public String getVerKey() { return verKey; }

    public ApiClient getApiClient() {
        return apiClient;
    }

	public Boolean getBasisIdVerificationDevMode() {
		return basisIdVerificationDevMode;
	}
}
