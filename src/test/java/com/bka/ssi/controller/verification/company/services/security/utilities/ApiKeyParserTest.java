package com.bka.ssi.controller.verification.company.services.security.utilities;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.bka.ssi.controller.verification.company.aop.configuration.agents.ACAPYConfiguration;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

public class ApiKeyParserTest {

    private ApiKeyParser parser;

    @BeforeEach
    public void init() {
        HttpServletRequest request = Mockito.mock(HttpServletRequest.class);
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
        Mockito.when(request.getHeader("X-API-Key")).thenReturn("123");

        ACAPYConfiguration acapyConfiguration =
            new ACAPYConfiguration(null, null, null, null, null,
                "123", "X-API-Key", null,
                null, null, null, false);
        ApiKeyRegistry registry = new ApiKeyRegistry(acapyConfiguration);

        this.parser = new ApiKeyParser(registry);
    }

    @Test
    public void getApiKeyById() {
        String apiKey = this.parser.getApiKeyById(ACAPYConfiguration.API_KEY_ID);

        assertNotNull(apiKey);
        assertEquals("123", apiKey);
    }
}
