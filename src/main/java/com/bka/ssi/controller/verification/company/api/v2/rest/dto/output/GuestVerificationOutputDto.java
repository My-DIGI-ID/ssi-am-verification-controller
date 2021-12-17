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

package com.bka.ssi.controller.verification.company.api.v2.rest.dto.output;

import com.bka.ssi.controller.verification.company.services.enums.GuestVerificationStatus;
import java.time.ZonedDateTime;

/**
 * The type Guest verification output dto.
 */
public class GuestVerificationOutputDto {

    private String id;
    private BasisIdOutputDto basisId;
    private GuestCredentialOutputDto guestCredential;
    private ZonedDateTime checkOutDateTime;
    private ZonedDateTime checkInDateTime;
    private GuestVerificationStatus state;

    /**
     * Gets id.
     *
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * Sets id.
     *
     * @param id the id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Gets basis id.
     *
     * @return the basis id
     */
    public BasisIdOutputDto getBasisId() {
        return basisId;
    }

    /**
     * Sets basis id.
     *
     * @param basisId the basis id
     */
    public void setBasisId(BasisIdOutputDto basisId) {
        this.basisId = basisId;
    }

    /**
     * Gets guest credential.
     *
     * @return the guest credential
     */
    public GuestCredentialOutputDto getGuestCredential() {
        return guestCredential;
    }

    /**
     * Sets guest credential.
     *
     * @param guestCredential the guest credential
     */
    public void setGuestCredential(GuestCredentialOutputDto guestCredential) {
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
     * Sets check out date time.
     *
     * @param checkOutDateTime the check out date time
     */
    public void setCheckOutDateTime(ZonedDateTime checkOutDateTime) {
        this.checkOutDateTime = checkOutDateTime;
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
     * Gets state.
     *
     * @return the state
     */
    public GuestVerificationStatus getState() {
        return state;
    }

    /**
     * Sets state.
     *
     * @param state the state
     */
    public void setState(GuestVerificationStatus state) {
        this.state = state;
    }
}