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

package com.bka.ssi.controller.verification.company.api.v2.rest.dto.output;

import com.bka.ssi.controller.verification.company.services.enums.EmployeeVerificationStatus;

import java.time.ZonedDateTime;

/**
 * The type Employee verification output dto.
 */
public class EmployeeVerificationOutputDto {

    private String id;
    private EmployeeCredentialOutputDto employeeCredential;
    private EmployeeVerificationStatus state;
    private ZonedDateTime checkInDateTime;

    /**
     * Gets id.
     *
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * Sets id.
     *
     * @param id the id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Gets employee credential.
     *
     * @return the employee credential
     */
    public EmployeeCredentialOutputDto getEmployeeCredential() {
        return employeeCredential;
    }

    /**
     * Sets employee credential.
     *
     * @param employeeCredential the employee credential
     */
    public void setEmployeeCredential(
        EmployeeCredentialOutputDto employeeCredential) {
        this.employeeCredential = employeeCredential;
    }

    /**
     * Gets state.
     *
     * @return the state
     */
    public EmployeeVerificationStatus getState() {
        return state;
    }

    /**
     * Sets state.
     *
     * @param state the state
     */
    public void setState(EmployeeVerificationStatus state) {
        this.state = state;
    }

    public ZonedDateTime getCheckInDateTime() {
        return checkInDateTime;
    }

    public void setCheckInDateTime(ZonedDateTime checkInDateTime) {
        this.checkInDateTime = checkInDateTime;
    }
}
