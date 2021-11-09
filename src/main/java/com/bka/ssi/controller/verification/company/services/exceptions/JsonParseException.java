package com.bka.ssi.controller.verification.company.services.exceptions;

public class JsonParseException extends Exception {

    public JsonParseException() {
        super("JSON parse exception");
    }

    public JsonParseException(String message) {
        super("JSON parse exception: " + message);
    }
}
