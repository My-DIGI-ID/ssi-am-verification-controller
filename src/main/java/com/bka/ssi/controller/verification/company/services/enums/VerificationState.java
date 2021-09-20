package com.bka.ssi.controller.verification.company.services.enums;

public enum VerificationState {
	CHECK_IN("CHECK_IN"),
	CHECK_OUT("CHECK_OUT"),
	FAIL_DATE_TIME("FAIL_DATE_TIME"),
	FAIL_VERIFY_CREDENTIAL("FAIL_VERIFY_CREDENTIAL");
	
	private final String name;

    VerificationState(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }
}
