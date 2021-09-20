package com.bka.ssi.controller.verification.company.services;

import com.bka.ssi.controller.verification.company.services.security.facade.ProtectedTransaction;

import java.util.List;
import java.net.URI;

/* Transaction-Script design */
public interface VerificationService<T, S, U> {

    public final static String PERMISSION_RESOURCE_IDENTIFIER = "abstract";

    /* E.g. handles connectionless and connection-oriented proof request without proposal */
    /* TODO - Remove verificationId if verification is not added manually */
    URI handleProofRequest(String locationId, String terminalId) throws Exception;

    /* TODO - What is passed when proof is received - tbd */
    void handlePresentationAcknowledged(S proofReceipt) throws Exception;

    /* TODO - What is passed when proof is verified - tbd */
    void handleProofVerified(U proofVerified) throws Exception;

    List<Object> handleVerificationProcessComplete(String locationId, String terminalId)
        throws Exception;
}
