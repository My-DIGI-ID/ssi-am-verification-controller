package com.bka.ssi.controller.verification.company.services.enums;

public enum EmployeeVerificationStatus implements VerificationStatus {
    PENDING("PENDING"),
    FAIL_VERIFY_CREDENTIAL("FAIL_VERIFY_CREDENTIAL"),
    VERIFIED("VERIFIED");

    private final String name;

    EmployeeVerificationStatus(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }
}
