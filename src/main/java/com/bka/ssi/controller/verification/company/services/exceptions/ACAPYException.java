package com.bka.ssi.controller.verification.company.services.exceptions;

public class ACAPYException extends Exception {

    public ACAPYException() {
        super("ACAPY API client Exception");
    }

    public ACAPYException(String message) {
        super("ACAPY API client Exception: " + message);
    }
}
