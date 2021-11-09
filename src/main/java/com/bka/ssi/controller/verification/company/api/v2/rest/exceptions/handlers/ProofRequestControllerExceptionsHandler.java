package com.bka.ssi.controller.verification.company.api.v2.rest.exceptions.handlers;

import com.bka.ssi.controller.verification.company.api.common.exceptions.LogOutput;
import com.bka.ssi.controller.verification.company.api.common.exceptions.response.RestErrorResponse;
import com.bka.ssi.controller.verification.company.api.common.exceptions.response.factories.RestErrorResponseFactory;
import com.bka.ssi.controller.verification.company.api.v1.rest.controllers.ProofRequestController;
import com.bka.ssi.controller.verification.company.infra.db.mongo.exceptions.InfrastructureException;
import com.bka.ssi.controller.verification.company.services.exceptions.ScriptException;
import com.bka.ssi.controller.verification.company.services.exceptions.ServiceException;
import org.slf4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.annotation.Priority;
import javax.servlet.http.HttpServletRequest;

@Component("proofRequestControllerExceptionsHandlerV1")
@RestControllerAdvice(basePackageClasses = ProofRequestController.class)
@Priority(0)
public class ProofRequestControllerExceptionsHandler {

    private final RestErrorResponseFactory restErrorResponseFactory;
    private final Logger logger;

    public ProofRequestControllerExceptionsHandler(
        RestErrorResponseFactory restErrorResponseFactory, Logger logger) {
        this.restErrorResponseFactory = restErrorResponseFactory;
        this.logger = logger;
    }

    @ExceptionHandler(ServiceException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    /* TODO - This handler is a placeholder - remove, once first specific exception defined in
        service layer */
    public ResponseEntity<RestErrorResponse> handleServiceException(
        ServiceException ex,
        HttpServletRequest request
    ) {
        RestErrorResponse response = restErrorResponseFactory.create(
            "message.common.rest.error.service_exception_placeholder",
            HttpStatus.BAD_REQUEST,
            request
        );

        logger.debug(ex.getMessage());
        logger.error(new LogOutput(response).toString());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ScriptException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    /* TODO - This handler is a placeholder - remove it, once first specific exception defined in
         service layer scripts */
    public ResponseEntity<RestErrorResponse> handleScriptException(
        ScriptException ex,
        HttpServletRequest request
    ) {
        RestErrorResponse response = restErrorResponseFactory.create(
            "message.common.rest.error.script_exception_placeholder",
            HttpStatus.BAD_REQUEST,
            request
        );

        logger.debug(ex.getMessage());
        logger.error(new LogOutput(response).toString());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(InfrastructureException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    /* TODO - This handler is a placeholder - remove, once first specific exception defined in
        infrastructure layer */
    public ResponseEntity<RestErrorResponse> handleInfrastructureException(
        InfrastructureException ex,
        HttpServletRequest request
    ) {
        RestErrorResponse response = restErrorResponseFactory.create(
            "message.common.rest.error.infrastructure_exception_placeholder",
            HttpStatus.BAD_REQUEST,
            request
        );

        logger.debug(ex.getMessage());
        logger.error(new LogOutput(response).toString());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}
