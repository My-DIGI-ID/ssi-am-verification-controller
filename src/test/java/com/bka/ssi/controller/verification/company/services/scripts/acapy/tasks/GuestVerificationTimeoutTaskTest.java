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

package com.bka.ssi.controller.verification.company.services.scripts.acapy.tasks;

import com.bka.ssi.controller.verification.company.services.enums.GuestVerificationStatus;
import com.bka.ssi.controller.verification.company.services.models.GuestVerification;
import com.bka.ssi.controller.verification.company.services.repositories.GuestVerificationRepository;
import com.bka.ssi.controller.verification.company.testutilities.GuestVerificationBuilder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

public class GuestVerificationTimeoutTaskTest {

    @Mock
    private GuestVerificationRepository guestVerificationRepository;

    private GuestVerificationTimeoutTask guestVerificationTimeoutTask;

    private String verificationId = "123";

    private GuestVerificationBuilder guestVerificationBuilder;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);

        guestVerificationTimeoutTask =
            new GuestVerificationTimeoutTask(guestVerificationRepository, verificationId);

        guestVerificationBuilder = new GuestVerificationBuilder();
        guestVerificationBuilder.id = verificationId;
    }

    @Test
    void verificationTimeout() {

        GuestVerification pendingVerification = guestVerificationBuilder.buildPendingVerification();

        Mockito
            .when(guestVerificationRepository.findById(pendingVerification.getId()))
            .thenReturn(Optional.of(pendingVerification));

        guestVerificationTimeoutTask.run();

        ArgumentCaptor<GuestVerification> argumentCaptor =
            ArgumentCaptor.forClass(GuestVerification.class);
        Mockito.verify(guestVerificationRepository, Mockito.times(1))
            .save(argumentCaptor.capture());
        GuestVerification savedVerification = argumentCaptor.getValue();

        Assertions.assertEquals(GuestVerificationStatus.TIMEOUT, savedVerification.getState());
    }

    @Test
    void verificationNotFound() {
        Mockito
            .when(guestVerificationRepository.findById(verificationId))
            .thenReturn(Optional.empty());

        guestVerificationTimeoutTask.run();

        Mockito.verify(guestVerificationRepository, Mockito.never()).save(Mockito.any());
    }

    @Test
    void verificationNotPending() {
        GuestVerification checkinVerification =
            guestVerificationBuilder.buildCheckInVerification();

        Mockito
            .when(guestVerificationRepository.findById(verificationId))
            .thenReturn(Optional.of(checkinVerification));

        guestVerificationTimeoutTask.run();

        Mockito.verify(guestVerificationRepository, Mockito.never()).save(Mockito.any());
    }
}
