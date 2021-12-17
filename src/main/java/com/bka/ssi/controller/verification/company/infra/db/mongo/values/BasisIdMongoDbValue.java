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

/**
 * The type Basis id mongo db value.
 */
public class BasisIdMongoDbValue {

    @Field("firstName")
    private String firstName;

    @Field("familyName")
    private String familyName;

    @Field("dateOfBirth")
    private String dateOfBirth;

    @Field("dateOfExpiry")
    private String dateOfExpiry;

    /**
     * Instantiates a new Basis id mongo db value.
     */
    public BasisIdMongoDbValue() {
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
     * Gets family name.
     *
     * @return the family name
     */
    public String getFamilyName() {
        return familyName;
    }

    /**
     * Sets family name.
     *
     * @param familyName the family name
     */
    public void setFamilyName(String familyName) {
        this.familyName = familyName;
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
     * Gets date of expiry.
     *
     * @return the date of expiry
     */
    public String getDateOfExpiry() {
        return dateOfExpiry;
    }

    /**
     * Sets date of expiry.
     *
     * @param dateOfExpiry the date of expiry
     */
    public void setDateOfExpiry(String dateOfExpiry) {
        this.dateOfExpiry = dateOfExpiry;
    }
}
