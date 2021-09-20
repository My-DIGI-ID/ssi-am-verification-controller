package com.bka.ssi.controller.verification.company.services.models.common;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

public class ConnectionlessProofRequest {
    @JsonProperty("@type")
    private String type = "did:sov:BzCbsNYhMrjHiqZDTUASHg;spec/present-proof/1.0/request-presentation";

    @JsonProperty("@id")
    private String id;

    @JsonProperty("request_presentations~attach")
    @JsonInclude(value = JsonInclude.Include.NON_EMPTY)
    private RequestPresentationAttach[] requestPresentationAttach;

    @JsonProperty("~service")
    @JsonInclude(value = JsonInclude.Include.NON_EMPTY)
    private ProofRequestService service;

    @JsonProperty("~thread")
    @JsonInclude(value = JsonInclude.Include.NON_EMPTY)
    private ProofRequestThread thread;

    public ConnectionlessProofRequest() {}

    public void setType(String type) {
        this.type = type;
    }

    public void setId(String id) {
        this.id = id;
    }

    public void setRequestPresentationAttach(RequestPresentationAttach[] requestPresentationAttach) {
        this.requestPresentationAttach = requestPresentationAttach;
    }

    public void setService(ProofRequestService service) {
        this.service = service;
    }

    public void setThread(ProofRequestThread thread) {
        this.thread = thread;
    }
}
