package com.bka.ssi.controller.verification.company.services.scripts.acapy.webhooks;

import com.bka.ssi.controller.verification.company.services.scripts.acapy.dto.input.ACAPYPresentProofDto;

public interface WebhookService {

    public void handlePresentProof(ACAPYPresentProofDto inputDto) throws Exception;
}
