package com.bka.ssi.controller.verification.company.api.v2.rest.dto.output;

import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

public class GuestCredentialOutputDto {

    @Size(max = 50)
    @NotNull
    private String firstName;

    @Size(max = 100)
    @NotNull
    private String lastName;

    @Size(max = 50)
    @NotNull
    private String title;

    @Size(max = 100)
    @NotNull
    private String email;

    @Size(max = 50)
    @NotNull
    private String primaryPhoneNumber;

    @Size(max = 50)
    @NotNull
    private String secondaryPhoneNumber;

    @Size(max = 50)
    @NotNull
    private String companyName;

    @Size(max = 50)
    @NotNull
    private String typeOfVisit;

    @Size(max = 50)
    @NotNull
    private String location;

    @NotNull
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private Date validFromDate;

    @NotNull
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private Date validFromTime;

    @NotNull
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    private Date validUntilDate;

    @NotNull
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private Date validUntilTime;

    @Size(max = 50)
    @NotNull
    private String invitedBy;

    @Size(max = 50)
    @NotNull
    private String dateOfBirth;

    @Size(max = 50)
    @NotNull
    private String licensePlateNumber;

    @Size(max = 50)
    @NotNull
    private String companyCity;

    @Size(max = 50)
    @NotNull
    private String companyStreet;

    @Size(max = 50)
    @NotNull
    private String companyPostCode;

    @Size(max = 50)
    @NotNull
    private String referenceBasisId;

    @Size(max = 50)
    @NotNull
    private String referenceEmployeeId;

    @Size(max = 50)
    @NotNull
    private String companyProofOfOwnership;

    @Size(max = 50)
    @NotNull
    private String dataEncryptionAlgorithm;

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

    public String getTitel() {
        return title;
    }

    public void setTitel(String titel) {
        this.title = titel;
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

    public Date getValidFromDate() {
        return validFromDate;
    }

    public void setValidFromDate(Date validFromDate) {
        this.validFromDate = validFromDate;
    }

    public Date getValidFromTime() {
        return validFromTime;
    }

    public void setValidFromTime(Date validFromTime) {
        this.validFromTime = validFromTime;
    }

    public Date getValidUntilDate() {
        return validUntilDate;
    }

    public void setValidUntilDate(Date validUntilDate) {
        this.validUntilDate = validUntilDate;
    }

    public Date getValidUntilTime() {
        return validUntilTime;
    }

    public void setValidUntilTime(Date validUntilTime) {
        this.validUntilTime = validUntilTime;
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
