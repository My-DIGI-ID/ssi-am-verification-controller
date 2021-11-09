package com.bka.ssi.controller.verification.company.infra.system.accreditation.exception;

public class AccreditationSSLException extends Exception {
    public AccreditationSSLException() {
        super("Exception occurred while configuring SSL for the Accreditation API client.");
    }
}
