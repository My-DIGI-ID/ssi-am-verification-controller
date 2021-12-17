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

package com.bka.ssi.controller.verification.company.services.models.credentials;

import com.bka.ssi.controller.verification.company.services.models.common.ContactInformation;
import com.bka.ssi.controller.verification.company.services.models.common.Employer;
import com.bka.ssi.controller.verification.company.services.models.common.IdentityManagement;
import com.bka.ssi.controller.verification.company.services.models.common.Persona;
import com.bka.ssi.controller.verification.company.services.models.common.Position;

/**
 * The type Employee credential.
 */
public class EmployeeCredential {

    private String employeeId;
    private String employeeState;
    private Persona persona;
    private ContactInformation contactInformation;
    private IdentityManagement identityManagement;
    private Employer employer;
    private Position position;

    /**
     * Instantiates a new Employee credential.
     */
    public EmployeeCredential() {
    }

    /**
     * Gets employee id.
     *
     * @return the employee id
     */
    public String getEmployeeId() {
        return employeeId;
    }

    /**
     * Sets employee id.
     *
     * @param employeeId the employee id
     */
    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    /**
     * Gets employee state.
     *
     * @return the employee state
     */
    public String getEmployeeState() {
        return employeeState;
    }

    /**
     * Sets employee state.
     *
     * @param employeeState the employee state
     */
    public void setEmployeeState(String employeeState) {
        this.employeeState = employeeState;
    }

    /**
     * Gets persona.
     *
     * @return the persona
     */
    public Persona getPersona() {
        return persona;
    }

    /**
     * Sets persona.
     *
     * @param persona the persona
     */
    public void setPersona(
        Persona persona) {
        this.persona = persona;
    }

    /**
     * Gets contact information.
     *
     * @return the contact information
     */
    public ContactInformation getContactInformation() {
        return contactInformation;
    }

    /**
     * Sets contact information.
     *
     * @param contactInformation the contact information
     */
    public void setContactInformation(
        ContactInformation contactInformation) {
        this.contactInformation = contactInformation;
    }

    /**
     * Gets identity management.
     *
     * @return the identity management
     */
    public IdentityManagement getIdentityManagement() {
        return identityManagement;
    }

    /**
     * Sets identity management.
     *
     * @param identityManagement the identity management
     */
    public void setIdentityManagement(
        IdentityManagement identityManagement) {
        this.identityManagement = identityManagement;
    }

    /**
     * Gets employer.
     *
     * @return the employer
     */
    public Employer getEmployer() {
        return employer;
    }

    /**
     * Sets employer.
     *
     * @param employer the employer
     */
    public void setEmployer(
        Employer employer) {
        this.employer = employer;
    }

    /**
     * Gets position.
     *
     * @return the position
     */
    public Position getPosition() {
        return position;
    }

    /**
     * Sets position.
     *
     * @param position the position
     */
    public void setPosition(
        Position position) {
        this.position = position;
    }
}
