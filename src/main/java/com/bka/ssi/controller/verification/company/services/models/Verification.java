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

import com.bka.ssi.controller.verification.company.services.enums.VerificationStatus;
import com.bka.ssi.controller.verification.company.services.models.abstractions.Record;

/**
 * The type Verification.
 *
 * @param <T> the type parameter
 */
public abstract class Verification<T extends VerificationStatus> extends Record {

    /**
     * The State.
     */
    protected T state;

    private String locationId;
    private String terminalId;
    private String threadId;
    private String proofId;
    private String proofState;

    /**
     * Instantiates a new Verification.
     *
     * @param id the id
     */
    public Verification(String id) {
        super(id);
    }

    /**
     * Instantiates a new Verification.
     *
     * @param locationId the location id
     * @param terminalId the terminal id
     */
    public Verification(String locationId, String terminalId) {
        super(null);
        this.locationId = locationId;
        this.terminalId = terminalId;
    }

    /**
     * Instantiates a new Verification.
     *
     * @param id         the id
     * @param locationId the location id
     * @param terminalId the terminal id
     * @param threadId   the thread id
     * @param proofId    the proof id
     */
    public Verification(String id, String locationId, String terminalId, String threadId,
        String proofId) {
        super(id);
        this.locationId = locationId;
        this.terminalId = terminalId;
        this.threadId = threadId;
        this.proofId = proofId;
    }

    /**
     * Instantiates a new Verification.
     *
     * @param id         the id
     * @param locationId the location id
     * @param terminalId the terminal id
     * @param threadId   the thread id
     * @param proofId    the proof id
     * @param state      the state
     * @param proofState the proof state
     */
    public Verification(String id, String locationId, String terminalId, String threadId,
        String proofId,
        T state, String proofState) {
        super(id);
        this.locationId = locationId;
        this.terminalId = terminalId;
        this.threadId = threadId;
        this.proofId = proofId;
        this.state = state;
        this.proofState = proofState;
    }

    /**
     * Gets location id.
     *
     * @return the location id
     */
    public String getLocationId() {
        return locationId;
    }

    /**
     * Sets location id.
     *
     * @param locationId the location id
     */
    public void setLocationId(String locationId) {
        this.locationId = locationId;
    }

    /**
     * Gets terminal id.
     *
     * @return the terminal id
     */
    public String getTerminalId() {
        return terminalId;
    }

    /**
     * Sets terminal id.
     *
     * @param terminalId the terminal id
     */
    public void setTerminalId(String terminalId) {
        this.terminalId = terminalId;
    }

    /**
     * Gets thread id.
     *
     * @return the thread id
     */
    public String getThreadId() {
        return threadId;
    }

    /**
     * Sets thread id.
     *
     * @param threadId the thread id
     */
    public void setThreadId(String threadId) {
        this.threadId = threadId;
    }

    /**
     * Gets proof id.
     *
     * @return the proof id
     */
    public String getProofId() {
        return proofId;
    }

    /**
     * Sets proof id.
     *
     * @param proofId the proof id
     */
    public void setProofId(String proofId) {
        this.proofId = proofId;
    }

    /**
     * Gets state.
     *
     * @return the state
     */
    public T getState() {
        return state;
    }

    /**
     * Sets state.
     *
     * @param state the state
     */
    public void setState(T state) {
        this.state = state;
    }

    /**
     * Gets proof state.
     *
     * @return the proof state
     */
    public String getProofState() {
        return proofState;
    }

    /**
     * Sets proof state.
     *
     * @param proofState the proof state
     */
    public void setProofState(String proofState) {
        this.proofState = proofState;
    }
}
