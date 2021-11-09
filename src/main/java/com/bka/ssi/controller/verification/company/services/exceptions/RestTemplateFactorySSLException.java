package com.bka.ssi.controller.verification.company.services.exceptions;

public class RestTemplateFactorySSLException extends Exception {
    public RestTemplateFactorySSLException() {
        super("Exception occurred while configuring SSL for the RestTemplate");
    }
}
