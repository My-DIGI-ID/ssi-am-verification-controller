package com.bka.ssi.controller.verification.company.services.scripts.acapy.tasks;

import com.bka.ssi.controller.verification.company.services.enums.GuestVerificationStatus;
import com.bka.ssi.controller.verification.company.services.models.GuestVerification;
import com.bka.ssi.controller.verification.company.services.repositories.GuestVerificationRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;

public class GuestVerificationTimeoutTask implements Runnable {

    private final Logger logger = LoggerFactory.getLogger(GuestVerificationTimeoutTask.class);
    private final GuestVerificationRepository repository;
    public final String verificationId;

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
