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

package com.bka.ssi.controller.verification.company.infra.db.mongo.facades;

import com.bka.ssi.controller.verification.company.infra.db.mongo.documents.GuestVerificationMongoDbDocument;
import com.bka.ssi.controller.verification.company.infra.db.mongo.mappers.GuestVerificationMongoDbMapper;
import com.bka.ssi.controller.verification.company.infra.db.mongo.repositories.GuestVerificationMongoDbRepository;
import com.bka.ssi.controller.verification.company.services.models.GuestVerification;
import com.bka.ssi.controller.verification.company.services.repositories.GuestVerificationRepository;
import org.slf4j.Logger;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * The type Guest verification mongo db facade.
 */
@Service
/* TODO - Consider implicit mapping for reflected MongoDB Documents, then facade is not needed */
public class GuestVerificationMongoDbFacade implements GuestVerificationRepository {

    private final GuestVerificationMongoDbRepository repository;
    private final GuestVerificationMongoDbMapper mapper;
    private final Logger logger;

    /**
     * Instantiates a new Guest verification mongo db facade.
     *
     * @param guestVerificationMongoDbRepository the guest verification mongo db repository
     * @param guestVerificationMongoDBMapper     the guest verification mongo db mapper
     * @param logger                             the logger
     */
    public GuestVerificationMongoDbFacade(
        GuestVerificationMongoDbRepository guestVerificationMongoDbRepository,
        GuestVerificationMongoDbMapper guestVerificationMongoDBMapper, Logger logger) {
        this.repository = guestVerificationMongoDbRepository;
        this.mapper = guestVerificationMongoDBMapper;
        this.logger = logger;
    }

    @Override
    public GuestVerification save(GuestVerification verification) {
        logger.debug("Saving guest verification");

        GuestVerificationMongoDbDocument document = mapper.entityToDocument(verification);
        GuestVerificationMongoDbDocument savedDocument = repository.save(document);

        GuestVerification savedVerification = mapper.documentToEntity(savedDocument);

        return savedVerification;
    }

    @Override
    public <S extends GuestVerification> Iterable<S> saveAll(Iterable<S> iterable) {
        throw new UnsupportedOperationException("Operation saveAll is not yet implemented");
    }

    @Override
    public Optional<GuestVerification> findById(String id) {
        logger.debug("Fetching guest verification with id " + id);

        Optional<GuestVerificationMongoDbDocument> document = repository.findById(id);

        GuestVerification verification = mapper.documentToEntity(document.get());

        return Optional.ofNullable(verification);
    }

    @Override
    public boolean existsById(String id) {
        throw new UnsupportedOperationException("Operation existsById is not yet implemented");
    }

    @Override
    public Iterable<GuestVerification> findAll() {
        logger.debug("Fetching all guest verifications");

        Iterable<GuestVerificationMongoDbDocument> documents = repository.findAll();

        List<GuestVerification> verifications = new ArrayList<>();
        documents.forEach(document -> verifications.add(mapper.documentToEntity(document)));

        return verifications;
    }

    @Override
    public Iterable<GuestVerification> findAllById(Iterable<String> iterable) {
        throw new UnsupportedOperationException("Operation findAllById is not yet implemented");
    }

    @Override
    public List<GuestVerification> findAllByLocationIdAndTerminalId(String locationId,
        String terminalId) {
        logger.debug("Fetching all verifications with locationId " + locationId + " and " +
            "terminalId " + terminalId);

        List<GuestVerificationMongoDbDocument> documents =
            this.repository.findAllByLocationIdAndTerminalId(locationId, terminalId);

        List<GuestVerification> verifications = new ArrayList<>();
        documents.forEach(document -> verifications.add(this.mapper.documentToEntity(document)));

        return verifications;
    }

    @Override
    public Optional<GuestVerification> findByThreadId(String threadId) {
        logger.debug("Fetching verification with threadId " + threadId);

        Optional<GuestVerificationMongoDbDocument> document = repository.findByThreadId(threadId);

        GuestVerification verification = mapper.documentToEntity(document.orElse(null));

        return Optional.ofNullable(verification);
    }

    @Override
    public Optional<GuestVerification> findByAccreditationId(String accreditationId) {
        logger.debug("Fetching verification with accreditationId " + accreditationId);

        Optional<GuestVerificationMongoDbDocument> document =
            repository.findByAccreditationId(accreditationId);

        if (document.isEmpty()){
            logger.debug("No verification found for this accreditation");
            return Optional.ofNullable(null);
        }
        logger.debug("Verification found for this accreditation");
        GuestVerification verification = mapper.documentToEntity(document.get());

        return Optional.of(verification);
    }

    @Override
    public long count() {
        throw new UnsupportedOperationException("Operation is not yet implemented");
    }

    @Override
    public void deleteById(String id) {
        logger.debug("Deleting verification with id " + id);

        this.repository.deleteById(id);
    }

    @Override
    public void delete(GuestVerification verification) {
        throw new UnsupportedOperationException("Operation is not yet implemented");
    }

    @Override
    public void deleteAllById(Iterable<? extends String> iterable) {
        throw new UnsupportedOperationException("Operation is not yet implemented");
    }

    @Override
    public void deleteAll(Iterable<? extends GuestVerification> iterable) {
        throw new UnsupportedOperationException("Operation is not yet implemented");
    }

    @Override
    public void deleteAll() {
        throw new UnsupportedOperationException("Operation is not yet implemented");
    }
}
