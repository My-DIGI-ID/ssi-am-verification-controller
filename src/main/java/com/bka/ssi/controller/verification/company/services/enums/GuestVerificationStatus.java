package com.bka.ssi.controller.verification.company.services.enums;

public enum GuestVerificationStatus implements VerificationStatus {
    PENDING("PENDING"),
    CHECK_IN("CHECK_IN"),
    CHECK_OUT("CHECK_OUT"),
    FAIL_DATE_TIME("FAIL_DATE_TIME"),
    FAIL_VERIFY_CREDENTIAL("FAIL_VERIFY_CREDENTIAL"),
    FAIL_LOCATION("FAIL_LOCATION"),
    TIMEOUT("TIMEOUT");

    private final String name;

    GuestVerificationStatus(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }
}
