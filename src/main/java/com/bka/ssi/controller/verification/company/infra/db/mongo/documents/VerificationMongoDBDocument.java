package com.bka.ssi.controller.verification.company.infra.db.mongo.documents;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import com.bka.ssi.controller.verification.company.infra.db.mongo.values.BasisIdDbValue;
import com.bka.ssi.controller.verification.company.infra.db.mongo.values.GuestCredentialDbValue;
import com.bka.ssi.controller.verification.company.services.enums.VerificationState;

@Document(collection = "verifications")
public class VerificationMongoDBDocument {

    @Id
    private String id;

    @Field("proofId")
    private String proofId;

    @Field("accreditationId")
    private String accreditationId;

    @Field("locationId")
    private String locationId;

    @Field("terminalId")
    private String terminalId;

    @Field("threadId")
    private String threadId;
    
    @Field("basisId")
    private BasisIdDbValue basisIdDbValue;
    
    @Field("guestCredential")
    private GuestCredentialDbValue guestCredentialDbValue;
    
    @Field("checkOutDateTime")
    private Date checkOutDateTime;
    
    @Field("checkInDateTime")
    private Date checkInDateTime;
    
    @Field("state")
    private VerificationState state;
    
    @Field("proofState")
    private String proofState;

    public VerificationMongoDBDocument() {
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

    public String getAccreditationId() { return accreditationId; }

    public void setAccreditationId(String accreditationId) { this.accreditationId =
        accreditationId; }

    public String getLocationId() { return locationId; }

    public void setLocationId(String locationId) { this.locationId = locationId; }

    public String getTerminalId() { return terminalId; }

    public void setTerminalId(String terminalId) { this.terminalId = terminalId; }

    public String getThreadId() { return threadId; }

    public void setThreadId(String threadId) { this.threadId = threadId; }

	public String getProofState() {
		return proofState;
	}

	public void setProofState(String proofState) {
		this.proofState = proofState;
	}

	public BasisIdDbValue getBasisIdDbValue() {
		return basisIdDbValue;
	}

	public void setBasisIdDbValue(BasisIdDbValue basisIdDbValue) {
		this.basisIdDbValue = basisIdDbValue;
	}

	public GuestCredentialDbValue getGuestCredentialDbValue() {
		return guestCredentialDbValue;
	}

	public void setGuestCredentialDbValue(GuestCredentialDbValue guestCredentialDbValue) {
		this.guestCredentialDbValue = guestCredentialDbValue;
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
}
