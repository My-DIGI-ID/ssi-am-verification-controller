package com.bka.ssi.controller.verification.company.services.system.accreditation;

import com.bka.ssi.controller.verification.company.services.enums.GuestVerificationStatus;
import com.bka.ssi.controller.verification.company.services.system.accreditation.dto.output.GuestAccreditationOpenOutputDto;
import com.bka.ssi.controller.verification.company.services.system.accreditation.dto.output.GuestAccreditationPrivateOutputDto;

import java.time.ZonedDateTime;

public interface AccreditationClient {

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

    GuestAccreditationPrivateOutputDto cleanupAccreditation(String accreditationId)
        throws Exception;

    GuestAccreditationOpenOutputDto revokeAccreditation(String accreditationId) throws Exception;

    GuestAccreditationOpenOutputDto updateAccreditation(String accreditationId,
        GuestVerificationStatus status) throws Exception;
}
