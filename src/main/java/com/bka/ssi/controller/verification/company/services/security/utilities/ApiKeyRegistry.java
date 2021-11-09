package com.bka.ssi.controller.verification.company.services.security.utilities;

import com.bka.ssi.controller.verification.company.aop.configuration.agents.ACAPYConfiguration;
import org.springframework.data.util.Pair;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class ApiKeyRegistry {

    private Map<String, Pair<String, String>> registry;
    private final ACAPYConfiguration acapyConfiguration;

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

    public Pair<String, String> getEntryById(String id) {
        return this.registry.get(id);
    }

    public Map<String, Pair<String, String>> getRegistry() {
        return registry;
    }
}
