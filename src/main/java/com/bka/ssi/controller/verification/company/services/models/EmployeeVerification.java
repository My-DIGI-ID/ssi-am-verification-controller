package com.bka.ssi.controller.verification.company.services.models;

import com.bka.ssi.controller.verification.company.services.enums.EmployeeVerificationStatus;
import com.bka.ssi.controller.verification.company.services.models.credentials.EmployeeCredential;

public class EmployeeVerification extends Verification<EmployeeVerificationStatus> {

    private EmployeeCredential employeeCredential;

    public EmployeeVerification(String id) {
        super(id);
        this.state = EmployeeVerificationStatus.PENDING;
    }

    public EmployeeVerification(String threadId, String locationId, String terminalId) {
        super(null, locationId, terminalId, threadId, threadId);
        this.state = EmployeeVerificationStatus.PENDING;
    }

    public EmployeeVerification(String id, String threadId, String locationId, String terminalId) {
        super(id, locationId, terminalId, threadId, threadId);
        this.state = EmployeeVerificationStatus.PENDING;
    }

    public EmployeeCredential getEmployeeCredential() {
        return employeeCredential;
    }

    public void setEmployeeCredential(
        EmployeeCredential employeeCredential) {
        this.employeeCredential = employeeCredential;
    }
}
