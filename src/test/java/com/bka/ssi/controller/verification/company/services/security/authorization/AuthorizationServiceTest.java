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

package com.bka.ssi.controller.verification.company.services.security.authorization;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.bka.ssi.controller.verification.company.infra.sso.keycloak.KeycloakHttpClient;
import com.bka.ssi.controller.verification.company.services.exceptions.UnauthorizedException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.slf4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.web.client.HttpClientErrorException;

public class AuthorizationServiceTest {

    private static final String validToken = "token";
    private static final String invalidToken = "invalidToken";
    private static final String authorizedCondition = "validCondition";
    private static final String unauthorizedCondition = "invalidCondition";

    private static final HttpClientErrorException FORBIDDEN =
        HttpClientErrorException.Forbidden.create(HttpStatus.FORBIDDEN, null, null, null, null);
    private static final HttpClientErrorException UNAUTHORIZED =
        HttpClientErrorException.Unauthorized.create(HttpStatus.UNAUTHORIZED, null, null, null,
            null);

    @Mock
    private Logger logger;

    @Mock
    private KeycloakHttpClient client;

    @BeforeEach
    public void init() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void shouldReturnTrueForAuthorizedTransaction() throws Exception {
        // Given authorized permission response
        Mockito
            .when(client.verifyPermission(validToken, authorizedCondition))
            .thenReturn(true);

        // when
        AuthorizationService authenticationService = new AuthorizationService(client, logger);

        // then
        assertTrue(authenticationService.verifySSOPermission(validToken, authorizedCondition));
    }

    @Test
    public void shouldThrowUnauthorizedExceptionForRejectedPermissions() throws Exception {
        // Given rejected permission response
        Mockito
            .when(client.verifyPermission(validToken, unauthorizedCondition))
            .thenReturn(false);

        // when
        AuthorizationService authenticationService = new AuthorizationService(client, logger);

        // then
        assertThrows(UnauthorizedException.class, () -> {
            authenticationService.verifySSOPermission(validToken, unauthorizedCondition);
        });
    }

    @Test
    public void shouldForwardUnauthorizedExceptions() throws Exception {

        // Given rejected permission response
        Mockito
            .when(client.verifyPermission(validToken, unauthorizedCondition))
            .thenThrow(FORBIDDEN);

        // when
        AuthorizationService authenticationService = new AuthorizationService(client, logger);

        // then
        assertThrows(HttpClientErrorException.Forbidden.class, () -> {
            authenticationService.verifySSOPermission(validToken, unauthorizedCondition);
        });
    }

    @Test
    public void shouldThrowUnauthenticatedExceptionForInvalidToken() throws Exception {
        // Given rejected token response
        Mockito
            .when(client.verifyPermission(invalidToken, authorizedCondition))
            .thenThrow(UNAUTHORIZED);

        // when
        AuthorizationService authenticationService = new AuthorizationService(client, logger);

        // then
        assertThrows(HttpClientErrorException.Unauthorized.class, () -> {
            authenticationService.verifySSOPermission(invalidToken, authorizedCondition);
        });
    }
}
