package com.bka.ssi.controller.verification.company.services.security.facade;

import com.bka.ssi.controller.verification.company.services.security.authentication.AuthenticationService;
import com.bka.ssi.controller.verification.company.services.security.authorization.AuthorizationService;
import com.bka.ssi.controller.verification.company.services.security.utilities.BearerTokenParser;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class SSOSecurityFacade {

    private final AuthenticationService authenticationService;
    private final AuthorizationService authorizationService;
    private final BearerTokenParser bearerTokenParser;
    private final Logger logger;

    public SSOSecurityFacade(
        AuthenticationService authenticationService,
        AuthorizationService authorizationService,
        BearerTokenParser bearerTokenParser, Logger logger) {
        this.authenticationService = authenticationService;
        this.authorizationService = authorizationService;
        this.bearerTokenParser = bearerTokenParser;
        this.logger = logger;
    }

    @Before("@annotation(com.bka.ssi.controller.verification.company.services.security.facade.SSOProtectedTransaction)")
    public void protectedTransaction(JoinPoint joinPoint) throws Exception {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        String scope = signature
            .getMethod()
            .getAnnotation(SSOProtectedTransaction.class)
            .scope();

        String resource = signature
            .getMethod()
            .getAnnotation(SSOProtectedTransaction.class)
            .resource();

        String conditionName = resource + "#" + scope;

        String token = bearerTokenParser.getToken();
        this.authenticationService.verifySSOToken(token);
        this.authorizationService.verifySSOPermission(token, conditionName);
    }
}
