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

package com.bka.ssi.controller.verification.company.services.models;

import com.bka.ssi.controller.verification.company.services.enums.EmployeeVerificationStatus;
import com.bka.ssi.controller.verification.company.services.models.credentials.EmployeeCredential;

import java.time.ZonedDateTime;

/**
 * The type Employee verification.
 */
public class EmployeeVerification extends Verification<EmployeeVerificationStatus> {

    private EmployeeCredential employeeCredential;
    // TODO: Shift checkIn checkOut to abstract Verification class
    private ZonedDateTime checkInDateTime;

    /**
     * Instantiates a new Employee verification.
     *
     * @param id the id
     */
    public EmployeeVerification(String id) {
        super(id);
        this.state = EmployeeVerificationStatus.PENDING;
    }

    /**
     * Instantiates a new Employee verification.
     *
     * @param threadId   the thread id
     * @param locationId the location id
     * @param terminalId the terminal id
     */
    public EmployeeVerification(String threadId, String locationId, String terminalId) {
        super(null, locationId, terminalId, threadId, threadId);
        this.state = EmployeeVerificationStatus.PENDING;
    }

    /**
     * Instantiates a new Employee verification.
     *
     * @param id         the id
     * @param threadId   the thread id
     * @param locationId the location id
     * @param terminalId the terminal id
     */
    public EmployeeVerification(String id, String threadId, String locationId, String terminalId) {
        super(id, locationId, terminalId, threadId, threadId);
        this.state = EmployeeVerificationStatus.PENDING;
    }

    /**
     * Gets employee credential.
     *
     * @return the employee credential
     */
    public EmployeeCredential getEmployeeCredential() {
        return employeeCredential;
    }

    /**
     * Sets employee credential.
     *
     * @param employeeCredential the employee credential
     */
    public void setEmployeeCredential(
        EmployeeCredential employeeCredential) {
        this.employeeCredential = employeeCredential;
    }

    public ZonedDateTime getCheckInDateTime() {
        return checkInDateTime;
    }

    public void setCheckInDateTime(ZonedDateTime checkInDateTime) {
        this.checkInDateTime = checkInDateTime;
    }

    public void checkIn() {
        this.checkInDateTime = ZonedDateTime.now();
        this.state = EmployeeVerificationStatus.VERIFIED;
    }
}
