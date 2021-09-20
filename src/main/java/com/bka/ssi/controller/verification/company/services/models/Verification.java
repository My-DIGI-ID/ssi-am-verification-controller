package com.bka.ssi.controller.verification.company.services.models;

import java.util.Date;

import com.bka.ssi.controller.verification.company.services.enums.VerificationState;
import com.bka.ssi.controller.verification.company.services.models.abstractions.Record;
import com.bka.ssi.controller.verification.company.services.models.common.BasisId;
import com.bka.ssi.controller.verification.company.services.models.common.GuestCredential;

public class Verification extends Record {

    private String accreditationId;
    private String locationId;
    private String terminalId;
    private String threadId;
    private String proofId;
    
    private BasisId basisId;
    private GuestCredential guestCredential;
    private Date checkOutDateTime;
    private Date checkInDateTime;
    private VerificationState state;
    private String proofState;
    /* TODO - Add proof request attributes */
    /* TODO - Add proof request predicates */
    /* TODO - Add flag for verified proof */
    /* TODO - May need to add identifier to entity */

    public Verification(String id, String accreditationId) {
        super(id);
        this.accreditationId = accreditationId;
    }

    public Verification( String accreditationId) {
        super(null);
        this.accreditationId = accreditationId;
    }

    public Verification(String threadId, String locationId, String terminalId) {
        super(null);
        this.proofId = threadId;
        this.threadId = threadId;
        this.locationId = locationId;
        this.terminalId = terminalId;
    }

    public String getAccreditationId() {
        return accreditationId;
    }

    public void setAccreditationId(String accreditationId) {
        this.accreditationId = accreditationId;
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

	public Date getCheckOutDateTime() {
		return checkOutDateTime;
	}

	public void setCheckOutDateTime(Date checkOutDateTime) {
		this.checkOutDateTime = checkOutDateTime;
	}

	public Date getCheckInDateTime() {
		return checkInDateTime;
	}

	public void setCheckInDateTime(Date checkInDateTime) {
		this.checkInDateTime = checkInDateTime;
	}

	public VerificationState getState() {
		return state;
	}

	public void setState(VerificationState state) {
		this.state = state;
	}

	public String getProofState() {
		return proofState;
	}

	public void setProofState(String proofState) {
		this.proofState = proofState;
	}

    @Override
    public String toString() {
        return "Verification{" +
            "accreditationId='" + accreditationId + '\'' +
            ", locationId='" + locationId + '\'' +
            ", terminalId='" + terminalId + '\'' +
            ", threadId='" + threadId + '\'' +
            ", proofId='" + proofId + '\'' +
            ", basisId=" + basisId +
            ", guestCredential=" + guestCredential +
            ", checkOutDateTime=" + checkOutDateTime +
            ", checkInDateTime=" + checkInDateTime +
            ", state=" + state +
            ", proofState='" + proofState + '\'' +
            '}';
    }
}
