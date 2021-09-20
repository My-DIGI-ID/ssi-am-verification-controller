package com.bka.ssi.controller.verification.company.services.scripts.acapy.builders;

import com.bka.ssi.controller.verification.acapy_client.model.IndyProofReqAttrSpec;
import com.bka.ssi.controller.verification.acapy_client.model.IndyProofReqPredSpec;
import com.bka.ssi.controller.verification.acapy_client.model.IndyProofRequest;
import com.bka.ssi.controller.verification.company.services.scripts.AbstractNestedBuilder;
import org.springframework.stereotype.Component;

import java.security.SecureRandom;
import java.util.HashMap;

@Component
public class ACAPYProofRequest {

    public static final String MAIN_TYPE = "IndyProofRequest";

    public static Builder newBuilder() {
        return new Builder();
    }

    /* TODO - Move to restrictions builder */
    protected enum RestrictionType {
        schema_id,
        schema_issuer_did,
        schema_name,
        schema_version,
        issuer_did,
        cred_def_id
    }

    public static final class Builder extends
        AbstractNestedBuilder<ACAPYConnectionlessProofRequestV10.Builder, IndyProofRequest> {

        private final SecureRandom secureRandom;
        /* TODO - BKAACMGT-125 - withParentBuilder should only be accessible within builders */
        private final ACAPYRequestedAttributes.Builder acapyRequestedAttributesBuilder =
            ACAPYRequestedAttributes.newBuilder().withParentBuilder(this);
        private final ACAPYRequestedPredicates.Builder acapyRequestedPredicatesBuilder =
            ACAPYRequestedPredicates.newBuilder().withParentBuilder(this);
        private IndyProofRequest indyProofRequest;

        private Builder() {
            this.secureRandom = new SecureRandom();
            this.methodIdentifierForParent = MAIN_TYPE;
            this.init();
        }

        @Override
        public void init() {
            this.indyProofRequest = new IndyProofRequest();
        }

        @Override
        public IndyProofRequest build() {
            return this.indyProofRequest;
        }

        public ACAPYRequestedAttributes.Builder addRequestedAttributes() {
            return this.acapyRequestedAttributesBuilder;
        }

        public ACAPYRequestedPredicates.Builder addRequestedPredicates() {
            return this.acapyRequestedPredicatesBuilder;
        }

        public Builder withName(String name) {
            this.indyProofRequest.setName(name);
            return this;
        }

        public Builder withNonce(String nonce) {
            /* TODO - Make AbstractACAPYProofRequest use String.valueOf(secureRandom.nextInt());
                as default */
            this.indyProofRequest.setNonce(nonce);
            return this;
        }

        public Builder withVersion(String version) {
            this.indyProofRequest.setVersion(version);
            return this;
        }

        public Builder withIndyProofReqAttrSpec(
            HashMap<String, IndyProofReqAttrSpec> requestedAttributes) {
            this.indyProofRequest.setRequestedAttributes(requestedAttributes);
            return this;
        }

        public Builder withIndyProofReqPredSpec(
            HashMap<String, IndyProofReqPredSpec> requestedPredicates) {
            this.indyProofRequest.setRequestedPredicates(requestedPredicates);
            return this;
        }
    }
}
