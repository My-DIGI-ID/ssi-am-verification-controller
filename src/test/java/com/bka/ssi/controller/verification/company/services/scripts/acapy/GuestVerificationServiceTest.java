package com.bka.ssi.controller.verification.company.services.scripts.acapy;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.bka.ssi.controller.verification.acapy_client.api.PresentProofV10Api;
import com.bka.ssi.controller.verification.company.aop.configuration.agents.ACAPYConfiguration;
import com.bka.ssi.controller.verification.company.aop.configuration.agents.CredentialsConfiguration;
import com.bka.ssi.controller.verification.company.services.enums.GuestVerificationStatus;
import com.bka.ssi.controller.verification.company.services.exceptions.InvalidVerificationStateChangeException;
import com.bka.ssi.controller.verification.company.services.models.GuestVerification;
import com.bka.ssi.controller.verification.company.services.models.common.BasisId;
import com.bka.ssi.controller.verification.company.services.models.credentials.GuestCredential;
import com.bka.ssi.controller.verification.company.services.repositories.GuestVerificationRepository;
import com.bka.ssi.controller.verification.company.services.scripts.acapy.utilities.ACAPYConnectionlessProofUtility;
import com.bka.ssi.controller.verification.company.services.scripts.acapy.utilities.ACAPYUtilities;
import com.bka.ssi.controller.verification.company.services.system.accreditation.AccreditationClient;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.ZonedDateTime;
import java.util.Optional;

public class GuestVerificationServiceTest {

    private final Logger logger = LoggerFactory.getLogger(GuestVerificationServiceTest.class);

    @Mock
    private ACAPYConnectionlessProofUtility acapyConnectionlessProofUtility;

    @Mock
    private GuestVerificationRepository repository;

    @Mock
    private ACAPYConfiguration acapyConfiguration;

    @Mock
    private AccreditationClient accreditationClient;

    @Mock
    private CredentialsConfiguration credentialsConfiguration;

    @Mock
    private ACAPYUtilities acapyUtilities;

    @Mock
    private PresentProofV10Api presentProofV10Api;

    @InjectMocks
    private GuestVerificationService guestVerificationService;

    private BasisId basisId;
    private GuestCredential guestCredential;

    private GuestVerification checkedInVerification;
    private GuestVerification pendingVerification;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);

        basisId = new BasisId();
        basisId.setFirstName("John");
        basisId.setFamilyName("Doe");
        basisId.setDateOfBirth("05/05/1980");
        basisId.setDateOfExpiry("01/01/2025");

        guestCredential = new GuestCredential();
        guestCredential.setFirstName("John");
        guestCredential.setLastName("Doe");

        checkedInVerification = new GuestVerification("12345");
        checkedInVerification.setBasisId(basisId);
        checkedInVerification.setGuestCredential(guestCredential);
        checkedInVerification.setAccreditationId("67890");
        checkedInVerification.setState(GuestVerificationStatus.CHECK_IN);
        checkedInVerification.setCheckInDateTime(ZonedDateTime.now());
        checkedInVerification.setLocationId("123");
        checkedInVerification.setThreadId("456");

        pendingVerification = new GuestVerification("12346");
        pendingVerification.setState(GuestVerificationStatus.PENDING);

        guestVerificationService = new GuestVerificationService(acapyConnectionlessProofUtility,
            logger, acapyConfiguration, accreditationClient, repository, credentialsConfiguration
            , acapyUtilities);
    }

    @Test
    void checkout() throws Exception {
        String verificationId = "12345";

        Mockito.when(repository.findById(verificationId))
            .thenReturn(Optional.of(checkedInVerification));

        Mockito.when(repository.save(Mockito.any(GuestVerification.class)))
            .thenAnswer(invocation -> invocation.getArguments()[0]);

        ZonedDateTime currentTime = ZonedDateTime.now();

        GuestVerification
            returnedVerification = guestVerificationService.checkout(verificationId);

        assertEquals(basisId, returnedVerification.getBasisId());
        assertEquals(guestCredential, returnedVerification.getGuestCredential());
        assertEquals(GuestVerificationStatus.CHECK_OUT, returnedVerification.getState());
        assertTrue(checkedInVerification.getCheckInDateTime()
            .isEqual(returnedVerification.getCheckInDateTime()));
        assertTrue(currentTime.isBefore(returnedVerification.getCheckOutDateTime()));
        assertEquals(checkedInVerification.getLocationId(), returnedVerification.getLocationId());
        assertEquals(checkedInVerification.getTerminalId(), returnedVerification.getTerminalId());
    }

    @Test
    void checkoutShouldThrowIfVerificationNotCheckedIn() throws Exception {
        String verificationId = "12346";

        Mockito.when(repository.findById(verificationId))
            .thenReturn(Optional.of(pendingVerification));

        assertThrows(InvalidVerificationStateChangeException.class, () -> {
            guestVerificationService.checkout(verificationId);
        });
    }
}
