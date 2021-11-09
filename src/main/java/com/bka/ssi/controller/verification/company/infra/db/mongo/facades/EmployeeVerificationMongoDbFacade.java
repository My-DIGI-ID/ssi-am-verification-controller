package com.bka.ssi.controller.verification.company.infra.db.mongo.facades;

import com.bka.ssi.controller.verification.company.infra.db.mongo.documents.EmployeeVerificationMongoDbDocument;
import com.bka.ssi.controller.verification.company.infra.db.mongo.mappers.EmployeeVerificationMongoDbMapper;
import com.bka.ssi.controller.verification.company.infra.db.mongo.repositories.EmployeeVerificationMongoDbRepository;
import com.bka.ssi.controller.verification.company.services.models.EmployeeVerification;
import com.bka.ssi.controller.verification.company.services.repositories.EmployeeVerificationRepository;
import org.slf4j.Logger;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class EmployeeVerificationMongoDbFacade implements EmployeeVerificationRepository {

    private final EmployeeVerificationMongoDbRepository repository;
    private final EmployeeVerificationMongoDbMapper mapper;
    private final Logger logger;

    public EmployeeVerificationMongoDbFacade(
        EmployeeVerificationMongoDbRepository employeeVerificationMongoDbRepository,
        EmployeeVerificationMongoDbMapper employeeVerificationMongoDbMapper, Logger logger) {
        this.repository = employeeVerificationMongoDbRepository;
        this.mapper = employeeVerificationMongoDbMapper;
        this.logger = logger;
    }

    @Override
    public EmployeeVerification save(EmployeeVerification verification) {
        logger.debug("Saving employee verification");

        EmployeeVerificationMongoDbDocument document = mapper.entityToDocument(verification);
        EmployeeVerificationMongoDbDocument savedDocument = repository.save(document);

        EmployeeVerification savedVerification = mapper.documentToEntity(savedDocument);

        return savedVerification;
    }

    @Override
    public <S extends EmployeeVerification> Iterable<S> saveAll(Iterable<S> entities) {
        throw new UnsupportedOperationException("Operation saveAll is not implemented ");
    }

    @Override
    public Optional<EmployeeVerification> findById(String id) {
        logger.debug("Fetching employee verification with id " + id);

        Optional<EmployeeVerificationMongoDbDocument> document = repository.findById(id);

        EmployeeVerification verification = mapper.documentToEntity(document.get());

        return Optional.ofNullable(verification);
    }

    @Override
    public boolean existsById(String id) {
        throw new UnsupportedOperationException("Operation existsById is not implemented");
    }

    @Override
    public Iterable<EmployeeVerification> findAll() {
        logger.debug("Fetching all employee verifications");

        Iterable<EmployeeVerificationMongoDbDocument> documents = repository.findAll();

        List<EmployeeVerification> verifications = new ArrayList<>();
        documents.forEach(document -> verifications.add(mapper.documentToEntity(document)));

        return verifications;
    }

    @Override
    public Iterable<EmployeeVerification> findAllById(Iterable<String> strings) {
        throw new UnsupportedOperationException("Operation findAllById is not yet implemented");
    }

    @Override
    public List<EmployeeVerification> findAllByLocationIdAndTerminalId(
        String locationId, String terminalId) {
        logger.debug("Fetching all employee verifications with locationId " + locationId + " and " +
            "terminalId " + terminalId);

        List<EmployeeVerificationMongoDbDocument> documents =
            this.repository.findAllByLocationIdAndTerminalId(locationId, terminalId);

        List<EmployeeVerification> verifications = new ArrayList<>();
        documents.forEach(document -> verifications.add(this.mapper.documentToEntity(document)));

        return verifications;
    }

    @Override
    public Optional<EmployeeVerification> findByThreadId(String threadId) {
        logger.debug("Fetching employee verification with threadId " + threadId);

        Optional<EmployeeVerificationMongoDbDocument> document =
            repository.findByThreadId(threadId);

        EmployeeVerification verification = mapper.documentToEntity(document.orElse(null));

        return Optional.ofNullable(verification);
    }

    @Override
    public long count() {
        throw new UnsupportedOperationException("Operation is not implemented");
    }

    @Override
    public void deleteById(String id) {
        logger.debug("Deleting verification with id " + id);

        this.repository.deleteById(id);
    }

    @Override
    public void delete(
        EmployeeVerification verification) {
        throw new UnsupportedOperationException("Operation is not implemented");
    }

    @Override
    public void deleteAllById(Iterable<? extends String> ids) {
        throw new UnsupportedOperationException("Operation is not implemented");
    }

    @Override
    public void deleteAll(
        Iterable<? extends EmployeeVerification> entities) {
        throw new UnsupportedOperationException("Operation is not implemented");
    }

    @Override
    public void deleteAll() {
        throw new UnsupportedOperationException("Operation is not implemented");
    }
}
