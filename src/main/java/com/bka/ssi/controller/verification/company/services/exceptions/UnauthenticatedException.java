package com.bka.ssi.controller.verification.company.services.exceptions;

public class UnauthenticatedException extends Exception {
    public UnauthenticatedException() {
        super("User is not authenticated");
    }
}
