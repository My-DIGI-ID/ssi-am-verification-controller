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

package com.bka.ssi.controller.verification.company.infra.db.mongo.mappers;

import com.bka.ssi.controller.verification.company.infra.db.mongo.documents.EmployeeVerificationMongoDbDocument;
import com.bka.ssi.controller.verification.company.infra.db.mongo.values.EmployeeCredentialMongoDbValue;
import com.bka.ssi.controller.verification.company.infra.db.mongo.values.common.AddressMongoDbValue;
import com.bka.ssi.controller.verification.company.infra.db.mongo.values.common.ContactInformationMongoDbValue;
import com.bka.ssi.controller.verification.company.infra.db.mongo.values.common.EmployerMongoDbValue;
import com.bka.ssi.controller.verification.company.infra.db.mongo.values.common.IdentityManagementMongoDbValue;
import com.bka.ssi.controller.verification.company.infra.db.mongo.values.common.PersonaMongoDbValue;
import com.bka.ssi.controller.verification.company.infra.db.mongo.values.common.PositionMongoDbValue;
import com.bka.ssi.controller.verification.company.services.enums.EmployeeVerificationStatus;
import com.bka.ssi.controller.verification.company.services.models.EmployeeVerification;
import com.bka.ssi.controller.verification.company.services.models.common.Address;
import com.bka.ssi.controller.verification.company.services.models.common.ContactInformation;
import com.bka.ssi.controller.verification.company.services.models.common.Employer;
import com.bka.ssi.controller.verification.company.services.models.common.IdentityManagement;
import com.bka.ssi.controller.verification.company.services.models.common.Persona;
import com.bka.ssi.controller.verification.company.services.models.common.Position;
import com.bka.ssi.controller.verification.company.services.models.credentials.EmployeeCredential;
import org.springframework.stereotype.Component;

/**
 * The type Employee verification mongo db mapper.
 */
@Component
public class EmployeeVerificationMongoDbMapper {

    /**
     * Entity to document employee verification mongo db document.
     *
     * @param verification the verification
     * @return the employee verification mongo db document
     */
    public EmployeeVerificationMongoDbDocument entityToDocument(EmployeeVerification verification) {
        if (verification == null) {
            return null;
        } else {
            EmployeeVerificationMongoDbDocument document =
                new EmployeeVerificationMongoDbDocument();

            document.setId(verification.getId());
            document.setProofId(verification.getProofId());
            document.setLocationId(verification.getLocationId());
            document.setTerminalId(verification.getTerminalId());
            document.setThreadId(verification.getThreadId());
            document.setState(verification.getState().getName());
            document.setProofState(verification.getProofState());

            EmployeeCredentialMongoDbValue employeeCredentialMongoDbValue =
                new EmployeeCredentialMongoDbValue();
            if (verification.getEmployeeCredential() != null) {
                PersonaMongoDbValue personaMongoDbValue = new PersonaMongoDbValue();
                personaMongoDbValue
                    .setTitle(verification.getEmployeeCredential().getPersona().getTitle());
                personaMongoDbValue.setFirstName(
                    verification.getEmployeeCredential().getPersona().getFirstName());
                personaMongoDbValue.setLastName(
                    verification.getEmployeeCredential().getPersona().getLastName());

                ContactInformationMongoDbValue contactInformationMongoDbValue =
                    new ContactInformationMongoDbValue();
                contactInformationMongoDbValue.setEmails(
                    verification.getEmployeeCredential().getContactInformation().getEmails());
                contactInformationMongoDbValue.setPhoneNumbers(
                    verification.getEmployeeCredential().getContactInformation()
                        .getPhoneNumbers());

                IdentityManagementMongoDbValue identityManagementMongoDbValue =
                    new IdentityManagementMongoDbValue();
                identityManagementMongoDbValue.setEmail(
                    verification.getEmployeeCredential().getIdentityManagement().getEmail());
                identityManagementMongoDbValue.setReference(
                    verification.getEmployeeCredential().getIdentityManagement()
                        .getReference());
                identityManagementMongoDbValue.setUsername(
                    verification.getEmployeeCredential().getIdentityManagement()
                        .getUsername());

                AddressMongoDbValue employerAddressMongoDbValue = new AddressMongoDbValue();
                employerAddressMongoDbValue.setPostalCode(
                    verification.getEmployeeCredential().getEmployer().getAddress()
                        .getPostalCode());
                employerAddressMongoDbValue.setCountry(
                    verification.getEmployeeCredential().getEmployer().getAddress()
                        .getCountry());
                employerAddressMongoDbValue.setCity(
                    verification.getEmployeeCredential().getEmployer().getAddress().getCity());
                employerAddressMongoDbValue.setStreet(
                    verification.getEmployeeCredential().getEmployer().getAddress()
                        .getStreet());
                employerAddressMongoDbValue.setHouseNumber(
                    verification.getEmployeeCredential().getEmployer().getAddress()
                        .getHouseNumber());
                employerAddressMongoDbValue.setDoorNumber(
                    verification.getEmployeeCredential().getEmployer().getAddress()
                        .getDoorNumber());

                EmployerMongoDbValue employerMongoDbValue = new EmployerMongoDbValue();
                employerMongoDbValue.setAddress(employerAddressMongoDbValue);
                employerMongoDbValue
                    .setName(verification.getEmployeeCredential().getEmployer().getName());
                employerMongoDbValue.setSubject(
                    verification.getEmployeeCredential().getEmployer().getSubject());
                employerMongoDbValue.setProofOfOwnership(
                    verification.getEmployeeCredential().getEmployer().getProofOfOwnership());

                PositionMongoDbValue positionMongoDbValue = new PositionMongoDbValue();
                positionMongoDbValue
                    .setName(verification.getEmployeeCredential().getPosition().getName());

                employeeCredentialMongoDbValue
                    .setEmployeeId(verification.getEmployeeCredential().getEmployeeId());
                employeeCredentialMongoDbValue
                    .setEmployeeState(verification.getEmployeeCredential().getEmployeeState());
                employeeCredentialMongoDbValue.setPersona(personaMongoDbValue);
                employeeCredentialMongoDbValue.setContactInformation(
                    contactInformationMongoDbValue);
                employeeCredentialMongoDbValue.setIdentityManagement(
                    identityManagementMongoDbValue);
                employeeCredentialMongoDbValue.setEmployer(employerMongoDbValue);
                employeeCredentialMongoDbValue.setPosition(positionMongoDbValue);
            }

            document.setEmployeeCredentialMongoDbValue(employeeCredentialMongoDbValue);
            document.setCheckInDateTime(verification.getCheckInDateTime());

            return document;
        }
    }

    /**
     * Document to entity employee verification.
     *
     * @param document the document
     * @return the employee verification
     */
    public EmployeeVerification documentToEntity(EmployeeVerificationMongoDbDocument document) {
        if (document == null) {
            return null;
        } else {
            EmployeeVerification verification = new EmployeeVerification(document.getId());

            EmployeeCredential employeeCredential = new EmployeeCredential();
            if (document.getEmployeeCredentialMongoDbValue() != null) {

                Persona persona = null;
                if (document.getEmployeeCredentialMongoDbValue().getPersona() != null) {
                    persona = new Persona(
                        document.getEmployeeCredentialMongoDbValue().getPersona()
                            .getTitle(),
                        document.getEmployeeCredentialMongoDbValue().getPersona()
                            .getFirstName(),
                        document.getEmployeeCredentialMongoDbValue().getPersona()
                            .getLastName());
                }

                ContactInformation contactInformation = null;
                if (document.getEmployeeCredentialMongoDbValue().getContactInformation() != null) {
                    contactInformation =
                        new ContactInformation(document.getEmployeeCredentialMongoDbValue()
                            .getContactInformation().getEmails(),
                            document.getEmployeeCredentialMongoDbValue()
                                .getContactInformation().getPhoneNumbers());
                }

                IdentityManagement identityManagement = null;
                if (document.getEmployeeCredentialMongoDbValue().getIdentityManagement() != null) {
                    identityManagement =
                        new IdentityManagement(document.getEmployeeCredentialMongoDbValue()
                            .getIdentityManagement().getReference(),
                            document.getEmployeeCredentialMongoDbValue()
                                .getIdentityManagement().getUsername(),
                            document.getEmployeeCredentialMongoDbValue()
                                .getIdentityManagement().getEmail());
                }

                Employer employer = null;
                if (document.getEmployeeCredentialMongoDbValue().getEmployer() != null) {
                    Address employerAddress =
                        new Address(
                            document.getEmployeeCredentialMongoDbValue().getEmployer()
                                .getAddress().getPostalCode(),
                            document.getEmployeeCredentialMongoDbValue().getEmployer()
                                .getAddress().getCity(),
                            document.getEmployeeCredentialMongoDbValue().getEmployer()
                                .getAddress().getStreet());

                    employer =
                        new Employer(
                            document.getEmployeeCredentialMongoDbValue().getEmployer()
                                .getName(), employerAddress,
                            document.getEmployeeCredentialMongoDbValue().getEmployer()
                                .getSubject(),
                            document.getEmployeeCredentialMongoDbValue().getEmployer()
                                .getProofOfOwnership());
                }

                Position position = null;
                if (document.getEmployeeCredentialMongoDbValue().getPosition() != null) {
                    position =
                        new Position(
                            document.getEmployeeCredentialMongoDbValue().getPosition()
                                .getName());
                }

                employeeCredential.setPersona(persona);
                employeeCredential.setContactInformation(contactInformation);
                employeeCredential.setIdentityManagement(identityManagement);
                employeeCredential.setEmployer(employer);
                employeeCredential.setPosition(position);

                employeeCredential.setEmployeeId(
                    document.getEmployeeCredentialMongoDbValue().getEmployeeId());
                employeeCredential.setEmployeeState(
                    document.getEmployeeCredentialMongoDbValue().getEmployeeState());
            }

            verification.setEmployeeCredential(employeeCredential);

            verification.setProofId(document.getProofId());
            verification.setThreadId(document.getThreadId());
            verification.setLocationId(document.getLocationId());
            verification.setTerminalId(document.getTerminalId());
            verification.setState(EmployeeVerificationStatus.valueOf(document.getState()));
            verification.setProofState(document.getProofState());
            verification.setCheckInDateTime(document.getCheckInDateTime());

            return verification;
        }
    }
}
