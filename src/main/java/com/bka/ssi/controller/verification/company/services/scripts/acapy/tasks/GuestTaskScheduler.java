package com.bka.ssi.controller.verification.company.services.scripts.acapy.tasks;

public interface GuestTaskScheduler {
    void scheduleGuestVerificationTimeout(String verificationId);
}
