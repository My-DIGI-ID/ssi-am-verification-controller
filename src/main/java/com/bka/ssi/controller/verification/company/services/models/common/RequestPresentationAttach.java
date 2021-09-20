package com.bka.ssi.controller.verification.company.services.models.common;

import com.fasterxml.jackson.annotation.JsonProperty;

public class RequestPresentationAttach {
    @JsonProperty("@id")
    private String id;

    @JsonProperty("mime-type")
    private String mimeType;

    @JsonProperty("data")
    private Base64Payload data;

    public RequestPresentationAttach() {};

    public void setId(String id) {
        this.id = id;
    }

    public void setMimeType(String mimeType) {
        this.mimeType = mimeType;
    }

    public void setData(Base64Payload data) {
        this.data = data;
    }

    public String getId() {
        return id;
    }

    public String getMimeType() {
        return mimeType;
    }

    public Base64Payload getData() {
        return data;
    }
}
