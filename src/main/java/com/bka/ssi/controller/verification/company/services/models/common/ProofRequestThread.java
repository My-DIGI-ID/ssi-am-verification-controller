package com.bka.ssi.controller.verification.company.services.models.common;

import com.fasterxml.jackson.annotation.JsonProperty;

public class ProofRequestThread {

    @JsonProperty("thid")
    private String threadId;

    @JsonProperty("sender_order")
    private Integer senderOrder = 0;

    @JsonProperty("received_orders")
    private EmptyDTO emptyDTO;

    public ProofRequestThread() {}

    public void setThreadId(String threadId) {
        this.threadId = threadId;
    }

    public void setSenderOrder(Integer senderOrder) {
        this.senderOrder = senderOrder;
    }

    public void setReceivedOrders(EmptyDTO empty) {
        this.emptyDTO = empty;
    }
}
