package com.bka.ssi.controller.verification.company.services.models.common;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Base64Payload {
    @JsonProperty("base64")
    private String base64;

    public Base64Payload() {};

    public void setBase64(String base64) {
        this.base64 = base64;
    }

    public String getBase64() {
        return base64;
    }
}
