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

package com.bka.ssi.controller.verification.company.services.repositories;

import com.bka.ssi.controller.verification.company.services.models.GuestVerification;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * The interface Guest verification repository.
 */
@Repository
public interface GuestVerificationRepository extends CrudRepository<GuestVerification, String> {

    /**
     * Find all by location id and terminal id list.
     *
     * @param locationId the location id
     * @param terminalId the terminal id
     * @return the list
     */
    List<GuestVerification> findAllByLocationIdAndTerminalId(String locationId, String terminalId);

    /**
     * Find by thread id optional.
     *
     * @param threadId the thread id
     * @return the optional
     */
    Optional<GuestVerification> findByThreadId(String threadId);

    /**
     * Find by accreditation id optional.
     *
     * @param accreditationId the accreditation id
     * @return the optional
     */
    Optional<GuestVerification> findByAccreditationId(String accreditationId);
}
