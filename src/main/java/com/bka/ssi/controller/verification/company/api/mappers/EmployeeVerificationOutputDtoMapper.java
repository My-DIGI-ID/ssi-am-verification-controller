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

package com.bka.ssi.controller.verification.company.api.mappers;

import com.bka.ssi.controller.verification.company.api.v2.rest.dto.output.EmployeeCredentialOutputDto;
import com.bka.ssi.controller.verification.company.api.v2.rest.dto.output.EmployeeVerificationOutputDto;
import com.bka.ssi.controller.verification.company.services.models.EmployeeVerification;
import com.bka.ssi.controller.verification.company.services.models.common.ContactInformation;
import com.bka.ssi.controller.verification.company.services.models.common.Employer;
import com.bka.ssi.controller.verification.company.services.models.common.Persona;
import com.bka.ssi.controller.verification.company.services.models.credentials.EmployeeCredential;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * The type Employee verification output dto mapper.
 */
@Service
public class EmployeeVerificationOutputDtoMapper {

    /**
     * Entity to employee verification output dto.
     *
     * @param verification the verification
     * @return the employee verification output dto
     */
    public EmployeeVerificationOutputDto entityToDto(EmployeeVerification verification) {
        if (verification == null) {
            return null;
        } else {
            EmployeeVerificationOutputDto dto = new EmployeeVerificationOutputDto();

            dto.setId(verification.getId());

            EmployeeCredentialOutputDto credentialDto = new EmployeeCredentialOutputDto();

            if (verification.getEmployeeCredential() != null) {
                EmployeeCredential credential = verification.getEmployeeCredential();

                Persona persona = credential.getPersona();

                if (persona != null) {
                    credentialDto.setFirstName(persona.getFirstName());
                    credentialDto.setLastName(persona.getLastName());
                    credentialDto.setTitle(persona.getTitle());
                }

                ContactInformation contactInformation = credential.getContactInformation();

                if (contactInformation != null) {

                    String primaryPhoneNumber = null;
                    String secondaryPhoneNumber = null;

                    List<String> phoneNumbers =
                        contactInformation.getPhoneNumbers();

                    if (phoneNumbers.size() >= 1) {
                        primaryPhoneNumber = phoneNumbers.get(0);
                    }

                    if (phoneNumbers.size() >= 2) {
                        secondaryPhoneNumber = phoneNumbers.get(1);
                    }

                    credentialDto.setPrimaryPhoneNumber(primaryPhoneNumber);
                    credentialDto.setSecondaryPhoneNumber(secondaryPhoneNumber);

                    String email = null;

                    List<String> emails = contactInformation.getEmails();

                    if (emails.size() >= 1) {
                        email = emails.get(0);
                    }

                    credentialDto.setEmail(email);
                }

                credentialDto.setEmployeeState(credential.getEmployeeState());
                credentialDto.setEmployeeId(credential.getEmployeeId());

                if (credential.getPosition() != null) {
                    credentialDto.setPosition(credential.getPosition().getName());
                }

                Employer employer = credential.getEmployer();

                if (employer != null) {
                    credentialDto.setCompanyName(employer.getName());
                    credentialDto.setCompanyStreet(employer.getAddress().getStreet());
                    credentialDto.setCompanyCity(employer.getAddress().getCity());
                    credentialDto.setCompanyPostalCode(employer.getAddress().getPostalCode());
                }
            }

            dto.setEmployeeCredential(credentialDto);
            dto.setState(verification.getState());
            dto.setCheckInDateTime(verification.getCheckInDateTime());

            return dto;
        }
    }
}
