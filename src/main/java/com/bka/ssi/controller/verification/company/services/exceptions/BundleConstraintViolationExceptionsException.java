package com.bka.ssi.controller.verification.company.services.exceptions;

import java.util.List;
import javax.validation.ConstraintViolationException;

/**
 * The type Bundle constraint violation exceptions exception.
 */
public class BundleConstraintViolationExceptionsException extends BundleExceptionsException {

    /**
     * Instantiates a new Bundle constraint violation exceptions exception.
     *
     * @param exceptions the exceptions
     */
    public BundleConstraintViolationExceptionsException(
            List<ConstraintViolationException> exceptions) {
        super(exceptions);
    }

    /**
     * Instantiates a new Bundle constraint violation exceptions exception.
     *
     * @param message    the message
     * @param exceptions the exceptions
     */
    public BundleConstraintViolationExceptionsException(String message,
                                                        List<ConstraintViolationException> exceptions) {
        super(message, exceptions);
    }
}
