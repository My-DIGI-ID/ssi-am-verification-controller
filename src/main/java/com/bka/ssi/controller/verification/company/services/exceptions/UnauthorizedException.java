package com.bka.ssi.controller.verification.company.services.exceptions;

/* TODO - Discuss if actually needed, dont see the need right now */
public class UnauthorizedException extends Exception {
    public UnauthorizedException() {
        super("User is not authorized to perform transaction");
    }
}
