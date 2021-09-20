package com.bka.ssi.controller.verification.company.services.models.common;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ProofRequestService {
    @JsonProperty("recipientKeys")
    private String[] recipientKeys;

    @JsonProperty("routingKeys")
    private String[] routingKeys;

    @JsonProperty("serviceEndpoint")
    private String serviceEndpoint;

    @JsonProperty("endpointName")
    private String endpointName;

    public ProofRequestService() {}

    public void setRecipientKeys(String[] recipientKeys) {
        this.recipientKeys = recipientKeys;
    }

    public void setRoutingKeys(String[] routingKeys) {
        this.routingKeys = routingKeys;
    }

    public void setServiceEndpoint(String serviceEndpoint) {
        this.serviceEndpoint = serviceEndpoint;
    }

    public void setEndpointName(String endpointName) {
        this.endpointName = endpointName;
    }
}
