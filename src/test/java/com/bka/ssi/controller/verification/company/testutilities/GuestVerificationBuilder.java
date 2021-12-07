package com.bka.ssi.controller.verification.company.testutilities;

import com.bka.ssi.controller.verification.company.services.enums.GuestVerificationStatus;
import com.bka.ssi.controller.verification.company.services.models.GuestVerification;
import com.bka.ssi.controller.verification.company.services.models.common.BasisId;
import com.bka.ssi.controller.verification.company.services.models.credentials.GuestCredential;

import java.time.ZonedDateTime;

public class GuestVerificationBuilder {

    public String id;

    public GuestVerificationStatus state;

    public String locationId;
    public String terminalId;
    public String threadId;
    public String proofId;
    public String proofState;

    public String accreditationId;
    public BasisId basisId;
    public GuestCredential guestCredential;
    public ZonedDateTime checkOutDateTime;
    public ZonedDateTime checkInDateTime;

    public GuestVerificationBuilder() {
    }

    public void reset() {
        this.id = null;
        this.state = null;
        this.locationId = null;
        this.terminalId = null;
        this.threadId = null;
        this.proofId = null;
        this.proofState = null;
        this.accreditationId = null;
        this.basisId = null;
        this.guestCredential = null;
        this.checkOutDateTime = null;
        this.checkInDateTime = null;
    }

    public GuestVerification buildPendingVerification() {
        this.id = this.id == null ? "6196830d1ce7a74cd5fecaee" : this.id;
        this.state = GuestVerificationStatus.PENDING;
        this.locationId = this.locationId == null ? "loc1" : this.locationId;
        this.terminalId = this.terminalId == null ? "term1" : this.terminalId;
        this.threadId = this.threadId == null ? "d1a1d389-6be6-4fb1-9a4d-dfb7ce5dde67" :
            this.threadId;
        this.proofId = this.proofId == null ? "d1a1d389-6be6-4fb1-9a4d-dfb7ce5dde67" : this.proofId;

        return build();
    }

    public GuestVerification buildCheckInVerification() {
        this.id = this.id == null ? "6196830d1ce7a74cd5fecaee" : this.id;
        this.state = GuestVerificationStatus.CHECK_IN;
        this.locationId = this.locationId == null ? "loc1" : this.locationId;
        this.terminalId = this.terminalId == null ? "term1" : this.terminalId;
        this.threadId = this.threadId == null ? "d1a1d389-6be6-4fb1-9a4d-dfb7ce5dde67" :
            this.threadId;
        this.proofId = this.proofId == null ? "d1a1d389-6be6-4fb1-9a4d-dfb7ce5dde67" : this.proofId;

        this.basisId = this.basisId == null ? new BasisId("Erika", "Mustermann", "1970-01-01",
            "2050-01-01") : this.basisId;

        this.guestCredential = this.guestCredential == null ?
            new GuestCredentialBuilder().buildCredential() : this.guestCredential;

        this.proofState = this.proofState == null ? "verified" : this.proofState;
        this.accreditationId =
            this.accreditationId == null ? "618546b38f217151f037ff52" : this.accreditationId;
        this.checkInDateTime = this.checkInDateTime == null ? ZonedDateTime.now() :
            this.checkInDateTime;

        return build();
    }

    private GuestVerification build() {
        return new GuestVerification(
            this.id,
            this.accreditationId,
            this.locationId,
            this.terminalId,
            this.threadId,
            this.proofId,
            this.basisId,
            this.guestCredential,
            this.checkInDateTime,
            this.checkOutDateTime,
            this.state,
            this.proofState);
    }
}
