package com.bka.ssi.controller.verification.company.services.exceptions;

public class NotFoundException extends Exception {

    public NotFoundException() {
        super("Not found");
    }

    public NotFoundException(String message) {
        super("Not found: " + message);
    }
}
