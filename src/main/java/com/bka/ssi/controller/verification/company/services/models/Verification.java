package com.bka.ssi.controller.verification.company.services.models;

import com.bka.ssi.controller.verification.company.services.enums.VerificationStatus;
import com.bka.ssi.controller.verification.company.services.models.abstractions.Record;

public abstract class Verification<T extends VerificationStatus> extends Record {

    protected T state;

    private String locationId;
    private String terminalId;
    private String threadId;
    private String proofId;
    private String proofState;

    public Verification(String id) {
        super(id);
    }

    public Verification(String locationId, String terminalId) {
        super(null);
        this.locationId = locationId;
        this.terminalId = terminalId;
    }

    public Verification(String id, String locationId, String terminalId, String threadId,
        String proofId) {
        super(id);
        this.locationId = locationId;
        this.terminalId = terminalId;
        this.threadId = threadId;
        this.proofId = proofId;
    }

    public Verification(String id, String locationId, String terminalId, String threadId,
        String proofId,
        T state, String proofState) {
        super(id);
        this.locationId = locationId;
        this.terminalId = terminalId;
        this.threadId = threadId;
        this.proofId = proofId;
        this.state = state;
        this.proofState = proofState;
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

    public String getProofId() {
        return proofId;
    }

    public void setProofId(String proofId) {
        this.proofId = proofId;
    }

    public T getState() {
        return state;
    }

    public void setState(T state) {
        this.state = state;
    }

    public String getProofState() {
        return proofState;
    }

    public void setProofState(String proofState) {
        this.proofState = proofState;
    }
}
