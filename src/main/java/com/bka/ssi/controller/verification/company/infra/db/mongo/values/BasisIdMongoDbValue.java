package com.bka.ssi.controller.verification.company.infra.db.mongo.values;

import org.springframework.data.mongodb.core.mapping.Field;

public class BasisIdMongoDbValue {

    @Field("firstName")
    private String firstName;

    @Field("familyName")
    private String familyName;

    @Field("dateOfBirth")
    private String dateOfBirth;

    @Field("dateOfExpiry")
    private String dateOfExpiry;

    public BasisIdMongoDbValue() {
    }

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
