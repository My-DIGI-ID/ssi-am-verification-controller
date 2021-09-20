package com.bka.ssi.controller.verification.company.services.scripts.acapy.exceptions;

public class ACAPYCustomException extends Exception {
    public ACAPYCustomException() {
        super("Exception occurred in the service layer script for acapy");
    }
}
