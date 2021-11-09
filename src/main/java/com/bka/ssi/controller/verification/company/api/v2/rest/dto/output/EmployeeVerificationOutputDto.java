package com.bka.ssi.controller.verification.company.api.v2.rest.dto.output;

import com.bka.ssi.controller.verification.company.services.enums.EmployeeVerificationStatus;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class EmployeeVerificationOutputDto {
    @Size(max = 50)
    @NotEmpty
    @NotNull
    private String id;

    @NotEmpty
    @NotNull
    private EmployeeCredentialOutputDto employeeCredential;

    @NotEmpty
    @NotNull
    private EmployeeVerificationStatus state;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public EmployeeCredentialOutputDto getEmployeeCredential() {
        return employeeCredential;
    }

    public void setEmployeeCredential(
        EmployeeCredentialOutputDto employeeCredential) {
        this.employeeCredential = employeeCredential;
    }

    public EmployeeVerificationStatus getState() {
        return state;
    }

    public void setState(EmployeeVerificationStatus state) {
        this.state = state;
    }
}
