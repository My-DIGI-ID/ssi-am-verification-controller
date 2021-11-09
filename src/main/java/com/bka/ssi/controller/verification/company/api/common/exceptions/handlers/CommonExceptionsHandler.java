package com.bka.ssi.controller.verification.company.api.common.exceptions.handlers;

import com.bka.ssi.controller.verification.company.api.common.exceptions.LogOutput;
import com.bka.ssi.controller.verification.company.api.common.exceptions.response.RestErrorResponse;
import com.bka.ssi.controller.verification.company.api.common.exceptions.response.factories.RestErrorResponseFactory;
import com.bka.ssi.controller.verification.company.services.exceptions.NotFoundException;
import com.bka.ssi.controller.verification.company.services.exceptions.UnauthenticatedException;
import com.bka.ssi.controller.verification.company.services.exceptions.UnauthorizedException;
import org.slf4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.annotation.Priority;
import javax.servlet.http.HttpServletRequest;

@RestControllerAdvice(basePackages = {"com.bka.ssi.controller.verification.company.api"})
@Priority(1)
public class CommonExceptionsHandler {

    private final RestErrorResponseFactory restErrorResponseFactory;
    private final Logger logger;

    public CommonExceptionsHandler(RestErrorResponseFactory restErrorResponseFactory,
        Logger logger) {
        this.restErrorResponseFactory = restErrorResponseFactory;
        this.logger = logger;
    }

    @ExceptionHandler(UnsupportedOperationException.class)
    @ResponseStatus(HttpStatus.NOT_IMPLEMENTED)
    public ResponseEntity<RestErrorResponse> handleUnsupportedOperationException(
        UnsupportedOperationException ex,
        HttpServletRequest request
    ) {
        RestErrorResponse response = restErrorResponseFactory.create(
            "message.common.rest.error.not_implemented_exception_placeholder",
            HttpStatus.NOT_IMPLEMENTED,
            request
        );

        logger.debug(ex.getMessage());
        logger.error(new LogOutput(response).toString());
        return new ResponseEntity<>(response, HttpStatus.NOT_IMPLEMENTED);
    }

    @ExceptionHandler(UnauthorizedException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ResponseEntity<RestErrorResponse> handleUnauthorizedException(
        UnauthorizedException ex,
        HttpServletRequest request
    ) {
        RestErrorResponse response = restErrorResponseFactory.create(
            "message.common.rest.error.unauthorized_exception_placeholder",
            HttpStatus.FORBIDDEN,
            request
        );

        logger.debug(ex.getMessage());
        logger.error(new LogOutput(response).toString());
        return new ResponseEntity<>(response, HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(UnauthenticatedException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ResponseEntity<RestErrorResponse> handleUnauthenticatedException(
        UnauthenticatedException ex,
        HttpServletRequest request
    ) {
        RestErrorResponse response = restErrorResponseFactory.create(
            "message.common.rest.error.unauthorized_exception_placeholder",
            HttpStatus.UNAUTHORIZED,
            request
        );

        logger.debug(ex.getMessage());
        logger.error(new LogOutput(response).toString());
        return new ResponseEntity<>(response, HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<RestErrorResponse> handleUnknownException(Exception ex,
        HttpServletRequest request) {

        RestErrorResponse response = restErrorResponseFactory.create(
            "message.common.rest.error.unknown_exception_placeholder",
            HttpStatus.INTERNAL_SERVER_ERROR,
            request
        );

        logger.debug(ex.getMessage());
        logger.error(new LogOutput(response).toString());
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<RestErrorResponse> handleNotFoundException(NotFoundException ex,
        HttpServletRequest request) {

        RestErrorResponse response = restErrorResponseFactory.create(
            "message.common.rest.error.not_found_exception_placeholder",
            HttpStatus.NOT_FOUND, request);

        logger.debug(ex.getMessage());
        logger.error(new LogOutput(response).toString());
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }
}
