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
 * The type Proof request service.
 */
public class ProofRequestService {
    @JsonProperty("recipientKeys")
    private String[] recipientKeys;

    @JsonProperty("routingKeys")
    private String[] routingKeys;

    @JsonProperty("serviceEndpoint")
    private String serviceEndpoint;

    @JsonProperty("endpointName")
    private String endpointName;

    /**
     * Instantiates a new Proof request service.
     */
    public ProofRequestService() {}

    /**
     * Sets recipient keys.
     *
     * @param recipientKeys the recipient keys
     */
    public void setRecipientKeys(String[] recipientKeys) {
        this.recipientKeys = recipientKeys;
    }

    /**
     * Sets routing keys.
     *
     * @param routingKeys the routing keys
     */
    public void setRoutingKeys(String[] routingKeys) {
        this.routingKeys = routingKeys;
    }

    /**
     * Sets service endpoint.
     *
     * @param serviceEndpoint the service endpoint
     */
    public void setServiceEndpoint(String serviceEndpoint) {
        this.serviceEndpoint = serviceEndpoint;
    }

    /**
     * Sets endpoint name.
     *
     * @param endpointName the endpoint name
     */
    public void setEndpointName(String endpointName) {
        this.endpointName = endpointName;
    }
}
