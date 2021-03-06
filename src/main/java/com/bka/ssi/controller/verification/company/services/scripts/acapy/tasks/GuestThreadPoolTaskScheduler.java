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

import com.bka.ssi.controller.verification.company.services.repositories.GuestVerificationRepository;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.concurrent.ThreadPoolTaskScheduler;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * The type Guest thread pool task scheduler.
 */
@Component
public class GuestThreadPoolTaskScheduler implements GuestTaskScheduler {

    private int timeout;

    private final Logger logger;
    private final ThreadPoolTaskScheduler taskScheduler;
    private final GuestVerificationRepository repository;

    /**
     * Instantiates a new Guest thread pool task scheduler.
     *
     * @param logger                 the logger
     * @param taskScheduler          the task scheduler
     * @param verificationRepository the verification repository
     * @param timeout                the timeout
     */
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
