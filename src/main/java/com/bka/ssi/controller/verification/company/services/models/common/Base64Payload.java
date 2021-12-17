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
 * The type Base 64 payload.
 */
public class Base64Payload {
    @JsonProperty("base64")
    private String base64;

    /**
     * Instantiates a new Base 64 payload.
     */
    public Base64Payload() {};

    /**
     * Sets base 64.
     *
     * @param base64 the base 64
     */
    public void setBase64(String base64) {
        this.base64 = base64;
    }

    /**
     * Gets base 64.
     *
     * @return the base 64
     */
    public String getBase64() {
        return base64;
    }
}
