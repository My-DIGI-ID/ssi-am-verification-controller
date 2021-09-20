package com.bka.ssi.controller.verification.company.api.v1.rest.exceptions.handlers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import com.bka.ssi.controller.verification.company.api.common.exceptions.response.RestErrorResponse;
import com.bka.ssi.controller.verification.company.api.common.exceptions.response.factories.RestErrorResponseFactory;
import com.bka.ssi.controller.verification.company.infra.db.mongo.exceptions.InfrastructureException;
import com.bka.ssi.controller.verification.company.services.exceptions.ScriptException;
import com.bka.ssi.controller.verification.company.services.exceptions.ServiceException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.context.MessageSource;
import org.springframework.context.NoSuchMessageException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;

import java.util.Locale;

class ProofRequestControllerExceptionsHandlerTest {

    private MockHttpServletRequest request;
    private ResponseEntity<RestErrorResponse> response;
    private MessageSource messageSource;
    private RestErrorResponseFactory factory;
    private ProofRequestControllerExceptionsHandler handler;

    @BeforeEach
    void setUp() {
        messageSource = Mockito.mock(MessageSource.class);
        factory = new RestErrorResponseFactory(messageSource);
        handler = new ProofRequestControllerExceptionsHandler(factory);
        request = new MockHttpServletRequest(null, "/test/api/endpoint");
    }

    @Test
        /* TODO - This test is a placeholder - remove it, once first specific exception defined
            in service layer */
    void handleServiceException() {
        // given
        ServiceException exception = new ServiceException();
        Mockito
            .when(messageSource.getMessage(
                "message.common.rest.error.service_exception_placeholder",
                null,
                Locale.ENGLISH))
            .thenReturn("Public error message");

        // when
        response = handler.handleServiceException(exception, request);

        // then
        assertEquals(response.getStatusCode(), HttpStatus.BAD_REQUEST);
        assertEquals(response.getBody().getPath(), "/test/api/endpoint");
        assertEquals(response.getBody().getStatus(), 400);
        assertEquals(response.getBody().getMessage(), "Public error message");
        assertNotEquals(response.getBody().getTimestamp(), null);
    }

    @Test
        /* TODO - This test is a placeholder - remove it, once first specific exception defined
            in service layer */
    void handleServiceExceptionWithoutMessage() {
        // given
        ServiceException exception = new ServiceException();
        Mockito
            .when(messageSource.getMessage(
                "message.common.rest.error.service_exception_placeholder",
                null,
                Locale.ENGLISH))
            .thenThrow(new NoSuchMessageException("No message"));

        // when
        response = handler.handleServiceException(exception, request);

        // then
        assertEquals(response.getStatusCode(), HttpStatus.BAD_REQUEST);
        assertEquals(response.getBody().getPath(), "/test/api/endpoint");
        assertEquals(response.getBody().getStatus(), 400);
        assertEquals(response.getBody().getMessage(), "No message available");
        assertNotEquals(response.getBody().getTimestamp(), null);
    }

    @Test
        /* TODO - This test is a placeholder - remove it, once first specific exception defined
            in service layer scripts */
    void handleScriptException() {
        // given
        ScriptException exception = new ScriptException();
        Mockito
            .when(messageSource.getMessage(
                "message.common.rest.error.script_exception_placeholder",
                null,
                Locale.ENGLISH))
            .thenReturn("Public error message");

        // when
        response = handler.handleScriptException(exception, request);

        // then
        assertEquals(response.getStatusCode(), HttpStatus.BAD_REQUEST);
        assertEquals(response.getBody().getPath(), "/test/api/endpoint");
        assertEquals(response.getBody().getStatus(), 400);
        assertEquals(response.getBody().getMessage(), "Public error message");
        assertNotEquals(response.getBody().getTimestamp(), null);
    }

    @Test
        /* TODO - This test is a placeholder - remove it, once first specific exception defined
            in service layer scripts */
    void handleScriptExceptionWithoutMessage() {
        // given
        ScriptException exception = new ScriptException();
        Mockito
            .when(messageSource.getMessage(
                "message.common.rest.error.script_exception_placeholder",
                null,
                Locale.ENGLISH))
            .thenThrow(new NoSuchMessageException("No message"));

        // when
        response = handler.handleScriptException(exception, request);

        // then
        assertEquals(response.getStatusCode(), HttpStatus.BAD_REQUEST);
        assertEquals(response.getBody().getPath(), "/test/api/endpoint");
        assertEquals(response.getBody().getStatus(), 400);
        assertEquals(response.getBody().getMessage(), "No message available");
        assertNotEquals(response.getBody().getTimestamp(), null);
    }

    @Test
        /* TODO - This test is a placeholder - remove it, once first specific exception defined
            in infrastructure layer */
    void handleInfrastructureException() {
        // given
        InfrastructureException exception = new InfrastructureException();
        Mockito
            .when(messageSource.getMessage(
                "message.common.rest.error.infrastructure_exception_placeholder",
                null,
                Locale.ENGLISH))
            .thenReturn("Public error message");

        // when
        response = handler.handleInfrastructureException(exception, request);

        // then
        assertEquals(response.getStatusCode(), HttpStatus.BAD_REQUEST);
        assertEquals(response.getBody().getPath(), "/test/api/endpoint");
        assertEquals(response.getBody().getStatus(), 400);
        assertEquals(response.getBody().getMessage(), "Public error message");
        assertNotEquals(response.getBody().getTimestamp(), null);
    }

    @Test
        /* TODO - This test is a placeholder - remove it, once first specific exception defined
            in infrastructure layer */
    void handleInfrastructureExceptionWithoutMessage() {
        // given
        InfrastructureException exception = new InfrastructureException();
        Mockito
            .when(messageSource.getMessage(
                "message.common.rest.error.infrastructure_exception_placeholder",
                null,
                Locale.ENGLISH))
            .thenThrow(new NoSuchMessageException("No message"));

        // when
        response = handler.handleInfrastructureException(exception, request);

        // then
        assertEquals(response.getStatusCode(), HttpStatus.BAD_REQUEST);
        assertEquals(response.getBody().getPath(), "/test/api/endpoint");
        assertEquals(response.getBody().getStatus(), 400);
        assertEquals(response.getBody().getMessage(), "No message available");
        assertNotEquals(response.getBody().getTimestamp(), null);
    }
}
