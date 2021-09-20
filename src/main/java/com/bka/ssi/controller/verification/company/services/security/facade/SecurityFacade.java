package com.bka.ssi.controller.verification.company.services.security.facade;

import com.bka.ssi.controller.verification.company.services.exceptions.UnauthenticatedException;
import com.bka.ssi.controller.verification.company.services.security.authentication.AuthenticationService;
import com.bka.ssi.controller.verification.company.services.security.authorization.AuthorizationService;
import com.bka.ssi.controller.verification.company.services.security.utilities.BearerTokenParser;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import javax.servlet.http.HttpServletRequest;

@Aspect
@Component
public class SecurityFacade {

    private final AuthenticationService authenticationService;
    private final AuthorizationService authorizationService;
    private final BearerTokenParser bearerTokenParser;
    private final Logger logger;

    public SecurityFacade(
        AuthenticationService authenticationService,
        AuthorizationService authorizationService,
        BearerTokenParser bearerTokenParser, Logger logger) {
        this.authenticationService = authenticationService;
        this.authorizationService = authorizationService;
        this.bearerTokenParser = bearerTokenParser;
        this.logger = logger;
    }

    @Before("@annotation(ProtectedTransaction)")
    public void protectedTransaction(JoinPoint joinPoint) throws Exception {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        String scope = signature
            .getMethod()
            .getAnnotation(ProtectedTransaction.class)
            .scope();

        String resource = signature
            .getMethod()
            .getAnnotation(ProtectedTransaction.class)
            .resource();

        String conditionName = "res:" + resource + "#" + scope;

        String token = bearerTokenParser.getToken();
        logger.debug(token);
        if (token == null) {
            logger.debug("No token provided");
            throw new UnauthenticatedException();
        }

        this.authenticationService.verifyToken(token);
        this.authorizationService.verifyPermission(token, conditionName);
    }
}
