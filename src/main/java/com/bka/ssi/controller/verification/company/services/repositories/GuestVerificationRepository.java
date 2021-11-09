package com.bka.ssi.controller.verification.company.services.repositories;

import com.bka.ssi.controller.verification.company.services.models.GuestVerification;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface GuestVerificationRepository extends CrudRepository<GuestVerification, String> {

    List<GuestVerification> findAllByLocationIdAndTerminalId(String locationId, String terminalId);

    Optional<GuestVerification> findByThreadId(String threadId);

    Optional<GuestVerification> findByAccreditationId(String accreditationId);
}
