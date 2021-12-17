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

package com.bka.ssi.controller.verification.company.testutilities;

import com.bka.ssi.controller.verification.company.services.models.credentials.GuestCredential;

import java.time.ZonedDateTime;

public class GuestCredentialBuilder {

    public String firstName;
    public String lastName;
    public String title;
    public String email;
    public String primaryPhoneNumber;
    public String secondaryPhoneNumber;
    public String companyName;
    public String typeOfVisit;
    public String location;
    public ZonedDateTime validFrom;
    public ZonedDateTime validUntil;
    public String invitedBy;
    public String dateOfBirth;
    public String licensePlateNumber;
    public String companyCity;
    public String companyStreet;
    public String companyPostCode;
    public String referenceBasisId;
    public String referenceEmployeeId;
    public String companyProofOfOwnership;
    public String dataEncryptionAlgorithm;

    public GuestCredentialBuilder() {
    }

    public void reset() {
        this.firstName = null;
        this.lastName = null;
        this.title = null;
        this.email = null;
        this.primaryPhoneNumber = null;
        this.secondaryPhoneNumber = null;
        this.companyName = null;
        this.typeOfVisit = null;
        this.location = null;
        this.validFrom = null;
        this.validUntil = null;
        this.invitedBy = null;
        this.dateOfBirth = null;
        this.licensePlateNumber = null;
        this.companyCity = null;
        this.companyStreet = null;
        this.companyPostCode = null;
        this.referenceBasisId = null;
        this.referenceEmployeeId = null;
        this.companyProofOfOwnership = null;
        this.dataEncryptionAlgorithm = null;
    }

    public GuestCredential buildCredential() {
        this.firstName = this.firstName == null ? "Erika" : this.firstName;
        this.lastName = this.lastName == null ? "Mustermann" : this.lastName;
        this.title = this.title == null ? "Mrs." : this.title;
        this.email = this.email == null ? "mustermann@test.tld" : this.email;
        this.primaryPhoneNumber =
            this.primaryPhoneNumber == null ? "0123456789" : this.primaryPhoneNumber;
        this.secondaryPhoneNumber =
            this.secondaryPhoneNumber == null ? "9876543210" : this.secondaryPhoneNumber;
        this.companyName = this.companyName == null ? "Test Company" : this.companyName;
        this.typeOfVisit = this.typeOfVisit == null ? "Test Visit" : this.typeOfVisit;
        this.location = this.location == null ? "Test location" : this.location;
        this.validFrom = this.validFrom == null ? ZonedDateTime.now() : this.validFrom;
        this.validUntil =
            this.validUntil == null ? ZonedDateTime.now().plusHours(1) : this.validUntil;
        this.invitedBy = this.invitedBy == null ? "unittest" : this.invitedBy;
        this.dateOfBirth = this.dateOfBirth == null ? "1970-01-01" : this.dateOfBirth;
        this.licensePlateNumber =
            this.licensePlateNumber == null ? "licencePlateNumber" : this.licensePlateNumber;
        this.companyCity = this.companyCity == null ? "companyCity" : this.companyCity;
        this.companyStreet = this.companyStreet == null ? "companyStreet" : this.companyStreet;
        this.companyPostCode =
            this.companyPostCode == null ? "companyPostCode" : this.companyPostCode;
        this.referenceBasisId =
            this.referenceBasisId == null ? "referenceBasisId" : this.referenceBasisId;
        this.referenceEmployeeId =
            this.referenceEmployeeId == null ? "referenceEmployeeId" : this.referenceEmployeeId;
        this.companyProofOfOwnership =
            this.companyProofOfOwnership == null ? "companyProofOfOwnership" :
                this.companyProofOfOwnership;
        this.dataEncryptionAlgorithm =
            this.dataEncryptionAlgorithm == null ? "dataEncryptionAlgorithm" :
                this.dataEncryptionAlgorithm;

        return build();
    }

    private GuestCredential build() {
        return new GuestCredential(
            firstName,
            lastName,
            title,
            email,
            primaryPhoneNumber,
            secondaryPhoneNumber,
            companyName,
            typeOfVisit,
            location,
            validFrom,
            validUntil,
            invitedBy,
            dateOfBirth,
            licensePlateNumber,
            companyCity,
            companyStreet,
            companyPostCode,
            referenceBasisId,
            referenceEmployeeId,
            companyProofOfOwnership,
            dataEncryptionAlgorithm
        );
    }
}
