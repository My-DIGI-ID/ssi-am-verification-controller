package com.bka.ssi.controller.verification.company.services.repositories;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.bka.ssi.controller.verification.company.services.models.EmployeeVerification;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;

import java.util.List;
import java.util.Optional;

@SpringBootTest
@ActiveProfiles("integrationtest")
@TestPropertySource("classpath:application-integrationtest.properties")
public class EmployeeVerificationRepositoryIntegrationTest {

    @Autowired
    @Qualifier("employeeVerificationMongoDbFacade")
    private EmployeeVerificationRepository verificationRepository;

    @BeforeEach
    public void setUp() {
        EmployeeVerification verification = new EmployeeVerification(
            "123",
            "012",
            "456",
            "789"
        );

        EmployeeVerification anotherVerification = new EmployeeVerification(
            "124",
            "013",
            "456",
            "789"
        );

        EmployeeVerification savedVerification = this.verificationRepository.save(verification);
        EmployeeVerification savedAnotherVerification =
            this.verificationRepository.save(anotherVerification);

        assertEquals(verification.getId(), savedVerification.getId());
        assertEquals(anotherVerification.getId(), savedAnotherVerification.getId());
    }

    @Test
    public void shouldFindVerificationByThreadId() {
        Optional<EmployeeVerification> verification =
            this.verificationRepository.findByThreadId("012");

        assertTrue(verification.isPresent());
    }

    @Test
    public void shouldNotFindVerificationByTreadId() {
        Optional<EmployeeVerification> verification =
            this.verificationRepository.findByThreadId(
                "1234567890987654321");

        assertTrue(verification.isEmpty());
    }

    @Test
    public void shouldFindVerificationsByLocationIdAndTerminalId() {
        List<EmployeeVerification> verifications =
            this.verificationRepository.findAllByLocationIdAndTerminalId("456", "789");

        assertEquals(2, verifications.size());
    }

    @Test
    public void shouldNotFindVerificationsByLocationIdAndTerminalId() {
        List<EmployeeVerification> verifications =
            this.verificationRepository.findAllByLocationIdAndTerminalId("1234567890", "789");

        assertEquals(0, verifications.size());
    }
}
