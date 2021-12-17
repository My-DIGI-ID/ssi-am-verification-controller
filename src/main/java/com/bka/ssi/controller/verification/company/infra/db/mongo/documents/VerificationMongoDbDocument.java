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

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Field;

/**
 * The type Verification mongo db document.
 */
public class VerificationMongoDbDocument {
    @Id
    private String id;

    @Field("proofId")
    private String proofId;

    @Field("locationId")
    private String locationId;

    @Field("terminalId")
    private String terminalId;

    @Field("threadId")
    private String threadId;

    @Field("state")
    private String state;

    @Field("proofState")
    private String proofState;

    /**
     * Instantiates a new Verification mongo db document.
     */
    public VerificationMongoDbDocument() {
    }

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
     * Gets state.
     *
     * @return the state
     */
    public String getState() {
        return state;
    }

    /**
     * Sets state.
     *
     * @param state the state
     */
    public void setState(String state) {
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
