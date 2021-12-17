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

package com.bka.ssi.controller.verification.company.services.security.utilities;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.bka.ssi.controller.verification.company.aop.configuration.agents.ACAPYConfiguration;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ApiKeyRegistryTest {

    private ApiKeyRegistry registry;
    private ACAPYConfiguration acapyConfiguration;

    @BeforeEach
    public void init() {
        this.acapyConfiguration =
            new ACAPYConfiguration(null, null, null, null, null,
                "123", "X-API-Key", null,
                null, null, null, false);

        this.registry = new ApiKeyRegistry(acapyConfiguration);
    }

    @Test
    public void getRegistry() {
        assertNotNull(this.registry.getRegistry());
    }

    @Test
    public void getEntryForACAPY() {
        assertEquals(this.acapyConfiguration.getApiKeyHeaderName(),
            this.registry.getEntryById(ACAPYConfiguration.API_KEY_ID).getFirst());
        assertEquals(this.acapyConfiguration.getWebhookApiKey(),
            this.registry.getEntryById(ACAPYConfiguration.API_KEY_ID).getSecond());
    }
}
