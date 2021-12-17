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
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;

/**
 * The type Guest verification timeout task.
 */
public class GuestVerificationTimeoutTask implements Runnable {

    private final Logger logger = LoggerFactory.getLogger(GuestVerificationTimeoutTask.class);
    private final GuestVerificationRepository repository;
    /**
     * The Verification id.
     */
    public final String verificationId;

    /**
     * Instantiates a new Guest verification timeout task.
     *
     * @param repository     the repository
     * @param verificationId the verification id
     */
    public GuestVerificationTimeoutTask(GuestVerificationRepository repository,
        String verificationId) {
        this.repository = repository;
        this.verificationId = verificationId;
    }

    @Override
    public void run() {
        logger.debug("Executing timeout task for guest verification " + verificationId);

        Optional<GuestVerification> verificationOptional = this.repository.findById(verificationId);
        if (verificationOptional.isPresent()) {
            GuestVerification verification = verificationOptional.get();
            if (verification.getState() == GuestVerificationStatus.PENDING) {
                verification.setState(GuestVerificationStatus.TIMEOUT);

                this.repository.save(verification);
            }
        }
    }
}
