package com.bka.ssi.controller.verification.company.aop.configuration.agents;

import com.fasterxml.jackson.databind.DeserializationFeature;
import io.github.my_digi_id.acapy_client.invoker.ApiClient;
import io.github.my_digi_id.acapy_client.invoker.auth.ApiKeyAuth;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ACAPYConfiguration {

    public final static String API_KEY_ID = "ACAPY_WEBHOOK_API_KEY";

    private String name;
    private String endpoint;
    private String host;
    private String port;
    private String apiKey;
    private String webhookApiKey;
    private String apiKeyHeaderName;
    private String didcommUrl;
    private String ariesMessageType;
    private String ariesAttachId;
    private String verKey;
    private boolean basisIdVerificationDevMode;

    @Bean
    public ApiClient apiClient() {
        ApiClient apiClient =
            io.github.my_digi_id.acapy_client.invoker.Configuration.getDefaultApiClient();

        apiClient.setBasePath(this.host + ":" + this.port);

        ((ApiKeyAuth) apiClient.getAuthentication("ApiKeyHeader")).setApiKey(this.apiKey);

        apiClient.getJSON().getMapper()
            .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        return apiClient;
    }

    public ACAPYConfiguration(
        @Value("${verification.agent.name}") String name,
        @Value("${verification.agent.endpoint}") String endpoint,
        @Value("${verification.agent.host}") String host,
        @Value("${verification.agent.port}") String port,
        @Value("${verification.agent.api_key}") String apiKey,
        @Value("${verification.agent.webhook.api_key}") String webhookApiKey,
        @Value("${verification.agent.api_key_header_name}") String apiKeyHeaderName,
        @Value("${verification.agent.didcomm_url}") String didcommUrl,
        @Value("${verification.agent.aries_message_type}") String ariesMessageType,
        @Value("${verification.agent.aries_attach_id}") String ariesAttachId,
        @Value("${verification.agent.verkey}") String verKey,
        @Value("${guest.basisIdVerificationDevMode:false}") boolean basisIdVerificationDevMode
    ) {
        this.name = name;
        this.endpoint = endpoint;
        this.host = host;
        this.port = port;
        this.apiKey = apiKey;
        this.webhookApiKey = webhookApiKey;
        this.apiKeyHeaderName = apiKeyHeaderName;
        this.didcommUrl = didcommUrl;
        this.ariesMessageType = ariesMessageType;
        this.ariesAttachId = ariesAttachId;
        this.verKey = verKey;
        this.basisIdVerificationDevMode = basisIdVerificationDevMode;
    }

    public String getName() {
        return name;
    }

    public String getEndpoint() {
        return endpoint;
    }

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

    public String getDidcommUrl() {
        return didcommUrl;
    }

    public String getAriesMessageType() {
        return ariesMessageType;
    }

    public String getAriesAttachId() {
        return ariesAttachId;
    }

    public String getVerKey() {
        return verKey;
    }

    public Boolean getBasisIdVerificationDevMode() {
        return basisIdVerificationDevMode;
    }

    public ApiClient getApiClient() {
        return apiClient();
    }
}
