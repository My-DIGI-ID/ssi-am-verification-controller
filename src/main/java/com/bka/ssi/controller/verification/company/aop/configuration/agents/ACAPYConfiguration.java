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

package com.bka.ssi.controller.verification.company.aop.configuration.agents;

import com.fasterxml.jackson.databind.DeserializationFeature;
import io.github.my_digi_id.acapy_client.invoker.ApiClient;
import io.github.my_digi_id.acapy_client.invoker.auth.ApiKeyAuth;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * The type Acapy configuration.
 */
@Configuration
public class ACAPYConfiguration {

    /**
     * The constant API_KEY_ID.
     */
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

    /**
     * Api client api client.
     *
     * @return the api client
     */
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

    /**
     * Instantiates a new Acapy configuration.
     *
     * @param name                       the name
     * @param endpoint                   the endpoint
     * @param host                       the host
     * @param port                       the port
     * @param apiKey                     the api key
     * @param webhookApiKey              the webhook api key
     * @param apiKeyHeaderName           the api key header name
     * @param didcommUrl                 the didcomm url
     * @param ariesMessageType           the aries message type
     * @param ariesAttachId              the aries attach id
     * @param verKey                     the ver key
     * @param basisIdVerificationDevMode the basis id verification dev mode
     */
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

    /**
     * Gets name.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Gets endpoint.
     *
     * @return the endpoint
     */
    public String getEndpoint() {
        return endpoint;
    }

    /**
     * Gets host.
     *
     * @return the host
     */
    public String getHost() {
        return host;
    }

    /**
     * Gets port.
     *
     * @return the port
     */
    public String getPort() {
        return port;
    }

    /**
     * Gets api key.
     *
     * @return the api key
     */
    public String getApiKey() {
        return apiKey;
    }

    /**
     * Gets webhook api key.
     *
     * @return the webhook api key
     */
    public String getWebhookApiKey() {
        return webhookApiKey;
    }

    /**
     * Gets api key header name.
     *
     * @return the api key header name
     */
    public String getApiKeyHeaderName() {
        return apiKeyHeaderName;
    }

    /**
     * Gets didcomm url.
     *
     * @return the didcomm url
     */
    public String getDidcommUrl() {
        return didcommUrl;
    }

    /**
     * Gets aries message type.
     *
     * @return the aries message type
     */
    public String getAriesMessageType() {
        return ariesMessageType;
    }

    /**
     * Gets aries attach id.
     *
     * @return the aries attach id
     */
    public String getAriesAttachId() {
        return ariesAttachId;
    }

    /**
     * Gets ver key.
     *
     * @return the ver key
     */
    public String getVerKey() {
        return verKey;
    }

    /**
     * Gets basis id verification dev mode.
     *
     * @return the basis id verification dev mode
     */
    public Boolean getBasisIdVerificationDevMode() {
        return basisIdVerificationDevMode;
    }

    /**
     * Gets api client.
     *
     * @return the api client
     */
    public ApiClient getApiClient() {
        return apiClient();
    }
}
