package com.bka.ssi.controller.verification.company.services;

import com.bka.ssi.controller.verification.company.services.models.Verification;
import com.bka.ssi.controller.verification.company.services.scripts.acapy.dto.input.ACAPYPresentProofDto;

import java.net.URI;
import java.util.List;

/* Transaction-Script design */
public interface VerificationService<T extends Verification> {

    URI handleProofRequest(String locationId, String terminalId) throws Exception;

    void handlePresentationAcknowledged(ACAPYPresentProofDto proofReceipt) throws Exception;

    void handleProofVerified(ACAPYPresentProofDto proofVerified) throws Exception;

    List<T> handleVerificationProcessComplete(String locationId, String terminalId)
        throws Exception;
}
