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

package com.bka.ssi.controller.verification.company.services.repositories;

import com.bka.ssi.controller.verification.company.services.enums.GuestVerificationStatus;
import com.bka.ssi.controller.verification.company.services.models.GuestVerification;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("integrationtest")
@TestPropertySource("classpath:application-integrationtest.properties")
public class GuestVerificationRepositoryIntegrationTest {

    @Autowired
    @Qualifier("guestVerificationMongoDbFacade")
    private GuestVerificationRepository verificationRepository;

    @BeforeEach
    public void setUp() {
        GuestVerification verification = new GuestVerification(
            "123",
            "321",
            "456",
            "789",
            "012",
            null,
            null,
            null,
            null,
            null,
            GuestVerificationStatus.PENDING,
            null
        );

        GuestVerification anotherVerification = new GuestVerification(
            "123456",
            "654321",
            "456",
            "789",
            "0987654321",
            null,
            null,
            null,
            null,
            null,
            GuestVerificationStatus.PENDING,
            null
        );

        GuestVerification savedVerification = this.verificationRepository.save(verification);
        GuestVerification savedAnotherVerification =
            this.verificationRepository.save(anotherVerification);

        assertEquals(verification.getId(), savedVerification.getId());
        assertEquals(anotherVerification.getId(), savedAnotherVerification.getId());
    }

    @Test
    public void shouldFindVerificationByThreadId() {
        Optional<GuestVerification> verification =
            this.verificationRepository.findByThreadId("012");

        assertTrue(verification.isPresent());
    }

    @Test
    public void shouldNotFindVerificationByTreadId() {

        Optional<GuestVerification> verification = this.verificationRepository.findByThreadId(
            "1234567890987654321");

        assertTrue(verification.isEmpty());
    }

    @Test
    public void shouldFindVerificationByAccreditationId() {
        Optional<GuestVerification> verification =
            this.verificationRepository.findByAccreditationId("321");

        assertTrue(verification.isPresent());
    }

    @Test
    public void shouldNotFindVerificationByAccreditationId() {
        assertEquals(Optional.empty(), this.verificationRepository.findByAccreditationId(
                "1234567890987654321"));
    }

    @Test
    public void shouldFindVerificationsByLocationIdAndTerminalId() {
        List<GuestVerification> verifications =
            this.verificationRepository.findAllByLocationIdAndTerminalId("456", "789");

        assertEquals(2, verifications.size());
    }

    @Test
    public void shouldNotFindVerificationsByLocationIdAndTerminalId() {
        List<GuestVerification> verifications =
            this.verificationRepository.findAllByLocationIdAndTerminalId("1234567890", "789");

        assertEquals(0, verifications.size());
    }
}
