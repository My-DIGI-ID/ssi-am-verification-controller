package com.bka.ssi.controller.verification.company.services.security.authentication;

import com.bka.ssi.controller.verification.company.services.exceptions.UnauthenticatedException;
import com.bka.ssi.controller.verification.company.services.security.SSOClient;
import org.slf4j.Logger;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {

    private final SSOClient ssoClient;
    private final Logger logger;

    public AuthenticationService(SSOClient ssoClient, Logger logger) {
        this.ssoClient = ssoClient;
        this.logger = logger;
    }

    public boolean verifyToken(String token) throws Exception {
        Boolean isValid = ssoClient.verifyToken(token);

        if (!isValid) {
            logger.debug("Token not valid");
            throw new UnauthenticatedException();
        }

        return true;
    }
}
