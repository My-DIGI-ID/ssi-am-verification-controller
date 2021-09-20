package com.bka.ssi.controller.verification.company.infra.db.mongo.facades;

import com.bka.ssi.controller.verification.company.infra.db.mongo.documents.VerificationMongoDBDocument;
import com.bka.ssi.controller.verification.company.infra.db.mongo.mappers.VerificationMongoDBMapper;
import com.bka.ssi.controller.verification.company.infra.db.mongo.repositories.VerificationRepositoryMongoDB;
import com.bka.ssi.controller.verification.company.services.models.Verification;
import com.bka.ssi.controller.verification.company.services.repositories.VerificationRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

@Service
/* TODO - Consider implicit mapping for reflected MongoDB Documents, then facade is not needed */
public class VerificationMongoDBFacade implements VerificationRepository {

    private final VerificationRepositoryMongoDB verificationRepositoryMongoDB;
    private final VerificationMongoDBMapper verificationMongoDBMapper;

    public VerificationMongoDBFacade(VerificationRepositoryMongoDB verificationRepositoryMongoDB,
        VerificationMongoDBMapper verificationMongoDBMapper) {
        this.verificationRepositoryMongoDB = verificationRepositoryMongoDB;
        this.verificationMongoDBMapper = verificationMongoDBMapper;
    }


    @Override
    public Verification save(Verification verification) {
        VerificationMongoDBDocument verificationMongoDBDocument =
            verificationMongoDBMapper.verificationToVerificationMongoDBDocument(verification);
        VerificationMongoDBDocument savedVerificationMongoDBDocument =
            verificationRepositoryMongoDB.save(verificationMongoDBDocument);

        // TODO - change to proper handling, but also look to avoid the mapper altogether
        Verification savedVerification = verificationMongoDBMapper
            .verificationMongoDBDocumentToVerification(savedVerificationMongoDBDocument);

        return savedVerification;
    }

    @Override
    public <S extends Verification> Iterable<S> saveAll(Iterable<S> iterable) {
        return null;
    }

    @Override
    public Optional<Verification> findById(String s) {
        Optional<VerificationMongoDBDocument> verificationMongoDBDocument =
            verificationRepositoryMongoDB.findById(s);

        // TODO - change to proper handling, but also look to avoid the mapper altogether
        Verification verification = verificationMongoDBMapper
            .verificationMongoDBDocumentToVerification(verificationMongoDBDocument.get());

        return Optional.of(verification);
    }

    @Override
    public boolean existsById(String s) {
        throw new UnsupportedOperationException("Operation is not yet implemented");
    }

    @Override
    public Iterable<Verification> findAll() {
        Iterable<VerificationMongoDBDocument> verificationMongoDBDocuments =
            verificationRepositoryMongoDB.findAll();
        List<Verification> verificationList = new ArrayList<>();

        // TODO - change to proper handling, but also look to avoid the mapper altogether
        verificationMongoDBDocuments.forEach(verificationMongoDBDocument ->
            verificationList.add(verificationMongoDBMapper
                .verificationMongoDBDocumentToVerification(verificationMongoDBDocument))
        );

        return verificationList;
    }

    @Override
    public Iterable<Verification> findAllById(Iterable<String> iterable) {
        throw new UnsupportedOperationException("Operation is not yet implemented");
    }
    
    @Override
    public Iterable<Verification> findAllByLocationIdAndTerminalId(String locationId, String terminalId) {
    	
    	Iterator<VerificationMongoDBDocument> iterator = verificationRepositoryMongoDB.findAll()
    			.iterator();
    	List<Verification> verificationList = new ArrayList<>();
    	
    	while(iterator.hasNext()) {
    		VerificationMongoDBDocument  verificationMongoDBDocument = iterator.next();
    		
    		if(locationId.equals(verificationMongoDBDocument.getLocationId())
    				&& terminalId.equals(verificationMongoDBDocument.getTerminalId())) {
    			verificationList.add(verificationMongoDBMapper.
    					verificationMongoDBDocumentToVerification(verificationMongoDBDocument));
    		}
    	}
    	
    	return verificationList;
    }
    
    @Override
    public Optional<Verification> findByThreadId(String threadId) {
    	
    	Iterator<Verification> iterator = this.findAll().iterator();
    	while(iterator.hasNext()) {
    		Verification verification = iterator.next();
    		
    		if(threadId.equals(verification.getThreadId())) {
    			return Optional.of(verification);
    		}
    	}
    	return null;
    }
    
    @Override
    public Optional<Verification> findByAccreditationId(String accreditationId) {
    	Iterator<Verification> iterator = this.findAll().iterator();
    	while(iterator.hasNext()) {
    		Verification verification = iterator.next();
    		
    		if(accreditationId.equals(verification.getAccreditationId())) {
    			return Optional.of(verification);
    		}
    	}
    	return Optional.ofNullable(null);
    }

    @Override
    public long count() {
        throw new UnsupportedOperationException("Operation is not yet implemented");
    }

    @Override
    public void deleteById(String s) {
        this.verificationRepositoryMongoDB.deleteById(s);
    }

    @Override
    public void delete(Verification verification) {
        throw new UnsupportedOperationException("Operation is not yet implemented");
    }

    @Override
    public void deleteAllById(Iterable<? extends String> iterable) {
        throw new UnsupportedOperationException("Operation is not yet implemented");
    }

    @Override
    public void deleteAll(Iterable<? extends Verification> iterable) {
        throw new UnsupportedOperationException("Operation is not yet implemented");
    }

    @Override
    public void deleteAll() {
        throw new UnsupportedOperationException("Operation is not yet implemented");
    }
}
