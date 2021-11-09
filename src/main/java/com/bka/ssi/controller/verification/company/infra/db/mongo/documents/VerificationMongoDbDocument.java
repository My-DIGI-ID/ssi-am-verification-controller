package com.bka.ssi.controller.verification.company.infra.db.mongo.documents;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Field;

public class VerificationMongoDbDocument {
    @Id
    private String id;

    @Field("proofId")
    private String proofId;

    @Field("locationId")
    private String locationId;

    @Field("terminalId")
    private String terminalId;

    @Field("threadId")
    private String threadId;

    @Field("state")
    private String state;

    @Field("proofState")
    private String proofState;

    public VerificationMongoDbDocument() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProofId() {
        return proofId;
    }

    public void setProofId(String proofId) {
        this.proofId = proofId;
    }

    public String getLocationId() {
        return locationId;
    }

    public void setLocationId(String locationId) {
        this.locationId = locationId;
    }

    public String getTerminalId() {
        return terminalId;
    }

    public void setTerminalId(String terminalId) {
        this.terminalId = terminalId;
    }

    public String getThreadId() {
        return threadId;
    }

    public void setThreadId(String threadId) {
        this.threadId = threadId;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getProofState() {
        return proofState;
    }

    public void setProofState(String proofState) {
        this.proofState = proofState;
    }
}
