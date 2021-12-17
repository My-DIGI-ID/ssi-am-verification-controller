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

import com.bka.ssi.controller.verification.company.services.exceptions.UnauthenticatedException;
import com.bka.ssi.controller.verification.company.services.security.SSOClient;
import com.bka.ssi.controller.verification.company.services.security.utilities.ApiKeyRegistry;
import org.slf4j.Logger;
import org.springframework.stereotype.Service;

/**
 * The type Authentication service.
 */
@Service
public class AuthenticationService {

    private final SSOClient ssoClient;
    private final ApiKeyRegistry registry;
    private final Logger logger;

    /**
     * Instantiates a new Authentication service.
     *
     * @param ssoClient the sso client
     * @param registry  the registry
     * @param logger    the logger
     */
    public AuthenticationService(SSOClient ssoClient,
        ApiKeyRegistry registry,
        Logger logger) {
        this.ssoClient = ssoClient;
        this.registry = registry;
        this.logger = logger;
    }

    /**
     * Verify sso token boolean.
     *
     * @param token the token
     * @return the boolean
     * @throws Exception the exception
     */
    public boolean verifySSOToken(String token) throws Exception {
        logger.info("Verifying SSO token");

        if (token == null || token.equals("") || token.equals("null")) {
            logger.debug("SSO token is null or empty");
            throw new UnauthenticatedException();
        }

        Boolean isValid = ssoClient.verifyToken(token);

        if (!isValid) {
            logger.debug("SSO token not valid");
            throw new UnauthenticatedException();
        }

        return true;
    }

    /**
     * Verify api key boolean.
     *
     * @param id     the id
     * @param apiKey the api key
     * @return the boolean
     * @throws UnauthenticatedException the unauthenticated exception
     */
    public boolean verifyApiKey(String id, String apiKey) throws UnauthenticatedException {
        logger.info("Verifying api key");

        if (apiKey == null || apiKey.equals("") || apiKey.equals("null") ||
            id == null || id.equals("")) {
            logger.debug("API key is null or empty or id is null or empty");
            throw new UnauthenticatedException();
        }

        if (this.registry.getEntryById(id) == null) {
            logger.debug("API key id not found in registry");
            throw new UnauthenticatedException();
        }

        if (!this.registry.getEntryById(id).getSecond().equals(apiKey)) {
            logger.debug("API key not valid");
            throw new UnauthenticatedException();
        }

        return true;
    }
}
