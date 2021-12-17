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

package com.bka.ssi.controller.verification.company.infra.sso.keycloak;

import com.bka.ssi.controller.verification.company.aop.configuration.security.SSOConfiguration;
import com.bka.ssi.controller.verification.company.services.exceptions.UnauthenticatedException;
import com.bka.ssi.controller.verification.company.services.exceptions.UnauthorizedException;
import com.bka.ssi.controller.verification.company.services.security.SSOClient;
import com.bka.ssi.controller.verification.company.services.utilities.http.RestTemplateFactory;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

/**
 * The type Keycloak http client.
 */
@Service
public class KeycloakHttpClient implements SSOClient {

    private final Logger logger;
    private final RestTemplateFactory restTemplateFactory;
    private final SSOConfiguration configuration;
    private final ObjectMapper mapper;

    /**
     * Instantiates a new Keycloak http client.
     *
     * @param logger              the logger
     * @param configuration       the configuration
     * @param restTemplateFactory the rest template factory
     */
    public KeycloakHttpClient(Logger logger, SSOConfiguration configuration,
        RestTemplateFactory restTemplateFactory) {
        this.logger = logger;
        this.configuration = configuration;
        this.mapper = new ObjectMapper();
        this.restTemplateFactory = restTemplateFactory;
    }

    public boolean verifyPermission(String token, String transaction) throws Exception {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        headers.setBearerAuth(token);

        MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
        body.add("grant_type", "urn:ietf:params:oauth:grant-type:uma-ticket");
        body.add("audience", configuration.getClientId());
        body.add("response_mode", "decision");
        body.add("permission", transaction);

        HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<>(body, headers);

        String url = configuration.getHost()
            .replace("{port}", ":" + configuration.getPort())
            .replace("{path}", configuration.getVerifyPermissionsPath())
            .replace("{realm}", configuration.getRealm());

        try {
            RestTemplate restTemplate =
                this.restTemplateFactory.create(configuration.isSslTrustAll(),
                    configuration.isSslVerifyHostname());

            ResponseEntity<String> response = restTemplate.postForEntity(url, entity, String.class);

            if (response
                .getStatusCode()
                .equals(HttpStatus.OK) && response.hasBody()) {
                JsonNode responseBodyJson = this.mapper.readTree(response.getBody());
                return responseBodyJson
                    .path("result")
                    .asBoolean();
            }
        } catch (HttpClientErrorException.Unauthorized e) {
            /* ToDo - handle exceptions outside of client - restTemplate ErrorHandler interferes
                with RestControllerAdvice */
            logger.error(e.getMessage());
            throw new UnauthenticatedException();
        } catch (HttpClientErrorException.Forbidden e) {
            /* ToDo - handle exceptions outside of client - restTemplate ErrorHandler interferes
                with RestControllerAdvice */
            logger.error(e.getMessage());
            throw new UnauthorizedException();
        }

        return false;
    }

    public boolean verifyToken(String token) throws Exception {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        MultiValueMap<String, String> body = new LinkedMultiValueMap<>();
        body.add("token", token);
        body.add("client_id", configuration.getClientId());
        body.add("client_secret", configuration.getClientSecret());

        HttpEntity<MultiValueMap<String, String>> entity = new HttpEntity<>(body, headers);

        String url = configuration.getHost()
            .replace("{port}", ":" + configuration.getPort())
            .replace("{path}", configuration.getVerifyTokenPath())
            .replace("{realm}", configuration.getRealm());

        RestTemplate restTemplate = this.restTemplateFactory.create(configuration.isSslTrustAll(),
            configuration.isSslVerifyHostname());

        ResponseEntity<String> response = restTemplate.postForEntity(url, entity, String.class);

        if (response
            .getStatusCode()
            .equals(HttpStatus.OK) && response.hasBody()) {
            JsonNode responseBodyJson = this.mapper.readTree(response.getBody());
            return responseBodyJson
                .path("active")
                .asBoolean();
        }

        return false;
    }
}
