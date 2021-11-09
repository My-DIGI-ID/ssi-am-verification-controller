package com.bka.ssi.controller.verification.company.api.v2.rest.dto.output;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class EmployeeCredentialOutputDto {

    @Size(max = 50)
    @NotNull
    private String firstName;

    @Size(max = 100)
    @NotNull
    private String lastName;

    @Size(max = 50)
    @NotNull
    private String title;

    @Size(max = 50)
    @NotNull
    private String primaryPhoneNumber;

    @Size(max = 50)
    @NotNull
    private String secondaryPhoneNumber;

    @Size(max = 100)
    @NotNull
    private String email;

    @Size(max = 50)
    @NotNull
    private String employeeState;

    @Size(max = 50)
    private String employeeId;

    @Size(max = 100)
    private String position;

    @Size(max = 200)
    @NotNull
    private String companyName;

    @Size(max = 50)
    @NotNull
    private String companyStreet;

    @Size(max = 50)
    private String companyCity;

    @Size(max = 50)
    @NotNull
    private String companyPostalCode;

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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getEmployeeState() {
        return employeeState;
    }

    public void setEmployeeState(String employeeState) {
        this.employeeState = employeeState;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCompanyStreet() {
        return companyStreet;
    }

    public void setCompanyStreet(String companyStreet) {
        this.companyStreet = companyStreet;
    }

    public String getCompanyCity() {
        return companyCity;
    }

    public void setCompanyCity(String companyCity) {
        this.companyCity = companyCity;
    }

    public String getCompanyPostalCode() {
        return companyPostalCode;
    }

    public void setCompanyPostalCode(String companyPostalCode) {
        this.companyPostalCode = companyPostalCode;
    }
}
