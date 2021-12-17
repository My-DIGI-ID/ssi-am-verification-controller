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

package com.bka.ssi.controller.verification.company.services.models.common;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * The type Proof request thread.
 */
public class ProofRequestThread {

    @JsonProperty("thid")
    private String threadId;

    @JsonProperty("sender_order")
    private Integer senderOrder = 0;

    @JsonProperty("received_orders")
    private EmptyDTO emptyDTO;

    /**
     * Instantiates a new Proof request thread.
     */
    public ProofRequestThread() {}

    /**
     * Sets thread id.
     *
     * @param threadId the thread id
     */
    public void setThreadId(String threadId) {
        this.threadId = threadId;
    }

    /**
     * Sets sender order.
     *
     * @param senderOrder the sender order
     */
    public void setSenderOrder(Integer senderOrder) {
        this.senderOrder = senderOrder;
    }

    /**
     * Sets received orders.
     *
     * @param empty the empty
     */
    public void setReceivedOrders(EmptyDTO empty) {
        this.emptyDTO = empty;
    }
}
