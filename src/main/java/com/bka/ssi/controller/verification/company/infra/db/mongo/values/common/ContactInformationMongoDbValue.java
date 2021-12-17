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

package com.bka.ssi.controller.verification.company.infra.db.mongo.values.common;

import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;

/**
 * The type Contact information mongo db value.
 */
public class ContactInformationMongoDbValue {

    @Field("emails")
    private List<String> emails;

    @Field("phoneNumbers")
    private List<String> phoneNumbers;

    /**
     * Instantiates a new Contact information mongo db value.
     */
    public ContactInformationMongoDbValue() {
    }

    /**
     * Gets emails.
     *
     * @return the emails
     */
    public List<String> getEmails() {
        return emails;
    }

    /**
     * Sets emails.
     *
     * @param emails the emails
     */
    public void setEmails(List<String> emails) {
        this.emails = emails;
    }

    /**
     * Gets phone numbers.
     *
     * @return the phone numbers
     */
    public List<String> getPhoneNumbers() {
        return phoneNumbers;
    }

    /**
     * Sets phone numbers.
     *
     * @param phoneNumbers the phone numbers
     */
    public void setPhoneNumbers(List<String> phoneNumbers) {
        this.phoneNumbers = phoneNumbers;
    }
}
