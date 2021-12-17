package com.bka.ssi.controller.verification.company.api.common.exceptions.handlers;

import com.bka.ssi.controller.verification.company.aop.logging.LoggingUtility;
import com.bka.ssi.controller.verification.company.api.common.exceptions.response.RestErrorResponse;
import com.bka.ssi.controller.verification.company.api.common.exceptions.response.factories.RestErrorResponseFactory;
import com.bka.ssi.controller.verification.company.services.exceptions.BundleConstraintViolationExceptionsException;
import org.slf4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.annotation.Priority;
import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolationException;

/**
 * The type Dto exceptions handler.
 */
@RestControllerAdvice(basePackages = {"com.bka.ssi.controller.verification.company.api"})
@Priority(0)
public class DTOExceptionsHandler {

    private final RestErrorResponseFactory restErrorResponseFactory;
    private final Logger logger;

    /**
     * Instantiates a new Dto exceptions handler.
     *
     * @param restErrorResponseFactory the rest error response factory
     * @param logger                   the logger
     */
    public DTOExceptionsHandler(RestErrorResponseFactory restErrorResponseFactory, Logger logger) {
        this.restErrorResponseFactory = restErrorResponseFactory;
        this.logger = logger;
    }

    /**
     * Handle method argument not valid exception response entity.
     *
     * @param ex      the ex
     * @param request the request
     * @return the response entity
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<RestErrorResponse> handleMethodArgumentNotValidException(
            MethodArgumentNotValidException ex, HttpServletRequest request) {

        /* ToDo - care about ex.getBindingResult().getFieldErrors() */
        RestErrorResponse response = restErrorResponseFactory
                .create(ex.getBindingResult().getFieldErrors(), HttpStatus.BAD_REQUEST, request);

        LoggingUtility.logRestErrorResponse(logger, response, ex);
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }

    /**
     * Handle constraint violation exception response entity.
     *
     * @param ex      the ex
     * @param request the request
     * @return the response entity
     */
    @ExceptionHandler({ConstraintViolationException.class,
            BundleConstraintViolationExceptionsException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<RestErrorResponse> handleConstraintViolationException(
            Exception ex, HttpServletRequest request) {

        /* ToDo - care about ex.getMessage, validation might contain sensible information? for
            now ex.getMessage consists of constraintViolation.getMessage() */
        /* ToDo - make use of restErrorResponseFactory */
        RestErrorResponse response = new RestErrorResponse(HttpStatus.BAD_REQUEST,
                ex.getMessage(), request.getRequestURI());

        LoggingUtility.logRestErrorResponse(logger, response, ex);
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}
