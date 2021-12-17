/*
 * Copyright 2021 Bundesrepublik Deutschland
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.bka.ssi.controller.verification.company.services.models;

import com.bka.ssi.controller.verification.company.services.enums.GuestVerificationStatus;
import com.bka.ssi.controller.verification.company.services.models.common.BasisId;
import com.bka.ssi.controller.verification.company.services.models.credentials.GuestCredential;

import java.time.ZonedDateTime;

/**
 * The type Guest verification.
 */
public class GuestVerification extends Verification<GuestVerificationStatus> {

    private String accreditationId;
    private BasisId basisId;
    private GuestCredential guestCredential;
    private ZonedDateTime checkOutDateTime;
    private ZonedDateTime checkInDateTime;

    /**
     * Instantiates a new Guest verification.
     *
     * @param locationId the location id
     * @param terminalId the terminal id
     */
    public GuestVerification(String locationId, String terminalId) {
        super(locationId, terminalId);
        this.state = GuestVerificationStatus.PENDING;
    }

    /**
     * Instantiates a new Guest verification.
     *
     * @param accreditationId the accreditation id
     */
    public GuestVerification(String accreditationId) {
        super(null);
        this.accreditationId = accreditationId;
        this.state = GuestVerificationStatus.PENDING;
    }

    /**
     * Instantiates a new Guest verification.
     *
     * @param threadId   the thread id
     * @param locationId the location id
     * @param terminalId the terminal id
     */
    public GuestVerification(String threadId, String locationId, String terminalId) {
        super(null, locationId, terminalId, threadId, threadId);
        this.state = GuestVerificationStatus.PENDING;
    }

    /**
     * Instantiates a new Guest verification.
     *
     * @param id               the id
     * @param accreditationId  the accreditation id
     * @param locationId       the location id
     * @param terminalId       the terminal id
     * @param threadId         the thread id
     * @param proofId          the proof id
     * @param basisId          the basis id
     * @param guestCredential  the guest credential
     * @param checkInDateTime  the check in date time
     * @param checkOutDateTime the check out date time
     * @param state            the state
     * @param proofState       the proof state
     */
    public GuestVerification(String id, String accreditationId, String locationId,
        String terminalId, String threadId, String proofId,
        BasisId basisId,
        GuestCredential guestCredential, ZonedDateTime checkInDateTime,
        ZonedDateTime checkOutDateTime,
        GuestVerificationStatus state, String proofState) {
        super(id, locationId, terminalId, threadId, proofId, state, proofState);
        this.accreditationId = accreditationId;
        this.basisId = basisId;
        this.guestCredential = guestCredential;
        this.checkOutDateTime = checkOutDateTime;
        this.checkInDateTime = checkInDateTime;
    }

    /**
     * Gets accreditation id.
     *
     * @return the accreditation id
     */
    public String getAccreditationId() {
        return accreditationId;
    }

    /**
     * Sets accreditation id.
     *
     * @param accreditationId the accreditation id
     */
    public void setAccreditationId(String accreditationId) {
        this.accreditationId = accreditationId;
    }

    /**
     * Gets basis id.
     *
     * @return the basis id
     */
    public BasisId getBasisId() {
        return basisId;
    }

    /**
     * Sets basis id.
     *
     * @param basisId the basis id
     */
    public void setBasisId(BasisId basisId) {
        this.basisId = basisId;
    }

    /**
     * Gets guest credential.
     *
     * @return the guest credential
     */
    public GuestCredential getGuestCredential() {
        return guestCredential;
    }

    /**
     * Sets guest credential.
     *
     * @param guestCredential the guest credential
     */
    public void setGuestCredential(GuestCredential guestCredential) {
        this.guestCredential = guestCredential;
    }

    /**
     * Gets check out date time.
     *
     * @return the check out date time
     */
    public ZonedDateTime getCheckOutDateTime() {
        return checkOutDateTime;
    }

    /**
     * Gets check in date time.
     *
     * @return the check in date time
     */
    public ZonedDateTime getCheckInDateTime() {
        return checkInDateTime;
    }

    /**
     * Sets check in date time.
     *
     * @param checkInDateTime the check in date time
     */
    public void setCheckInDateTime(ZonedDateTime checkInDateTime) {
        this.checkInDateTime = checkInDateTime;
    }

    /**
     * Check in.
     */
    public void checkIn() {
        this.checkInDateTime = ZonedDateTime.now();
        this.state = GuestVerificationStatus.CHECK_IN;
    }

    /**
     * Check out.
     */
    public void checkOut() {
        this.checkOutDateTime = ZonedDateTime.now();
        this.state = GuestVerificationStatus.CHECK_OUT;
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
