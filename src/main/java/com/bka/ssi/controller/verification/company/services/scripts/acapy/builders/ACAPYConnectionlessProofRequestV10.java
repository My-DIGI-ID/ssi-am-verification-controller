package com.bka.ssi.controller.verification.company.services.scripts.acapy.builders;

import com.bka.ssi.controller.verification.acapy_client.model.IndyProofRequest;
import com.bka.ssi.controller.verification.acapy_client.model.V10PresentationCreateRequestRequest;
import com.bka.ssi.controller.verification.company.services.scripts.Buildable;
import org.springframework.stereotype.Component;

@Component
public class ACAPYConnectionlessProofRequestV10 {

    public static final String MAIN_TYPE = "V10PresentationCreateRequestRequest";

    public static Builder newBuilder() {
        return new Builder();
    }

    public static final class Builder implements Buildable<V10PresentationCreateRequestRequest> {

        /* TODO - BKAACMGT-125 - withParentBuilder should only be accessible within builders */
        private final ACAPYProofRequest.Builder acapyProofRequestBuilder =
            ACAPYProofRequest.newBuilder().withParentBuilder(this);
        private V10PresentationCreateRequestRequest v10PresentationCreateRequestRequest;

        private Builder() {
            this.init();
        }

        @Override
        public void init() {
            this.v10PresentationCreateRequestRequest = new V10PresentationCreateRequestRequest();
        }

        @Override
        public V10PresentationCreateRequestRequest build() {
            return this.v10PresentationCreateRequestRequest;
        }

        public ACAPYProofRequest.Builder addIndyProofRequest() {
            return this.acapyProofRequestBuilder;
        }

        public Builder withComment(String comment) {
            this.v10PresentationCreateRequestRequest.setComment(comment);
            return this;
        }

        public Builder withTrace(Boolean trace) {
            this.v10PresentationCreateRequestRequest.setTrace(trace);
            return this;
        }

        public Builder withIndyProofRequest(IndyProofRequest proofRequest) {
            this.v10PresentationCreateRequestRequest.setProofRequest(proofRequest);
            return this;
        }
    }
}
