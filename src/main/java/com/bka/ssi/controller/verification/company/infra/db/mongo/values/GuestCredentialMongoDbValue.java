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

package com.bka.ssi.controller.verification.company.infra.db.mongo.values;

import org.springframework.data.mongodb.core.mapping.Field;

import java.time.ZonedDateTime;

/**
 * The type Guest credential mongo db value.
 */
public class GuestCredentialMongoDbValue {

    @Field("firstName")
    private String firstName;

    @Field("lastName")
    private String lastName;

    @Field("title")
    private String title;

    @Field("email")
    private String email;

    @Field("primaryPhoneNumber")
    private String primaryPhoneNumber;

    @Field("secondaryPhoneNumber")
    private String secondaryPhoneNumber;

    @Field("companyName")
    private String companyName;

    @Field("typeOfVisit")
    private String typeOfVisit;

    @Field("location")
    private String location;

    @Field("validFrom")
    private ZonedDateTime validFrom;

    @Field("validUntil")
    private ZonedDateTime validUntil;

    @Field("invitedBy")
    private String invitedBy;

    @Field("dateOfBirth")
    private String dateOfBirth;

    @Field("licensePlateNumber")
    private String licensePlateNumber;

    @Field("companyCity")
    private String companyCity;

    @Field("companyStreet")
    private String companyStreet;

    @Field("companyPostCode")
    private String companyPostCode;

    @Field("referenceBasisId")
    private String referenceBasisId;

    @Field("referenceEmployeeId")
    private String referenceEmployeeId;

    @Field("companyProofOfOwnership")
    private String companyProofOfOwnership;

    @Field("dataEncryptionAlgorithm")
    private String dataEncryptionAlgorithm;

    /**
     * Instantiates a new Guest credential mongo db value.
     */
    public GuestCredentialMongoDbValue() {
    }

    /**
     * Gets first name.
     *
     * @return the first name
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Sets first name.
     *
     * @param firstName the first name
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Gets last name.
     *
     * @return the last name
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Sets last name.
     *
     * @param lastName the last name
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Gets title.
     *
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * Sets title.
     *
     * @param title the title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Gets email.
     *
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets email.
     *
     * @param email the email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Gets primary phone number.
     *
     * @return the primary phone number
     */
    public String getPrimaryPhoneNumber() {
        return primaryPhoneNumber;
    }

    /**
     * Sets primary phone number.
     *
     * @param primaryPhoneNumber the primary phone number
     */
    public void setPrimaryPhoneNumber(String primaryPhoneNumber) {
        this.primaryPhoneNumber = primaryPhoneNumber;
    }

    /**
     * Gets secondary phone number.
     *
     * @return the secondary phone number
     */
    public String getSecondaryPhoneNumber() {
        return secondaryPhoneNumber;
    }

    /**
     * Sets secondary phone number.
     *
     * @param secondaryPhoneNumber the secondary phone number
     */
    public void setSecondaryPhoneNumber(String secondaryPhoneNumber) {
        this.secondaryPhoneNumber = secondaryPhoneNumber;
    }

    /**
     * Gets company name.
     *
     * @return the company name
     */
    public String getCompanyName() {
        return companyName;
    }

    /**
     * Sets company name.
     *
     * @param companyName the company name
     */
    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    /**
     * Gets type of visit.
     *
     * @return the type of visit
     */
    public String getTypeOfVisit() {
        return typeOfVisit;
    }

    /**
     * Sets type of visit.
     *
     * @param typeOfVisit the type of visit
     */
    public void setTypeOfVisit(String typeOfVisit) {
        this.typeOfVisit = typeOfVisit;
    }

    /**
     * Gets location.
     *
     * @return the location
     */
    public String getLocation() {
        return location;
    }

    /**
     * Sets location.
     *
     * @param location the location
     */
    public void setLocation(String location) {
        this.location = location;
    }

    /**
     * Gets valid from.
     *
     * @return the valid from
     */
    public ZonedDateTime getValidFrom() {
        return validFrom;
    }

    /**
     * Sets valid from.
     *
     * @param validFrom the valid from
     */
    public void setValidFrom(ZonedDateTime validFrom) {
        this.validFrom = validFrom;
    }

    /**
     * Gets valid until.
     *
     * @return the valid until
     */
    public ZonedDateTime getValidUntil() {
        return validUntil;
    }

    /**
     * Sets valid until.
     *
     * @param validUntil the valid until
     */
    public void setValidUntil(ZonedDateTime validUntil) {
        this.validUntil = validUntil;
    }

    /**
     * Gets invited by.
     *
     * @return the invited by
     */
    public String getInvitedBy() {
        return invitedBy;
    }

    /**
     * Sets invited by.
     *
     * @param invitedBy the invited by
     */
    public void setInvitedBy(String invitedBy) {
        this.invitedBy = invitedBy;
    }

    /**
     * Gets date of birth.
     *
     * @return the date of birth
     */
    public String getDateOfBirth() {
        return dateOfBirth;
    }

    /**
     * Sets date of birth.
     *
     * @param dateOfBirth the date of birth
     */
    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    /**
     * Gets license plate number.
     *
     * @return the license plate number
     */
    public String getLicensePlateNumber() {
        return licensePlateNumber;
    }

    /**
     * Sets license plate number.
     *
     * @param licensePlateNumber the license plate number
     */
    public void setLicensePlateNumber(String licensePlateNumber) {
        this.licensePlateNumber = licensePlateNumber;
    }

    /**
     * Gets company city.
     *
     * @return the company city
     */
    public String getCompanyCity() {
        return companyCity;
    }

    /**
     * Sets company city.
     *
     * @param companyCity the company city
     */
    public void setCompanyCity(String companyCity) {
        this.companyCity = companyCity;
    }

    /**
     * Gets company street.
     *
     * @return the company street
     */
    public String getCompanyStreet() {
        return companyStreet;
    }

    /**
     * Sets company street.
     *
     * @param companyStreet the company street
     */
    public void setCompanyStreet(String companyStreet) {
        this.companyStreet = companyStreet;
    }

    /**
     * Gets company post code.
     *
     * @return the company post code
     */
    public String getCompanyPostCode() {
        return companyPostCode;
    }

    /**
     * Sets company post code.
     *
     * @param companyPostCode the company post code
     */
    public void setCompanyPostCode(String companyPostCode) {
        this.companyPostCode = companyPostCode;
    }

    /**
     * Gets reference basis id.
     *
     * @return the reference basis id
     */
    public String getReferenceBasisId() {
        return referenceBasisId;
    }

    /**
     * Sets reference basis id.
     *
     * @param referenceBasisId the reference basis id
     */
    public void setReferenceBasisId(String referenceBasisId) {
        this.referenceBasisId = referenceBasisId;
    }

    /**
     * Gets reference employee id.
     *
     * @return the reference employee id
     */
    public String getReferenceEmployeeId() {
        return referenceEmployeeId;
    }

    /**
     * Sets reference employee id.
     *
     * @param referenceEmployeeId the reference employee id
     */
    public void setReferenceEmployeeId(String referenceEmployeeId) {
        this.referenceEmployeeId = referenceEmployeeId;
    }

    /**
     * Gets company proof of ownership.
     *
     * @return the company proof of ownership
     */
    public String getCompanyProofOfOwnership() {
        return companyProofOfOwnership;
    }

    /**
     * Sets company proof of ownership.
     *
     * @param companyProofOfOwnership the company proof of ownership
     */
    public void setCompanyProofOfOwnership(String companyProofOfOwnership) {
        this.companyProofOfOwnership = companyProofOfOwnership;
    }

    /**
     * Gets data encryption algorithm.
     *
     * @return the data encryption algorithm
     */
    public String getDataEncryptionAlgorithm() {
        return dataEncryptionAlgorithm;
    }

    /**
     * Sets data encryption algorithm.
     *
     * @param dataEncryptionAlgorithm the data encryption algorithm
     */
    public void setDataEncryptionAlgorithm(String dataEncryptionAlgorithm) {
        this.dataEncryptionAlgorithm = dataEncryptionAlgorithm;
    }
}
