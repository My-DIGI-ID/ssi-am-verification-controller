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

package com.bka.ssi.controller.verification.company.services.security.authentication;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.fail;

import com.bka.ssi.controller.verification.company.aop.configuration.agents.ACAPYConfiguration;
import com.bka.ssi.controller.verification.company.infra.sso.keycloak.KeycloakHttpClient;
import com.bka.ssi.controller.verification.company.services.exceptions.UnauthenticatedException;
import com.bka.ssi.controller.verification.company.services.security.utilities.ApiKeyRegistry;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.slf4j.Logger;
import org.springframework.data.util.Pair;

public class AuthenticationServiceTest {

    private static final String validToken = "token";
    private static final String invalidToken = "invalidToken";

    @Mock
    private Logger logger;

    @Mock
    private KeycloakHttpClient client;

    @Mock
    private ApiKeyRegistry registry;

    private AuthenticationService authenticationService;

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);

        this.authenticationService = new AuthenticationService(client, registry, logger);
    }

    @Test
    public void shouldThrowUnauthenticatedExceptionForInvalidToken() throws Exception {
        // given
        Mockito
            .when(client.verifyToken(invalidToken))
            .thenReturn(false);

        // then
        assertThrows(UnauthenticatedException.class, () -> {
            authenticationService.verifySSOToken(invalidToken);
        });
    }

    @Test
    public void shouldReturnTrueForValidToken() throws Exception {
        // given
        Mockito
            .when(client.verifyToken(validToken))
            .thenReturn(true);

        // then
        assertTrue(authenticationService.verifySSOToken(validToken));
    }

    @Test
    public void verifyApiKeyWithValidApiKey() {
        // given
        Mockito
            .when(registry.getEntryById(ACAPYConfiguration.API_KEY_ID))
            .thenReturn(Pair.of("X-API-Key", "123"));

        // then
        try {
            this.authenticationService.verifyApiKey(ACAPYConfiguration.API_KEY_ID, "123");
        } catch (Exception e) {
            fail(e.getMessage());
        }
    }

    @Test
    public void verifyApiKeyWithInvalidApiKey() {
        // given
        Mockito
            .when(registry.getEntryById(ACAPYConfiguration.API_KEY_ID))
            .thenReturn(Pair.of("X-API-Key", "123"));

        // then
        assertThrows(UnauthenticatedException.class, () -> {
            this.authenticationService.verifyApiKey(ACAPYConfiguration.API_KEY_ID, "456");
        });
    }

    @Test
    public void verifyApiKeyWithNoEntryInRegistry() {
        // given
        Mockito
            .when(registry.getEntryById(ACAPYConfiguration.API_KEY_ID))
            .thenReturn(null);

        // then
        assertThrows(UnauthenticatedException.class, () -> {
            this.authenticationService.verifyApiKey(ACAPYConfiguration.API_KEY_ID, "123");
        });
    }
}
