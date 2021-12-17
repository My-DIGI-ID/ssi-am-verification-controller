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

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * The type Connectionless proof request.
 */
public class ConnectionlessProofRequest {
    @JsonProperty("@type")
    private String type = "did:sov:BzCbsNYhMrjHiqZDTUASHg;spec/present-proof/1.0/request-presentation";

    @JsonProperty("@id")
    private String id;

    @JsonProperty("request_presentations~attach")
    @JsonInclude(value = JsonInclude.Include.NON_EMPTY)
    private RequestPresentationAttach[] requestPresentationAttach;

    @JsonProperty("~service")
    @JsonInclude(value = JsonInclude.Include.NON_EMPTY)
    private ProofRequestService service;

    @JsonProperty("~thread")
    @JsonInclude(value = JsonInclude.Include.NON_EMPTY)
    private ProofRequestThread thread;

    /**
     * Instantiates a new Connectionless proof request.
     */
    public ConnectionlessProofRequest() {}

    /**
     * Sets type.
     *
     * @param type the type
     */
    public void setType(String type) {
        this.type = type;
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
     * Sets request presentation attach.
     *
     * @param requestPresentationAttach the request presentation attach
     */
    public void setRequestPresentationAttach(RequestPresentationAttach[] requestPresentationAttach) {
        this.requestPresentationAttach = requestPresentationAttach;
    }

    /**
     * Sets service.
     *
     * @param service the service
     */
    public void setService(ProofRequestService service) {
        this.service = service;
    }

    /**
     * Sets thread.
     *
     * @param thread the thread
     */
    public void setThread(ProofRequestThread thread) {
        this.thread = thread;
    }
}
