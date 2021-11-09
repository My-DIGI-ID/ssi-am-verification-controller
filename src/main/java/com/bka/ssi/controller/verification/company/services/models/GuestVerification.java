package com.bka.ssi.controller.verification.company.services.models;

import com.bka.ssi.controller.verification.company.services.enums.GuestVerificationStatus;
import com.bka.ssi.controller.verification.company.services.models.common.BasisId;
import com.bka.ssi.controller.verification.company.services.models.credentials.GuestCredential;

import java.time.ZonedDateTime;

public class GuestVerification extends Verification<GuestVerificationStatus> {

    private String accreditationId;
    private BasisId basisId;
    private GuestCredential guestCredential;
    private ZonedDateTime checkOutDateTime;
    private ZonedDateTime checkInDateTime;

    public GuestVerification(String id, String accreditationId) {
        super(id);
        this.accreditationId = accreditationId;
        this.state = GuestVerificationStatus.PENDING;
    }

    public GuestVerification(String accreditationId) {
        super(null);
        this.accreditationId = accreditationId;
        this.state = GuestVerificationStatus.PENDING;
    }

    public GuestVerification(String threadId, String locationId, String terminalId) {
        super(null, locationId, terminalId, threadId, threadId);
        this.state = GuestVerificationStatus.PENDING;
    }

    public GuestVerification(String id, String accreditationId, String locationId,
        String terminalId, String threadId, String proofId,
        BasisId basisId,
        GuestCredential guestCredential, ZonedDateTime checkOutDateTime,
        ZonedDateTime checkInDateTime,
        GuestVerificationStatus state, String proofState) {
        super(id, locationId, terminalId, threadId, proofId, state, proofState);
        this.accreditationId = accreditationId;
        this.basisId = basisId;
        this.guestCredential = guestCredential;
        this.checkOutDateTime = checkOutDateTime;
        this.checkInDateTime = checkInDateTime;
    }

    public String getAccreditationId() {
        return accreditationId;
    }

    public void setAccreditationId(String accreditationId) {
        this.accreditationId = accreditationId;
    }

    public BasisId getBasisId() {
        return basisId;
    }

    public void setBasisId(BasisId basisId) {
        this.basisId = basisId;
    }

    public GuestCredential getGuestCredential() {
        return guestCredential;
    }

    public void setGuestCredential(GuestCredential guestCredential) {
        this.guestCredential = guestCredential;
    }

    public ZonedDateTime getCheckOutDateTime() {
        return checkOutDateTime;
    }

    public void setCheckOutDateTime(ZonedDateTime checkOutDateTime) {
        this.checkOutDateTime = checkOutDateTime;
    }

    public ZonedDateTime getCheckInDateTime() {
        return checkInDateTime;
    }

    public void setCheckInDateTime(ZonedDateTime checkInDateTime) {
        this.checkInDateTime = checkInDateTime;
    }


    @Override
    public String toString() {
        return "Verification{" +
            "accreditationId='" + accreditationId + '\'' +
            ", locationId='" + getLocationId() + '\'' +
            ", terminalId='" + getTerminalId() + '\'' +
            ", threadId='" + getThreadId() + '\'' +
            ", proofId='" + getProofId() + '\'' +
            ", basisId=" + basisId +
            ", guestCredential=" + guestCredential +
            ", checkOutDateTime=" + checkOutDateTime +
            ", checkInDateTime=" + checkInDateTime +
            ", state=" + getState() +
            ", proofState='" + getProofState() + '\'' +
            '}';
    }
}
