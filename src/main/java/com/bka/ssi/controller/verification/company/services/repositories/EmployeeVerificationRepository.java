package com.bka.ssi.controller.verification.company.services.repositories;

import com.bka.ssi.controller.verification.company.services.models.EmployeeVerification;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EmployeeVerificationRepository
    extends CrudRepository<EmployeeVerification, String> {

    List<EmployeeVerification> findAllByLocationIdAndTerminalId(String locationId,
        String terminalId);

    Optional<EmployeeVerification> findByThreadId(String threadId);
}
