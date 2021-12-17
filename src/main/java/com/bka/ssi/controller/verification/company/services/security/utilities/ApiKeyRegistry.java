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

import com.bka.ssi.controller.verification.company.aop.configuration.agents.ACAPYConfiguration;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

/**
 * The type Api key registry.
 */
@Component
public class ApiKeyRegistry {

    private Map<String, Pair<String, String>> registry;
    private final ACAPYConfiguration acapyConfiguration;

    /**
     * Instantiates a new Api key registry.
     *
     * @param acapyConfiguration the acapy configuration
     */
    public ApiKeyRegistry(
        ACAPYConfiguration acapyConfiguration) {
        this.acapyConfiguration = acapyConfiguration;

        this.init();
    }

    private void init() {
        this.registry = new HashMap<>();

        this.registry.put(
            ACAPYConfiguration.API_KEY_ID,
            Pair.of(this.acapyConfiguration.getApiKeyHeaderName(),
                this.acapyConfiguration.getWebhookApiKey())
        );
    }

    /**
     * Gets entry by id.
     *
     * @param id the id
     * @return the entry by id
     */
    public Pair<String, String> getEntryById(String id) {
        return this.registry.get(id);
    }

    /**
     * Gets registry.
     *
     * @return the registry
     */
    public Map<String, Pair<String, String>> getRegistry() {
        return registry;
    }
}
