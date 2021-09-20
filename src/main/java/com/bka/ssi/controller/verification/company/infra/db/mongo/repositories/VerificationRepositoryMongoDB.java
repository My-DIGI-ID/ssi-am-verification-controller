package com.bka.ssi.controller.verification.company.infra.db.mongo.repositories;

import com.bka.ssi.controller.verification.company.infra.db.mongo.documents.VerificationMongoDBDocument;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VerificationRepositoryMongoDB
    extends CrudRepository<VerificationMongoDBDocument, String> {
}
