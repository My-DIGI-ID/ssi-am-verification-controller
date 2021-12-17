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

package com.bka.ssi.controller.verification.company.services.system.accreditation;

import com.bka.ssi.controller.verification.company.services.enums.GuestVerificationStatus;
import com.bka.ssi.controller.verification.company.services.system.accreditation.dto.output.GuestAccreditationOpenOutputDto;
import com.bka.ssi.controller.verification.company.services.system.accreditation.dto.output.GuestAccreditationPrivateOutputDto;

import java.time.ZonedDateTime;

/**
 * The interface Accreditation client.
 */
public interface AccreditationClient {

    /**
     * Gets unique accreditation.
     *
     * @param referenceBasisId the reference basis id
     * @param firstName        the first name
     * @param lastName         the last name
     * @param dateOfBirth      the date of birth
     * @param companyName      the company name
     * @param validFrom        the valid from
     * @param validUntil       the valid until
     * @param invitedBy        the invited by
     * @return the unique accreditation
     * @throws Exception the exception
     */
    GuestAccreditationPrivateOutputDto getUniqueAccreditation(
        String referenceBasisId,
        String firstName,
        String lastName,
        String dateOfBirth,
        String companyName,
        ZonedDateTime validFrom,
        ZonedDateTime validUntil,
        String invitedBy
    ) throws Exception;

    /**
     * Cleanup accreditation guest accreditation private output dto.
     *
     * @param accreditationId the accreditation id
     * @return the guest accreditation private output dto
     * @throws Exception the exception
     */
    GuestAccreditationPrivateOutputDto cleanupAccreditation(String accreditationId)
        throws Exception;

    /**
     * Revoke accreditation guest accreditation open output dto.
     *
     * @param accreditationId the accreditation id
     * @return the guest accreditation open output dto
     * @throws Exception the exception
     */
    GuestAccreditationOpenOutputDto revokeAccreditation(String accreditationId) throws Exception;

    /**
     * Update accreditation guest accreditation open output dto.
     *
     * @param accreditationId the accreditation id
     * @param status          the status
     * @return the guest accreditation open output dto
     * @throws Exception the exception
     */
    GuestAccreditationOpenOutputDto updateAccreditation(String accreditationId,
        GuestVerificationStatus status) throws Exception;
}
