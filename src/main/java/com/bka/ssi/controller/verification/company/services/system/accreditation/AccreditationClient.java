package com.bka.ssi.controller.verification.company.services.system.accreditation;

import com.bka.ssi.controller.verification.company.services.system.accreditation.dto.output.GuestAccreditationPrivateOutputDto;

import java.util.Date;

public interface AccreditationClient {

    GuestAccreditationPrivateOutputDto getUniqueAccreditation(
        String referenceBasisId,
        String firstName,
        String lastName,
        String dateOfBirth,
        String companyName,
        Date validFromDate,
        Date validUntilDate,
        String invitedBy
    ) throws Exception;

    GuestAccreditationPrivateOutputDto cleanupAccreditation(String accreditationId) throws Exception;
}
