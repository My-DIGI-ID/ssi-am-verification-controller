package com.bka.ssi.controller.verification.company.services.system.accreditation.enums;

public enum DefaultAccreditationStatus implements AccreditationStatus {
    OPEN("OPEN"),
    PENDING("PENDING"),
    ACCEPTED("ACCEPTED"),
    CANCELLED("CANCELLED"),
    REVOKED("REVOKED");

    private final String name;

    DefaultAccreditationStatus(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return this.name;
    }
}
