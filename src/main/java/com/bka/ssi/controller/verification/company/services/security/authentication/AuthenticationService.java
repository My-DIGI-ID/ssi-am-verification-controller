package com.bka.ssi.controller.verification.company.services.security.authentication;

import com.bka.ssi.controller.verification.company.services.exceptions.UnauthenticatedException;
import com.bka.ssi.controller.verification.company.services.security.SSOClient;
import com.bka.ssi.controller.verification.company.services.security.utilities.ApiKeyRegistry;
import org.slf4j.Logger;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {

    private final SSOClient ssoClient;
    private final ApiKeyRegistry registry;
    private final Logger logger;

    public AuthenticationService(SSOClient ssoClient,
        ApiKeyRegistry registry,
        Logger logger) {
        this.ssoClient = ssoClient;
        this.registry = registry;
        this.logger = logger;
    }

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
