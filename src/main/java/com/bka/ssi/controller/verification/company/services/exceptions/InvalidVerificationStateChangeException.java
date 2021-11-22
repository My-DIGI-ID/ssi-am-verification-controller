package com.bka.ssi.controller.verification.company.services.exceptions;

public class InvalidVerificationStateChangeException extends Exception {

    public InvalidVerificationStateChangeException() {
        super("Invalid verification state change");
    }

    public InvalidVerificationStateChangeException(String message) {
        super("Invalid verification state change: " + message);
    }
}
