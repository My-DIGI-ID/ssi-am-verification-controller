package com.bka.ssi.controller.verification.company.infra.db.mongo.mappers;

import com.bka.ssi.controller.verification.company.infra.db.mongo.documents.VerificationMongoDBDocument;
import com.bka.ssi.controller.verification.company.infra.db.mongo.values.BasisIdDbValue;
import com.bka.ssi.controller.verification.company.infra.db.mongo.values.GuestCredentialDbValue;
import com.bka.ssi.controller.verification.company.services.models.Verification;
import com.bka.ssi.controller.verification.company.services.models.common.BasisId;
import com.bka.ssi.controller.verification.company.services.models.common.GuestCredential;

import org.springframework.stereotype.Component;

@Component
/* TODO - Consider implicit mapping for reflected MongoDB Documents, then mapper is not needed */
public class VerificationMongoDBMapper {

    public VerificationMongoDBDocument verificationToVerificationMongoDBDocument(
        Verification verification) {
        if (verification == null) {
            return null;
        } else {
            VerificationMongoDBDocument verificationMongoDBDocument =
                new VerificationMongoDBDocument();

            verificationMongoDBDocument.setId(verification.getId());
            verificationMongoDBDocument.setProofId(verification.getProofId());
            verificationMongoDBDocument.setAccreditationId(verification.getAccreditationId());
            verificationMongoDBDocument.setLocationId(verification.getLocationId());
            verificationMongoDBDocument.setTerminalId(verification.getTerminalId());
            verificationMongoDBDocument.setThreadId(verification.getThreadId());
            
            BasisIdDbValue basisId = new BasisIdDbValue();
            if(verification.getBasisId() != null) {
            	basisId.setFirstName(verification.getBasisId().getFirstName());
            	basisId.setFamilyName(verification.getBasisId().getFamilyName());
            	basisId.setDateOfBirth(verification.getBasisId().getDateOfBirth());
            	basisId.setDateOfExpiry(verification.getBasisId().getDateOfExpiry());
            }
            verificationMongoDBDocument.setBasisIdDbValue(basisId);
            
            GuestCredentialDbValue guestCredential = new GuestCredentialDbValue();
            if(verification.getGuestCredential() != null) {
            	guestCredential.setFirstName(verification.getGuestCredential().getFirstName());
            	guestCredential.setLastName(verification.getGuestCredential().getLastName());
            	guestCredential.setTitel(verification.getGuestCredential().getTitel());
            	guestCredential.setEmail(verification.getGuestCredential().getEmail());
            	guestCredential.setPrimaryPhoneNumber(verification.getGuestCredential().getPrimaryPhoneNumber());
            	guestCredential.setSecondaryPhoneNumber(verification.getGuestCredential().getSecondaryPhoneNumber());
            	guestCredential.setCompanyName(verification.getGuestCredential().getCompanyName());
            	guestCredential.setTypeOfVisit(verification.getGuestCredential().getTypeOfVisit());
            	guestCredential.setLocation(verification.getGuestCredential().getLocation());
            	guestCredential.setValidFromDate(verification.getGuestCredential().getValidFromDate());
            	guestCredential.setValidFromTime(verification.getGuestCredential().getValidFromTime());
            	guestCredential.setValidUntilDate(verification.getGuestCredential().getValidUntilDate());
            	guestCredential.setValidUntilTime(verification.getGuestCredential().getValidUntilTime());
            	guestCredential.setInvitedBy(verification.getGuestCredential().getInvitedBy());
            	guestCredential.setDateOfBirth(verification.getGuestCredential().getDateOfBirth());
            	guestCredential.setLicensePlateNumber(verification.getGuestCredential().getLicensePlateNumber());
            	guestCredential.setCompanyCity(verification.getGuestCredential().getCompanyCity());
            	guestCredential.setCompanyStreet(verification.getGuestCredential().getCompanyStreet());
            	guestCredential.setCompanyPostCode(verification.getGuestCredential().getCompanyPostCode());
            	guestCredential.setReferenceBasisId(verification.getGuestCredential().getReferenceBasisId());
            	guestCredential.setReferenceEmployeeId(verification.getGuestCredential().getReferenceEmployeeId());
            	guestCredential.setCompanyProofOfOwnership(verification.getGuestCredential().getCompanyProofOfOwnership());
            	guestCredential.setDataEncryptionAlgorithm(verification.getGuestCredential().getDataEncryptionAlgorithm());
            	
            }
            verificationMongoDBDocument.setGuestCredentialDbValue(guestCredential);
            
            verificationMongoDBDocument.setCheckOutDateTime(verification.getCheckOutDateTime());
            verificationMongoDBDocument.setCheckInDateTime(verification.getCheckInDateTime());
            verificationMongoDBDocument.setState(verification.getState());
            verificationMongoDBDocument.setProofState(verification.getProofState());
            
            return verificationMongoDBDocument;
        }
    }

    public Verification verificationMongoDBDocumentToVerification(
        VerificationMongoDBDocument verificationMongoDBDocument) {
        if (verificationMongoDBDocument == null) {
            return null;
        } else {
            Verification verification = new Verification(verificationMongoDBDocument.getId(),
            		verificationMongoDBDocument.getAccreditationId());
            
            verification.setProofId(verificationMongoDBDocument.getProofId());
            verification.setThreadId(verificationMongoDBDocument.getThreadId());
            verification.setLocationId(verificationMongoDBDocument.getLocationId());
            verification.setTerminalId(verificationMongoDBDocument.getTerminalId());
            
            BasisId basisId = new BasisId();
            if(verificationMongoDBDocument.getBasisIdDbValue() != null) {
            	basisId.setFirstName(verificationMongoDBDocument.getBasisIdDbValue().getFirstName());
            	basisId.setFamilyName(verificationMongoDBDocument.getBasisIdDbValue().getFamilyName());
            	basisId.setDateOfBirth(verificationMongoDBDocument.getBasisIdDbValue().getDateOfBirth());
            	basisId.setDateOfExpiry(verificationMongoDBDocument.getBasisIdDbValue().getDateOfExpiry());
            }
            verification.setBasisId(basisId);
            
            GuestCredential guestCredential = new GuestCredential();
            if(verificationMongoDBDocument.getGuestCredentialDbValue() != null) {
            	guestCredential.setFirstName(verificationMongoDBDocument.getGuestCredentialDbValue().getFirstName());
            	guestCredential.setLastName(verificationMongoDBDocument.getGuestCredentialDbValue().getLastName());
            	guestCredential.setTitel(verificationMongoDBDocument.getGuestCredentialDbValue().getTitel());
            	guestCredential.setEmail(verificationMongoDBDocument.getGuestCredentialDbValue().getEmail());
            	guestCredential.setPrimaryPhoneNumber(verificationMongoDBDocument.getGuestCredentialDbValue().getPrimaryPhoneNumber());
            	guestCredential.setSecondaryPhoneNumber(verificationMongoDBDocument.getGuestCredentialDbValue().getSecondaryPhoneNumber());
            	guestCredential.setCompanyName(verificationMongoDBDocument.getGuestCredentialDbValue().getCompanyName());
            	guestCredential.setTypeOfVisit(verificationMongoDBDocument.getGuestCredentialDbValue().getTypeOfVisit());
            	guestCredential.setLocation(verificationMongoDBDocument.getGuestCredentialDbValue().getLocation());
            	guestCredential.setValidFromDate(verificationMongoDBDocument.getGuestCredentialDbValue().getValidFromDate());
            	guestCredential.setValidFromTime(verificationMongoDBDocument.getGuestCredentialDbValue().getValidFromTime());
            	guestCredential.setValidUntilDate(verificationMongoDBDocument.getGuestCredentialDbValue().getValidUntilDate());
            	guestCredential.setValidUntilTime(verificationMongoDBDocument.getGuestCredentialDbValue().getValidUntilTime());
            	guestCredential.setInvitedBy(verificationMongoDBDocument.getGuestCredentialDbValue().getInvitedBy());
            	guestCredential.setDateOfBirth(verificationMongoDBDocument.getGuestCredentialDbValue().getDateOfBirth());
            	guestCredential.setLicensePlateNumber(verificationMongoDBDocument.getGuestCredentialDbValue().getLicensePlateNumber());
            	guestCredential.setCompanyCity(verificationMongoDBDocument.getGuestCredentialDbValue().getCompanyCity());
            	guestCredential.setCompanyStreet(verificationMongoDBDocument.getGuestCredentialDbValue().getCompanyStreet());
            	guestCredential.setCompanyPostCode(verificationMongoDBDocument.getGuestCredentialDbValue().getCompanyPostCode());
            	guestCredential.setReferenceBasisId(verificationMongoDBDocument.getGuestCredentialDbValue().getReferenceBasisId());
            	guestCredential.setReferenceEmployeeId(verificationMongoDBDocument.getGuestCredentialDbValue().getReferenceEmployeeId());
            	guestCredential.setCompanyProofOfOwnership(verificationMongoDBDocument.getGuestCredentialDbValue().getCompanyProofOfOwnership());
            	guestCredential.setDataEncryptionAlgorithm(verificationMongoDBDocument.getGuestCredentialDbValue().getDataEncryptionAlgorithm());
            	
            }
            verification.setGuestCredential(guestCredential);
            
            verification.setCheckOutDateTime(verificationMongoDBDocument.getCheckOutDateTime());
            verification.setCheckInDateTime(verificationMongoDBDocument.getCheckInDateTime());
            verification.setState(verificationMongoDBDocument.getState());
            verification.setProofState(verificationMongoDBDocument.getProofState());
            
            return verification;
        }
    }
}
