package com.bka.ssi.controller.verification.company.infra.db.mongo.repositories;

import com.bka.ssi.controller.verification.company.infra.db.mongo.documents.GuestVerificationMongoDbDocument;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface GuestVerificationMongoDbRepository
    extends MongoRepository<GuestVerificationMongoDbDocument, String> {

    List<GuestVerificationMongoDbDocument> findAllByLocationIdAndTerminalId(String locationId,
        String terminalId);

    Optional<GuestVerificationMongoDbDocument> findByThreadId(String threadId);

    Optional<GuestVerificationMongoDbDocument> findByAccreditationId(String accreditationId);
}
