package com.bka.ssi.controller.verification.company.services.repositories;

import com.bka.ssi.controller.verification.company.services.models.Verification;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

public interface VerificationRepository extends CrudRepository<Verification, String> {
	
	default Iterable<Verification> findAllByLocationIdAndTerminalId(String locationId,
			String terminalId) {
		throw new UnsupportedOperationException();
	}
	
	default Optional<Verification> findByThreadId(String threadId) {
		throw new UnsupportedOperationException();
	}
	
	default Optional<Verification> findByAccreditationId(String accreditationId) {
		throw new UnsupportedOperationException();
	}
}
