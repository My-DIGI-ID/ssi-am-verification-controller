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

package com.bka.ssi.controller.verification.company.services.enums;

/**
 * The enum Guest verification status.
 */
public enum GuestVerificationStatus implements VerificationStatus {
    /**
     * Pending guest verification status.
     */
    PENDING("PENDING"),
    /**
     * Check in guest verification status.
     */
    CHECK_IN("CHECK_IN"),
    /**
     * Check out guest verification status.
     */
    CHECK_OUT("CHECK_OUT"),
    /**
     * Fail date time guest verification status.
     */
    FAIL_DATE_TIME("FAIL_DATE_TIME"),
    /**
     * Fail verify credential guest verification status.
     */
    FAIL_VERIFY_CREDENTIAL("FAIL_VERIFY_CREDENTIAL"),
    /**
     * Fail location guest verification status.
     */
    FAIL_LOCATION("FAIL_LOCATION"),
    /**
     * Timeout guest verification status.
     */
    TIMEOUT("TIMEOUT");

    private final String name;

    GuestVerificationStatus(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }
}
