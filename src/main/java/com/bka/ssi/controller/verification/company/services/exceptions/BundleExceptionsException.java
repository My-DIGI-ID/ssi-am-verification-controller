package com.bka.ssi.controller.verification.company.services.exceptions;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * The type Bundle exceptions exception.
 */
public class BundleExceptionsException extends Exception {
    private final List<? extends Exception> exceptions;

    /**
     * Instantiates a new Bundle exceptions exception.
     *
     * @param exceptions the exceptions
     */
    public BundleExceptionsException(List<? extends Exception> exceptions) {
        this(exceptions != null ? toString(exceptions) : null, exceptions);
    }

    /**
     * Instantiates a new Bundle exceptions exception.
     *
     * @param message    the message
     * @param exceptions the exceptions
     */
    public BundleExceptionsException(String message, List<? extends Exception> exceptions) {
        super(message);
        if (exceptions == null) {
            this.exceptions = null;
        } else {
            this.exceptions = new ArrayList<>(exceptions);
        }
    }

    /**
     * Gets exceptions.
     *
     * @return the exceptions
     */
    public List<? extends Exception> getExceptions() {
        return exceptions;
    }

    private static String toString(List<? extends Exception> exceptions) {
        return exceptions
                .stream()
                .map(exception -> exception == null || exception.getMessage() == null ? "null" :
                        exception.getMessage())
                .collect(Collectors.joining(", "));
    }
}
