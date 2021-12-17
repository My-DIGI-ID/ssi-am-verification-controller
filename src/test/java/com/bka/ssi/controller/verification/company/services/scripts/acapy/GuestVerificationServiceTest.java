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

package com.bka.ssi.controller.verification.company.services.scripts.acapy;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.bka.ssi.controller.verification.company.aop.configuration.agents.ACAPYConfiguration;
import com.bka.ssi.controller.verification.company.aop.configuration.agents.CredentialsConfiguration;
import com.bka.ssi.controller.verification.company.services.enums.GuestVerificationStatus;
import com.bka.ssi.controller.verification.company.services.exceptions.InvalidVerificationStateChangeException;
import com.bka.ssi.controller.verification.company.services.models.GuestVerification;
import com.bka.ssi.controller.verification.company.services.models.common.BasisId;
import com.bka.ssi.controller.verification.company.services.models.credentials.GuestCredential;
import com.bka.ssi.controller.verification.company.services.repositories.GuestVerificationRepository;
import com.bka.ssi.controller.verification.company.services.scripts.acapy.dto.input.ACAPYPresentProofDto;
import com.bka.ssi.controller.verification.company.services.scripts.acapy.tasks.GuestThreadPoolTaskScheduler;
import com.bka.ssi.controller.verification.company.services.scripts.acapy.utilities.ACAPYConnectionlessProofUtility;
import com.bka.ssi.controller.verification.company.services.scripts.acapy.utilities.ACAPYUtilities;
import com.bka.ssi.controller.verification.company.services.system.accreditation.AccreditationClient;
import com.bka.ssi.controller.verification.company.testutilities.GuestVerificationBuilder;
import io.github.my_digi_id.acapy_client.api.PresentProofV10Api;
import io.github.my_digi_id.acapy_client.model.V10PresentationExchange;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.util.ReflectionTestUtils;

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

    @Mock
    private GuestThreadPoolTaskScheduler guestTaskScheduler;

    @InjectMocks
    private GuestVerificationService guestVerificationService;

    private BasisId basisId;
    private GuestCredential guestCredential;

    private GuestVerification checkedInVerification;
    private GuestVerification pendingVerification;

    private ACAPYPresentProofDto acapyPresentProofDto;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);

        GuestVerificationBuilder guestVerificationBuilder = new GuestVerificationBuilder();

        checkedInVerification = guestVerificationBuilder.buildCheckInVerification();
        basisId = checkedInVerification.getBasisId();
        guestCredential = checkedInVerification.getGuestCredential();

        guestVerificationBuilder.reset();
        pendingVerification = guestVerificationBuilder.buildPendingVerification();

        acapyPresentProofDto = new ACAPYPresentProofDto("12345", "23456", "verified");

        guestVerificationService = new GuestVerificationService(acapyConnectionlessProofUtility,
            logger, acapyConfiguration, accreditationClient, repository, credentialsConfiguration
            , acapyUtilities, guestTaskScheduler);
        ReflectionTestUtils.setField(guestVerificationService, "presentProofApi",
            presentProofV10Api);
    }

    @Test
    void handleProofVerifiedFailLocation() throws Exception {

        checkedInVerification.setLocationId("234");

        V10PresentationExchange presentationExchange = new V10PresentationExchange();
        presentationExchange.setVerified(V10PresentationExchange.VerifiedEnum.TRUE);

        Mockito.when(repository.findByThreadId(acapyPresentProofDto.getThreadId()))
            .thenReturn(Optional.of(checkedInVerification));

        Mockito.when(presentProofV10Api.presentProofRecordsPresExIdGet(
                acapyPresentProofDto.getPresentationExchangeId()))
            .thenReturn(presentationExchange);

        Mockito.when(acapyUtilities.getBasisId(Mockito.any())).thenReturn(basisId);
        Mockito.when(acapyUtilities.getGuestCredential(Mockito.any())).thenReturn(guestCredential);

        Mockito.when(acapyConfiguration.getBasisIdVerificationDevMode()).thenReturn(false);

        guestVerificationService.handleProofVerified(acapyPresentProofDto);

        ArgumentCaptor<GuestVerification> argumentCaptor =
            ArgumentCaptor.forClass(GuestVerification.class);
        Mockito.verify(repository).save(argumentCaptor.capture());

        GuestVerification savedVerification = argumentCaptor.getValue();
        assertEquals(GuestVerificationStatus.FAIL_LOCATION, savedVerification.getState());
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
