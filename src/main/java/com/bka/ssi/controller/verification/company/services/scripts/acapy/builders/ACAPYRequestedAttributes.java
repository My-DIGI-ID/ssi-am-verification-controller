package com.bka.ssi.controller.verification.company.services.scripts.acapy.builders;

import com.bka.ssi.controller.verification.acapy_client.model.IndyProofReqAttrSpec;
import com.bka.ssi.controller.verification.company.services.scripts.AbstractNestedBuilder;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class ACAPYRequestedAttributes {

    public static final String MAIN_TYPE = "IndyProofReqAttrSpec";

    public static Builder newBuilder() {
        return new Builder();
    }

    public static final class Builder extends
        AbstractNestedBuilder<ACAPYProofRequest.Builder, Map<String, IndyProofReqAttrSpec>> {

        private Map<String, IndyProofReqAttrSpec> requestedAttributes;

        private Builder() {
            this.methodIdentifierForParent = MAIN_TYPE;
            this.init();
        }

        @Override
        public void init() {
            this.requestedAttributes = new HashMap<>();
        }

        @Override
        public Map<String, IndyProofReqAttrSpec> build() {
            return this.requestedAttributes;
        }

        public Builder withRequestedAttribute(String name, IndyProofReqAttrSpec attribute) {
            this.requestedAttributes.put(name, attribute);
            return this;
        }
    }
}
