package com.bka.ssi.controller.verification.company.services.models.credentials;

import java.time.ZonedDateTime;

public class GuestCredential {
    private String firstName;
    private String lastName;
    private String title;
    private String email;
    private String primaryPhoneNumber;
    private String secondaryPhoneNumber;
    private String companyName;
    private String typeOfVisit;
    private String location;
    private ZonedDateTime validFrom;
    private ZonedDateTime validUntil;
    private String invitedBy;
    private String dateOfBirth;
    private String licensePlateNumber;
    private String companyCity;
    private String companyStreet;
    private String companyPostCode;
    private String referenceBasisId;
    private String referenceEmployeeId;
    private String companyProofOfOwnership;
    private String dataEncryptionAlgorithm;

    public GuestCredential() {
    }

    public GuestCredential(String firstName, String lastName, String title, String email,
        String primaryPhoneNumber, String secondaryPhoneNumber,
        String companyName, String typeOfVisit, String location,
        ZonedDateTime validFrom, ZonedDateTime validUntil,
        String invitedBy, String dateOfBirth, String licensePlateNumber,
        String companyCity, String companyStreet, String companyPostCode,
        String referenceBasisId, String referenceEmployeeId,
        String companyProofOfOwnership, String dataEncryptionAlgorithm) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.title = title;
        this.email = email;
        this.primaryPhoneNumber = primaryPhoneNumber;
        this.secondaryPhoneNumber = secondaryPhoneNumber;
        this.companyName = companyName;
        this.typeOfVisit = typeOfVisit;
        this.location = location;
        this.validFrom = validFrom;
        this.validUntil = validUntil;
        this.invitedBy = invitedBy;
        this.dateOfBirth = dateOfBirth;
        this.licensePlateNumber = licensePlateNumber;
        this.companyCity = companyCity;
        this.companyStreet = companyStreet;
        this.companyPostCode = companyPostCode;
        this.referenceBasisId = referenceBasisId;
        this.referenceEmployeeId = referenceEmployeeId;
        this.companyProofOfOwnership = companyProofOfOwnership;
        this.dataEncryptionAlgorithm = dataEncryptionAlgorithm;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPrimaryPhoneNumber() {
        return primaryPhoneNumber;
    }

    public void setPrimaryPhoneNumber(String primaryPhoneNumber) {
        this.primaryPhoneNumber = primaryPhoneNumber;
    }

    public String getSecondaryPhoneNumber() {
        return secondaryPhoneNumber;
    }

    public void setSecondaryPhoneNumber(String secondaryPhoneNumber) {
        this.secondaryPhoneNumber = secondaryPhoneNumber;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getTypeOfVisit() {
        return typeOfVisit;
    }

    public void setTypeOfVisit(String typeOfVisit) {
        this.typeOfVisit = typeOfVisit;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public ZonedDateTime getValidFrom() {
        return validFrom;
    }

    public void setValidFrom(ZonedDateTime validFrom) {
        this.validFrom = validFrom;
    }

    public ZonedDateTime getValidUntil() {
        return validUntil;
    }

    public void setValidUntil(ZonedDateTime validUntil) {
        this.validUntil = validUntil;
    }

    public String getInvitedBy() {
        return invitedBy;
    }

    public void setInvitedBy(String invitedBy) {
        this.invitedBy = invitedBy;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getLicensePlateNumber() {
        return licensePlateNumber;
    }

    public void setLicensePlateNumber(String licensePlateNumber) {
        this.licensePlateNumber = licensePlateNumber;
    }

    public String getCompanyCity() {
        return companyCity;
    }

    public void setCompanyCity(String companyCity) {
        this.companyCity = companyCity;
    }

    public String getCompanyStreet() {
        return companyStreet;
    }

    public void setCompanyStreet(String companyStreet) {
        this.companyStreet = companyStreet;
    }

    public String getCompanyPostCode() {
        return companyPostCode;
    }

    public void setCompanyPostCode(String companyPostCode) {
        this.companyPostCode = companyPostCode;
    }

    public String getReferenceBasisId() {
        return referenceBasisId;
    }

    public void setReferenceBasisId(String referenceBasisId) {
        this.referenceBasisId = referenceBasisId;
    }

    public String getReferenceEmployeeId() {
        return referenceEmployeeId;
    }

    public void setReferenceEmployeeId(String referenceEmployeeId) {
        this.referenceEmployeeId = referenceEmployeeId;
    }

    public String getCompanyProofOfOwnership() {
        return companyProofOfOwnership;
    }

    public void setCompanyProofOfOwnership(String companyProofOfOwnership) {
        this.companyProofOfOwnership = companyProofOfOwnership;
    }

    public String getDataEncryptionAlgorithm() {
        return dataEncryptionAlgorithm;
    }

    public void setDataEncryptionAlgorithm(String dataEncryptionAlgorithm) {
        this.dataEncryptionAlgorithm = dataEncryptionAlgorithm;
    }
}
