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

package com.bka.ssi.controller.verification.company.infra.db.mongo.documents;

import com.bka.ssi.controller.verification.company.infra.db.mongo.values.EmployeeCredentialMongoDbValue;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.ZonedDateTime;

/**
 * The type Employee verification mongo db document.
 */
@Document(collection = "employeeVerifications")
public class EmployeeVerificationMongoDbDocument extends VerificationMongoDbDocument {

    @Field("employeeCredential")
    private EmployeeCredentialMongoDbValue employeeCredentialMongoDbValue;

    @Field("checkInDateTime")
    private ZonedDateTime checkInDateTime;

    /**
     * Instantiates a new Employee verification mongo db document.
     */
    public EmployeeVerificationMongoDbDocument() {
    }

    /**
     * Gets employee credential mongo db value.
     *
     * @return the employee credential mongo db value
     */
    public EmployeeCredentialMongoDbValue getEmployeeCredentialMongoDbValue() {
        return employeeCredentialMongoDbValue;
    }

    /**
     * Sets employee credential mongo db value.
     *
     * @param employeeCredentialMongoDbValue the employee credential mongo db value
     */
    public void setEmployeeCredentialMongoDbValue(
        EmployeeCredentialMongoDbValue employeeCredentialMongoDbValue) {
        this.employeeCredentialMongoDbValue = employeeCredentialMongoDbValue;
    }

    public ZonedDateTime getCheckInDateTime() {
        return checkInDateTime;
    }

    public void setCheckInDateTime(ZonedDateTime checkInDateTime) {
        this.checkInDateTime = checkInDateTime;
    }
}
