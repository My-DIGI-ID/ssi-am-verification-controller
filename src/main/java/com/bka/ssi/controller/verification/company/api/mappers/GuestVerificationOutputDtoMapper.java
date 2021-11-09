package com.bka.ssi.controller.verification.company.api.mappers;

import com.bka.ssi.controller.verification.company.api.v2.rest.dto.output.BasisIdOutputDto;
import com.bka.ssi.controller.verification.company.api.v2.rest.dto.output.GuestCredentialOutputDto;
import com.bka.ssi.controller.verification.company.api.v2.rest.dto.output.GuestVerificationOutputDto;
import com.bka.ssi.controller.verification.company.services.models.GuestVerification;
import com.bka.ssi.controller.verification.company.services.models.credentials.GuestCredential;
import org.springframework.stereotype.Service;

@Service
public class GuestVerificationOutputDtoMapper {

    public GuestVerificationOutputDto entityToDto(GuestVerification verification) {

        if (verification == null) {
            return null;
        } else {
            GuestVerificationOutputDto dto = new GuestVerificationOutputDto();

            dto.setId(verification.getId());

            BasisIdOutputDto basisIdDto = new BasisIdOutputDto();

            if (verification.getBasisId() != null) {

                basisIdDto.setFirstName(verification.getBasisId().getFirstName());
                basisIdDto.setFamilyName(verification.getBasisId().getFamilyName());
                basisIdDto.setDateOfBirth(verification.getBasisId().getDateOfBirth());
                basisIdDto.setDateOfExpiry(verification.getBasisId().getDateOfExpiry());
            }

            GuestCredentialOutputDto credentialDto = new GuestCredentialOutputDto();

            if (verification.getGuestCredential() != null) {
                GuestCredential credential = verification.getGuestCredential();

                credentialDto.setFirstName(credential.getFirstName());
                credentialDto.setLastName(credential.getLastName());
                credentialDto.setTitle(credential.getTitle());
                credentialDto.setEmail(credential.getEmail());
                credentialDto.setPrimaryPhoneNumber(credential.getPrimaryPhoneNumber());
                credentialDto.setSecondaryPhoneNumber(credential.getSecondaryPhoneNumber());
                credentialDto.setCompanyName(credential.getCompanyName());
                credentialDto.setTypeOfVisit(credential.getTypeOfVisit());
                credentialDto.setLocation(credential.getLocation());
                credentialDto.setValidFrom(credential.getValidFrom());
                credentialDto.setValidUntil(credential.getValidUntil());
                credentialDto.setInvitedBy(credential.getInvitedBy());
                credentialDto.setDateOfBirth(credential.getDateOfBirth());
                credentialDto.setLicensePlateNumber(credential.getLicensePlateNumber());
                credentialDto.setCompanyCity(credential.getCompanyCity());
                credentialDto.setCompanyStreet(credential.getCompanyStreet());
                credentialDto.setCompanyPostCode(credential.getCompanyPostCode());
                credentialDto.setReferenceBasisId(credential.getReferenceBasisId());
                credentialDto.setReferenceEmployeeId(credential.getReferenceEmployeeId());
                credentialDto.setCompanyProofOfOwnership(credential.getCompanyProofOfOwnership());
                credentialDto.setDataEncryptionAlgorithm(credential.getDataEncryptionAlgorithm());
            }

            dto.setBasisId(basisIdDto);
            dto.setGuestCredential(credentialDto);
            dto.setCheckOutDateTime(verification.getCheckOutDateTime());
            dto.setCheckInDateTime(verification.getCheckInDateTime());
            dto.setState(verification.getState());

            return dto;
        }
    }
}
