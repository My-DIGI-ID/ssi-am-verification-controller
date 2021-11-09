package com.bka.ssi.controller.verification.company.services.system.accreditation.dto.output;

import org.springframework.format.annotation.DateTimeFormat;

import java.time.ZonedDateTime;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class GuestOpenOutputDto {

    @Size(max = 50)
    @NotEmpty
    @NotNull
    private String id;

    @Size(max = 50)
    private String title;

    @Size(max = 50)
    @NotNull
    @NotEmpty
    private String firstName;

    @Size(max = 50)
    @NotNull
    @NotEmpty
    private String lastName;

    @Email
    @NotNull
    @NotEmpty
    private String email;

    @Size(max = 50)
    private String primaryPhoneNumber;

    @Size(max = 50)
    private String secondaryPhoneNumber;

    @Size(max = 100)
    @NotNull
    private String companyName;

    @Size(max = 50)
    @NotNull
    private String typeOfVisit;

    @Size(max = 50)
    @NotNull
    private String location;

    @NotNull
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private ZonedDateTime validFrom;

    @NotNull
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private ZonedDateTime validUntil;

    @Size(max = 50)
    private String issuedBy;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getIssuedBy() {
        return issuedBy;
    }

    public void setIssuedBy(String issuedBy) {
        this.issuedBy = issuedBy;
    }

}

