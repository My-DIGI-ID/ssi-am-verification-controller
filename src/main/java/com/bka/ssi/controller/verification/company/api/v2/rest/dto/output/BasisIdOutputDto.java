package com.bka.ssi.controller.verification.company.api.v2.rest.dto.output;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class BasisIdOutputDto {

    @Size(max = 50)
    @NotNull
    private String firstName;

    @Size(max = 100)
    @NotNull
    private String familyName;

    @Size(max = 50)
    @NotNull
    private String dateOfBirth;

    @Size(max = 50)
    @NotNull
    private String dateOfExpiry;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getFamilyName() {
        return familyName;
    }

    public void setFamilyName(String familyName) {
        this.familyName = familyName;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getDateOfExpiry() {
        return dateOfExpiry;
    }

    public void setDateOfExpiry(String dateOfExpiry) {
        this.dateOfExpiry = dateOfExpiry;
    }
}
