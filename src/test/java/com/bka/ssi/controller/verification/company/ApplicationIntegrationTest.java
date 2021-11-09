package com.bka.ssi.controller.verification.company;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;

@SpringBootTest
@ActiveProfiles("defaultintegrationtest")
@TestPropertySource("classpath:application-defaultintegrationtest.properties")
class ApplicationIntegrationTest {
    
    @Test
    void contextLoads() {
    }
}
