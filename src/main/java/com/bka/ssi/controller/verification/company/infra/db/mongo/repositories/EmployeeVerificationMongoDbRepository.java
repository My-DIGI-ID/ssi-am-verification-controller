package com.bka.ssi.controller.verification.company.infra.db.mongo.repositories;

import com.bka.ssi.controller.verification.company.infra.db.mongo.documents.EmployeeVerificationMongoDbDocument;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface EmployeeVerificationMongoDbRepository
    extends MongoRepository<EmployeeVerificationMongoDbDocument, String> {

    List<EmployeeVerificationMongoDbDocument> findAllByLocationIdAndTerminalId(String locationId,
        String terminalId);

    Optional<EmployeeVerificationMongoDbDocument> findByThreadId(String threadId);
}
