package com.bka.ssi.controller.verification.company.infra.db.mongo.values;

import java.util.Date;

import org.springframework.data.mongodb.core.mapping.Field;

public class GuestCredentialDbValue {

	@Field("firstName")
	private String firstName;
	
	@Field("lastName")
    private String lastName;
	
	@Field("titel")
    private String titel;
	
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
	
	@Field("validFromDate")
    private Date validFromDate;
	
	@Field("validFromTime")
    private Date validFromTime;
	
	@Field("validUntilDate")
    private Date validUntilDate;
	
	@Field("validUntilTime")
    private Date validUntilTime;
	
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
	
	public GuestCredentialDbValue() {
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

	public String getTitel() {
		return titel;
	}

	public void setTitel(String titel) {
		this.titel = titel;
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
