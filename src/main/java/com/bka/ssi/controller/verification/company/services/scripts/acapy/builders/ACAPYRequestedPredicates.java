package com.bka.ssi.controller.verification.company.services.scripts.acapy.builders;

import com.bka.ssi.controller.verification.acapy_client.model.IndyProofReqPredSpec;
import com.bka.ssi.controller.verification.company.services.scripts.AbstractNestedBuilder;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class ACAPYRequestedPredicates {

    public static final String MAIN_TYPE = "IndyProofReqPredSpec";

    public static Builder newBuilder() {
        return new Builder();
    }

    public static final class Builder extends
        AbstractNestedBuilder<ACAPYProofRequest.Builder, Map<String, IndyProofReqPredSpec>> {

        private Map<String, IndyProofReqPredSpec> requestedPredicates;

        private Builder() {
            this.methodIdentifierForParent = MAIN_TYPE;
            this.init();
        }

        @Override
        public void init() {
            this.requestedPredicates = new HashMap<>();
        }

        @Override
        public Map<String, IndyProofReqPredSpec> build() {
            return this.requestedPredicates;
        }

        public Builder withRequestedPredicate(String name, IndyProofReqPredSpec predicate) {
            this.requestedPredicates.put(name, predicate);
            return this;
        }
    }
}
