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

package com.bka.ssi.controller.verification.company.infra.db.mongo.documents;

import com.bka.ssi.controller.verification.company.infra.db.mongo.values.BasisIdMongoDbValue;
import com.bka.ssi.controller.verification.company.infra.db.mongo.values.GuestCredentialMongoDbValue;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.ZonedDateTime;

/**
 * The type Guest verification mongo db document.
 */
@Document(collection = "guestVerifications")
public class GuestVerificationMongoDbDocument extends VerificationMongoDbDocument {

    @Field("accreditationId")
    private String accreditationId;

    @Field("basisId")
    private BasisIdMongoDbValue basisIdMongoDbValue;

    @Field("guestCredential")
    private GuestCredentialMongoDbValue guestCredentialMongoDbValue;

    @Field("checkOutDateTime")
    private ZonedDateTime checkOutDateTime;

    @Field("checkInDateTime")
    private ZonedDateTime checkInDateTime;

    /**
     * Instantiates a new Guest verification mongo db document.
     */
    public GuestVerificationMongoDbDocument() {
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
     * Gets basis id db value.
     *
     * @return the basis id db value
     */
    public BasisIdMongoDbValue getBasisIdDbValue() {
        return basisIdMongoDbValue;
    }

    /**
     * Sets basis id db value.
     *
     * @param basisIdMongoDbValue the basis id mongo db value
     */
    public void setBasisIdDbValue(BasisIdMongoDbValue basisIdMongoDbValue) {
        this.basisIdMongoDbValue = basisIdMongoDbValue;
    }

    /**
     * Gets guest credential db value.
     *
     * @return the guest credential db value
     */
    public GuestCredentialMongoDbValue getGuestCredentialDbValue() {
        return guestCredentialMongoDbValue;
    }

    /**
     * Sets guest credential db value.
     *
     * @param guestCredentialMongoDbValue the guest credential mongo db value
     */
    public void setGuestCredentialDbValue(GuestCredentialMongoDbValue guestCredentialMongoDbValue) {
        this.guestCredentialMongoDbValue = guestCredentialMongoDbValue;
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
}
