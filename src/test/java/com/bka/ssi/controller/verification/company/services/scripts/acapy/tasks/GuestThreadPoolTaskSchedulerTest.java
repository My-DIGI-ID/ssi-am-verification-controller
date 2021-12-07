package com.bka.ssi.controller.verification.company.services.scripts.acapy.tasks;

import com.bka.ssi.controller.verification.company.aop.configuration.scheduler.ThreadPoolTaskSchedulerConfig;
import com.bka.ssi.controller.verification.company.services.enums.GuestVerificationStatus;
import com.bka.ssi.controller.verification.company.services.models.GuestVerification;
import com.bka.ssi.controller.verification.company.services.repositories.GuestVerificationRepository;
import com.bka.ssi.controller.verification.company.testutilities.GuestVerificationBuilder;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.slf4j.Logger;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;

import java.util.Optional;
import java.util.concurrent.TimeUnit;

public class GuestThreadPoolTaskSchedulerTest {

    @Mock
    private Logger logger;

    @Mock
    private GuestVerificationRepository guestVerificationRepository;

    private ThreadPoolTaskScheduler taskScheduler;

    private GuestThreadPoolTaskScheduler guestTaskScheduler;

    @BeforeEach()
    void setup() {
        MockitoAnnotations.openMocks(this);

        taskScheduler = new ThreadPoolTaskSchedulerConfig().threadPoolTaskScheduler(5);
        taskScheduler.initialize();

        guestTaskScheduler =
            new GuestThreadPoolTaskScheduler(logger, taskScheduler, guestVerificationRepository,
                1);
    }

    @Test
    void scheduleGuestVerificationTimeout() throws InterruptedException {
        GuestVerification pendingVerification =
            new GuestVerificationBuilder().buildPendingVerification();

        Mockito.when(guestVerificationRepository.findById(pendingVerification.getId())).thenReturn(
            Optional.of(pendingVerification));

        guestTaskScheduler.scheduleGuestVerificationTimeout(pendingVerification.getId());

        TimeUnit.SECONDS.sleep(5);

        ArgumentCaptor<GuestVerification> argumentCaptor =
            ArgumentCaptor.forClass(GuestVerification.class);
        Mockito.verify(guestVerificationRepository, Mockito.times(1))
            .save(argumentCaptor.capture());
        GuestVerification savedVerification = argumentCaptor.getValue();

        Assertions.assertEquals(GuestVerificationStatus.TIMEOUT, savedVerification.getState());
    }

    @AfterEach
    void cleanup() {
        taskScheduler.shutdown();
    }
}
