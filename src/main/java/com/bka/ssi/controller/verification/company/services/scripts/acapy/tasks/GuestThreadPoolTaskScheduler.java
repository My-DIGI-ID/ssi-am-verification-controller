package com.bka.ssi.controller.verification.company.services.scripts.acapy.tasks;

import com.bka.ssi.controller.verification.company.services.repositories.GuestVerificationRepository;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.concurrent.TimeUnit;

@Component
public class GuestThreadPoolTaskScheduler implements GuestTaskScheduler {

    private int timeout;

    private final Logger logger;
    private final ThreadPoolTaskScheduler taskScheduler;
    private final GuestVerificationRepository repository;

    public GuestThreadPoolTaskScheduler(Logger logger, ThreadPoolTaskScheduler taskScheduler,
        @Qualifier("guestVerificationMongoDbFacade")
            GuestVerificationRepository verificationRepository,
        @Value("${guest.verificationTimeout}") int timeout) {
        this.logger = logger;
        this.taskScheduler = taskScheduler;
        this.repository = verificationRepository;
        this.timeout = timeout;
    }

    public void scheduleGuestVerificationTimeout(String verificationId) {
        logger.debug("Scheduling timeout task for guest verification " + verificationId);
        taskScheduler.schedule(new GuestVerificationTimeoutTask(this.repository, verificationId),
            new Date(System.currentTimeMillis() + TimeUnit.SECONDS.toMillis(timeout)));
    }
}
