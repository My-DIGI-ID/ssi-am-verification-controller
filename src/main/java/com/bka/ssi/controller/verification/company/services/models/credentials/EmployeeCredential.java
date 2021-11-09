package com.bka.ssi.controller.verification.company.services.models.credentials;

import com.bka.ssi.controller.verification.company.services.models.common.ContactInformation;
import com.bka.ssi.controller.verification.company.services.models.common.Employer;
import com.bka.ssi.controller.verification.company.services.models.common.IdentityManagement;
import com.bka.ssi.controller.verification.company.services.models.common.Persona;
import com.bka.ssi.controller.verification.company.services.models.common.Position;

public class EmployeeCredential {

    private String employeeId;
    private String employeeState;
    private Persona persona;
    private ContactInformation contactInformation;
    private IdentityManagement identityManagement;
    private Employer employer;
    private Position position;

    public EmployeeCredential() {
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public String getEmployeeState() {
        return employeeState;
    }

    public void setEmployeeState(String employeeState) {
        this.employeeState = employeeState;
    }

    public Persona getPersona() {
        return persona;
    }

    public void setPersona(
        Persona persona) {
        this.persona = persona;
    }

    public ContactInformation getContactInformation() {
        return contactInformation;
    }

    public void setContactInformation(
        ContactInformation contactInformation) {
        this.contactInformation = contactInformation;
    }

    public IdentityManagement getIdentityManagement() {
        return identityManagement;
    }

    public void setIdentityManagement(
        IdentityManagement identityManagement) {
        this.identityManagement = identityManagement;
    }

    public Employer getEmployer() {
        return employer;
    }

    public void setEmployer(
        Employer employer) {
        this.employer = employer;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(
        Position position) {
        this.position = position;
    }
}
