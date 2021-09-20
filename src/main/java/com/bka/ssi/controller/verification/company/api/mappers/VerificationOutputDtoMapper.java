package com.bka.ssi.controller.verification.company.api.mappers;

import org.springframework.stereotype.Service;

import com.bka.ssi.controller.verification.company.api.v2.rest.dto.output.BasisIdOutputDto;
import com.bka.ssi.controller.verification.company.api.v2.rest.dto.output.GuestCredentialOutputDto;
import com.bka.ssi.controller.verification.company.api.v2.rest.dto.output.VerificationOutputDto;
import com.bka.ssi.controller.verification.company.services.models.Verification;
import com.bka.ssi.controller.verification.company.services.models.common.GuestCredential;

@Service
public class VerificationOutputDtoMapper {
	
	public VerificationOutputDto toVerificationOutputDto(Verification verification) {
		
		if(verification == null) {
			return null;
		} else {
			VerificationOutputDto output = new VerificationOutputDto();
			
			output.setId(verification.getId());

            BasisIdOutputDto basisIdOutput = new BasisIdOutputDto();
			
			if(verification.getBasisId() != null) {
				
				basisIdOutput.setFirstName(verification.getBasisId().getFirstName());
				basisIdOutput.setFamilyName(verification.getBasisId().getFamilyName());
				basisIdOutput.setDateOfBirth(verification.getBasisId().getDateOfBirth());
				basisIdOutput.setDateOfExpiry(verification.getBasisId().getDateOfExpiry());
			}

            GuestCredentialOutputDto credentialOutput = new GuestCredentialOutputDto();
			
			if(verification.getGuestCredential() != null) {
				GuestCredential credential = verification.getGuestCredential();
				
				credentialOutput.setFirstName(credential.getFirstName());
				credentialOutput.setLastName(credential.getLastName());
				credentialOutput.setTitel(credential.getTitel());
				credentialOutput.setEmail(credential.getEmail());
				credentialOutput.setPrimaryPhoneNumber(credential.getPrimaryPhoneNumber());
				credentialOutput.setSecondaryPhoneNumber(credential.getSecondaryPhoneNumber());
				credentialOutput.setCompanyName(credential.getCompanyName());
				credentialOutput.setTypeOfVisit(credential.getTypeOfVisit());
				credentialOutput.setLocation(credential.getLocation());
				credentialOutput.setValidFromDate(credential.getValidFromDate());
				credentialOutput.setValidFromTime(credential.getValidFromTime());
				credentialOutput.setValidUntilDate(credential.getValidUntilDate());
				credentialOutput.setValidUntilTime(credential.getValidUntilTime());
				credentialOutput.setInvitedBy(credential.getInvitedBy());
				credentialOutput.setDateOfBirth(credential.getDateOfBirth());
				credentialOutput.setLicensePlateNumber(credential.getLicensePlateNumber());
				credentialOutput.setCompanyCity(credential.getCompanyCity());
				credentialOutput.setCompanyStreet(credential.getCompanyStreet());
				credentialOutput.setCompanyPostCode(credential.getCompanyPostCode());
				credentialOutput.setReferenceBasisId(credential.getReferenceBasisId());
				credentialOutput.setReferenceEmployeeId(credential.getReferenceEmployeeId());
				credentialOutput.setCompanyProofOfOwnership(credential.getCompanyProofOfOwnership());
				credentialOutput.setDataEncryptionAlgorithm(credential.getDataEncryptionAlgorithm());
			}

            output.setBasisId(basisIdOutput);
            output.setGuestCredential(credentialOutput);
			output.setCheckOutDateTime(verification.getCheckOutDateTime());
			output.setCheckInDateTime(verification.getCheckInDateTime());
			output.setState(verification.getState());
			
			return output;
		}
	}
}
