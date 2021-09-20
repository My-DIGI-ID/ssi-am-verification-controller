package com.bka.ssi.controller.verification.company.api.common.exceptions.handlers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import com.bka.ssi.controller.verification.company.api.common.exceptions.response.RestErrorResponse;
import com.bka.ssi.controller.verification.company.api.common.exceptions.response.factories.RestErrorResponseFactory;
import com.bka.ssi.controller.verification.company.services.exceptions.UnauthorizedException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.context.MessageSource;
import org.springframework.context.NoSuchMessageException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;

import java.util.Locale;

class CommonExceptionsHandlerTest {

    private MockHttpServletRequest request;
    private ResponseEntity<RestErrorResponse> response;
    private MessageSource messageSource;
    private RestErrorResponseFactory factory;
    private CommonExceptionsHandler handler;

    @BeforeEach
    void setUp() {
        messageSource = Mockito.mock(MessageSource.class);
        factory = new RestErrorResponseFactory(messageSource);
        handler = new CommonExceptionsHandler(factory);
        request = new MockHttpServletRequest(null, "/test/api/endpoint");
    }

    @Test
    void handleUnsupportedOperationException() {
        // given
        UnsupportedOperationException exception = new UnsupportedOperationException();
        Mockito
            .when(messageSource.getMessage(
                "message.common.rest.error.not_implemented_exception_placeholder",
                null,
                Locale.ENGLISH))
            .thenReturn("Public error message");

        // when
        response = handler.handleUnsupportedOperationException(exception, request);

        // then
        assertEquals(response.getStatusCode(), HttpStatus.NOT_IMPLEMENTED);
        assertEquals(response.getBody().getPath(), "/test/api/endpoint");
        assertEquals(response.getBody().getStatus(), 501);
        assertEquals(response.getBody().getMessage(), "Public error message");
        assertNotEquals(response.getBody().getTimestamp(), null);
    }

    @Test
    void handleUnsupportedOperationExceptionWithoutMessage() {
        // given
        UnsupportedOperationException exception = new UnsupportedOperationException();
        Mockito
            .when(messageSource.getMessage(
                "message.common.rest.error.not_implemented_exception_placeholder",
                null,
                Locale.ENGLISH))
            .thenThrow(new NoSuchMessageException("No message"));

        // when
        response = handler.handleUnsupportedOperationException(exception, request);

        // then
        assertEquals(response.getStatusCode(), HttpStatus.NOT_IMPLEMENTED);
        assertEquals(response.getBody().getPath(), "/test/api/endpoint");
        assertEquals(response.getBody().getStatus(), 501);
        assertEquals(response.getBody().getMessage(), "No message available");
        assertNotEquals(response.getBody().getTimestamp(), null);
    }

    @Test
    void handleUnauthorizedException() {
        // given
        UnauthorizedException exception = new UnauthorizedException();
        Mockito
            .when(messageSource.getMessage(
                "message.common.rest.error.unauthorized_exception_placeholder",
                null,
                Locale.ENGLISH))
            .thenReturn("Public error message");

        // when
        response = handler.handleUnauthorizedException(exception, request);

        // then
        assertEquals(response.getStatusCode(), HttpStatus.UNAUTHORIZED);
        assertEquals(response.getBody().getPath(), "/test/api/endpoint");
        assertEquals(response.getBody().getStatus(), 401);
        assertEquals(response.getBody().getMessage(), "Public error message");
        assertNotEquals(response.getBody().getTimestamp(), null);
    }

    @Test
    void handleUnauthorizedExceptionWithoutMessage() {
        // given
        UnauthorizedException exception = new UnauthorizedException();
        Mockito
            .when(messageSource.getMessage(
                "message.common.rest.error.unauthorized_exception_placeholder",
                null,
                Locale.ENGLISH))
            .thenThrow(new NoSuchMessageException("No message"));

        // when
        response = handler.handleUnauthorizedException(exception, request);

        // then
        assertEquals(response.getStatusCode(), HttpStatus.UNAUTHORIZED);
        assertEquals(response.getBody().getPath(), "/test/api/endpoint");
        assertEquals(response.getBody().getStatus(), 401);
        assertEquals(response.getBody().getMessage(), "No message available");
        assertNotEquals(response.getBody().getTimestamp(), null);
    }

    @Test
    void handleUnknownException() {
        // given
        Exception exception = new Exception();
        Mockito
            .when(messageSource.getMessage(
                "message.common.rest.error.unknown_exception_placeholder",
                null,
                Locale.ENGLISH))
            .thenReturn("Public error message");

        // when
        response = handler.handleUnknownException(exception, request);

        // then
        assertEquals(response.getStatusCode(), HttpStatus.INTERNAL_SERVER_ERROR);
        assertEquals(response.getBody().getPath(), "/test/api/endpoint");
        assertEquals(response.getBody().getStatus(), 500);
        assertEquals(response.getBody().getMessage(), "Public error message");
        assertNotEquals(response.getBody().getTimestamp(), null);
    }

    @Test
    void handleUnknownExceptionWithoutMessage() {
        // given
        Exception exception = new Exception();
        Mockito
            .when(messageSource.getMessage(
                "message.common.rest.error.unknown_exception_placeholder",
                null,
                Locale.ENGLISH))
            .thenThrow(new NoSuchMessageException("No message"));

        // when
        response = handler.handleUnknownException(exception, request);

        // then
        assertEquals(response.getStatusCode(), HttpStatus.INTERNAL_SERVER_ERROR);
        assertEquals(response.getBody().getPath(), "/test/api/endpoint");
        assertEquals(response.getBody().getStatus(), 500);
        assertEquals(response.getBody().getMessage(), "No message available");
        assertNotEquals(response.getBody().getTimestamp(), null);
    }

    /* TODO - Might want to remove below tests, redundant to UnknownException */
    @Test
    void handleNoSuchMethodException() {
        // given
        NoSuchMethodException exception = new NoSuchMethodException();
        Mockito
            .when(messageSource.getMessage(
                "message.common.rest.error.unknown_exception_placeholder",
                null,
                Locale.ENGLISH))
            .thenReturn("Public error message");

        // when
        response = handler.handleUnknownException(exception, request);

        // then
        assertEquals(response.getStatusCode(), HttpStatus.INTERNAL_SERVER_ERROR);
        assertEquals(response.getBody().getPath(), "/test/api/endpoint");
        assertEquals(response.getBody().getStatus(), 500);
        assertEquals(response.getBody().getMessage(), "Public error message");
        assertNotEquals(response.getBody().getTimestamp(), null);
    }

    @Test
    void handleNoSuchMethodExceptionWithoutMessage() {
        // given
        NoSuchMethodException exception = new NoSuchMethodException();
        Mockito
            .when(messageSource.getMessage(
                "message.common.rest.error.unknown_exception_placeholder",
                null,
                Locale.ENGLISH))
            .thenThrow(new NoSuchMessageException("No message"));

        // when
        response = handler.handleUnknownException(exception, request);

        // then
        assertEquals(response.getStatusCode(), HttpStatus.INTERNAL_SERVER_ERROR);
        assertEquals(response.getBody().getPath(), "/test/api/endpoint");
        assertEquals(response.getBody().getStatus(), 500);
        assertEquals(response.getBody().getMessage(), "No message available");
        assertNotEquals(response.getBody().getTimestamp(), null);
    }

    @Test
    void handleIllegalAccessException() {
        // given
        IllegalAccessException exception = new IllegalAccessException();
        Mockito
            .when(messageSource.getMessage(
                "message.common.rest.error.unknown_exception_placeholder",
                null,
                Locale.ENGLISH))
            .thenReturn("Public error message");

        // when
        response = handler.handleUnknownException(exception, request);

        // then
        assertEquals(response.getStatusCode(), HttpStatus.INTERNAL_SERVER_ERROR);
        assertEquals(response.getBody().getPath(), "/test/api/endpoint");
        assertEquals(response.getBody().getStatus(), 500);
        assertEquals(response.getBody().getMessage(), "Public error message");
        assertNotEquals(response.getBody().getTimestamp(), null);
    }

    @Test
    void handleIllegalAccessExceptionWithoutMessage() {
        // given
        IllegalAccessException exception = new IllegalAccessException();
        Mockito
            .when(messageSource.getMessage(
                "message.common.rest.error.unknown_exception_placeholder",
                null,
                Locale.ENGLISH))
            .thenThrow(new NoSuchMessageException("No message"));

        // when
        response = handler.handleUnknownException(exception, request);

        // then
        assertEquals(response.getStatusCode(), HttpStatus.INTERNAL_SERVER_ERROR);
        assertEquals(response.getBody().getPath(), "/test/api/endpoint");
        assertEquals(response.getBody().getStatus(), 500);
        assertEquals(response.getBody().getMessage(), "No message available");
        assertNotEquals(response.getBody().getTimestamp(), null);
    }
}
