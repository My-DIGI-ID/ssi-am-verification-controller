package com.bka.ssi.controller.verification.company.infra.db.mongo.mappers;

import com.bka.ssi.controller.verification.company.infra.db.mongo.documents.GuestVerificationMongoDbDocument;
import com.bka.ssi.controller.verification.company.infra.db.mongo.values.BasisIdMongoDbValue;
import com.bka.ssi.controller.verification.company.infra.db.mongo.values.GuestCredentialMongoDbValue;
import com.bka.ssi.controller.verification.company.services.enums.GuestVerificationStatus;
import com.bka.ssi.controller.verification.company.services.models.GuestVerification;
import com.bka.ssi.controller.verification.company.services.models.common.BasisId;
import com.bka.ssi.controller.verification.company.services.models.credentials.GuestCredential;
import org.springframework.stereotype.Component;

@Component
/* TODO - Consider implicit mapping for reflected MongoDB Documents, then mapper is not needed */
public class GuestVerificationMongoDbMapper {

    public GuestVerificationMongoDbDocument entityToDocument(
        GuestVerification verification) {
        if (verification == null) {
            return null;
        } else {
            GuestVerificationMongoDbDocument document =
                new GuestVerificationMongoDbDocument();

            document.setId(verification.getId());
            document.setProofId(verification.getProofId());
            document.setAccreditationId(verification.getAccreditationId());
            document.setLocationId(verification.getLocationId());
            document.setTerminalId(verification.getTerminalId());
            document.setThreadId(verification.getThreadId());

            BasisIdMongoDbValue basisId = new BasisIdMongoDbValue();
            if (verification.getBasisId() != null) {
                basisId.setFirstName(verification.getBasisId().getFirstName());
                basisId.setFamilyName(verification.getBasisId().getFamilyName());
                basisId.setDateOfBirth(verification.getBasisId().getDateOfBirth());
                basisId.setDateOfExpiry(verification.getBasisId().getDateOfExpiry());
            }
            document.setBasisIdDbValue(basisId);

            GuestCredentialMongoDbValue guestCredential = new GuestCredentialMongoDbValue();
            if (verification.getGuestCredential() != null) {
                guestCredential.setFirstName(verification.getGuestCredential().getFirstName());
                guestCredential.setLastName(verification.getGuestCredential().getLastName());
                guestCredential.setTitle(verification.getGuestCredential().getTitle());
                guestCredential.setEmail(verification.getGuestCredential().getEmail());
                guestCredential.setPrimaryPhoneNumber(
                    verification.getGuestCredential().getPrimaryPhoneNumber());
                guestCredential.setSecondaryPhoneNumber(
                    verification.getGuestCredential().getSecondaryPhoneNumber());
                guestCredential.setCompanyName(verification.getGuestCredential().getCompanyName());
                guestCredential.setTypeOfVisit(verification.getGuestCredential().getTypeOfVisit());
                guestCredential.setLocation(verification.getGuestCredential().getLocation());
                guestCredential
                    .setValidFrom(verification.getGuestCredential().getValidFrom());
                guestCredential
                    .setValidUntil(verification.getGuestCredential().getValidUntil());
                guestCredential.setInvitedBy(verification.getGuestCredential().getInvitedBy());
                guestCredential.setDateOfBirth(verification.getGuestCredential().getDateOfBirth());
                guestCredential.setLicensePlateNumber(
                    verification.getGuestCredential().getLicensePlateNumber());
                guestCredential.setCompanyCity(verification.getGuestCredential().getCompanyCity());
                guestCredential
                    .setCompanyStreet(verification.getGuestCredential().getCompanyStreet());
                guestCredential
                    .setCompanyPostCode(verification.getGuestCredential().getCompanyPostCode());
                guestCredential
                    .setReferenceBasisId(verification.getGuestCredential().getReferenceBasisId());
                guestCredential.setReferenceEmployeeId(
                    verification.getGuestCredential().getReferenceEmployeeId());
                guestCredential.setCompanyProofOfOwnership(
                    verification.getGuestCredential().getCompanyProofOfOwnership());
                guestCredential.setDataEncryptionAlgorithm(
                    verification.getGuestCredential().getDataEncryptionAlgorithm());

            }
            document.setGuestCredentialDbValue(guestCredential);

            document.setCheckOutDateTime(verification.getCheckOutDateTime());
            document.setCheckInDateTime(verification.getCheckInDateTime());
            document.setState(verification.getState().getName());
            document.setProofState(verification.getProofState());

            return document;
        }
    }

    public GuestVerification documentToEntity(
        GuestVerificationMongoDbDocument document) {
        if (document == null) {
            return null;
        } else {
            BasisId basisId = new BasisId();
            if (document.getBasisIdDbValue() != null) {
                basisId.setFirstName(document.getBasisIdDbValue().getFirstName());
                basisId.setFamilyName(document.getBasisIdDbValue().getFamilyName());
                basisId.setDateOfBirth(document.getBasisIdDbValue().getDateOfBirth());
                basisId.setDateOfExpiry(document.getBasisIdDbValue().getDateOfExpiry());
            }

            GuestCredential guestCredential = new GuestCredential();
            if (document.getGuestCredentialDbValue() != null) {
                guestCredential.setFirstName(document.getGuestCredentialDbValue().getFirstName());
                guestCredential.setLastName(document.getGuestCredentialDbValue().getLastName());
                guestCredential.setTitle(document.getGuestCredentialDbValue().getTitle());
                guestCredential.setEmail(document.getGuestCredentialDbValue().getEmail());
                guestCredential.setPrimaryPhoneNumber(
                    document.getGuestCredentialDbValue().getPrimaryPhoneNumber());
                guestCredential.setSecondaryPhoneNumber(
                    document.getGuestCredentialDbValue().getSecondaryPhoneNumber());
                guestCredential
                    .setCompanyName(document.getGuestCredentialDbValue().getCompanyName());
                guestCredential
                    .setTypeOfVisit(document.getGuestCredentialDbValue().getTypeOfVisit());
                guestCredential.setLocation(document.getGuestCredentialDbValue().getLocation());
                guestCredential
                    .setValidFrom(document.getGuestCredentialDbValue().getValidFrom());
                guestCredential
                    .setValidUntil(document.getGuestCredentialDbValue().getValidUntil());
                guestCredential.setInvitedBy(document.getGuestCredentialDbValue().getInvitedBy());
                guestCredential
                    .setDateOfBirth(document.getGuestCredentialDbValue().getDateOfBirth());
                guestCredential.setLicensePlateNumber(
                    document.getGuestCredentialDbValue().getLicensePlateNumber());
                guestCredential
                    .setCompanyCity(document.getGuestCredentialDbValue().getCompanyCity());
                guestCredential
                    .setCompanyStreet(document.getGuestCredentialDbValue().getCompanyStreet());
                guestCredential
                    .setCompanyPostCode(document.getGuestCredentialDbValue().getCompanyPostCode());
                guestCredential.setReferenceBasisId(
                    document.getGuestCredentialDbValue().getReferenceBasisId());
                guestCredential.setReferenceEmployeeId(
                    document.getGuestCredentialDbValue().getReferenceEmployeeId());
                guestCredential.setCompanyProofOfOwnership(
                    document.getGuestCredentialDbValue().getCompanyProofOfOwnership());
                guestCredential.setDataEncryptionAlgorithm(
                    document.getGuestCredentialDbValue().getDataEncryptionAlgorithm());

            }

            GuestVerification verification = new GuestVerification(document.getId(),
                document.getAccreditationId(),
                document.getLocationId(),
                document.getTerminalId(),
                document.getThreadId(),
                document.getProofId(),
                basisId,
                guestCredential,
                document.getCheckInDateTime(),
                document.getCheckOutDateTime(),
                GuestVerificationStatus.valueOf(document.getState()),
                document.getProofState());

            return verification;
        }
    }
}
