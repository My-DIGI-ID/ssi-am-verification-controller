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
