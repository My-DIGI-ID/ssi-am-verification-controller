package com.bka.ssi.controller.verification.company.aop.exceptions;

public class MongoDbConfigurationException extends RuntimeException {
    public MongoDbConfigurationException() {
        super("Exception occurred while configuring the MongoDB client");
    }
}
