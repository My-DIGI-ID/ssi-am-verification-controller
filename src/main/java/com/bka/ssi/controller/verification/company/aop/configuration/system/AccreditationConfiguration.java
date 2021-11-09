package com.bka.ssi.controller.verification.company.aop.configuration.system;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AccreditationConfiguration {

    @Value("${accreditation.api.url}")
    private String accreditationApiUrl;

    @Value("${accreditation.api.api_key}")
    private String accreditationApiKey;

    @Value("${accreditation.api.api_key_header_name}")
    private String accreditationApiKeyHeaderName;

    @Value("${accreditation.api.ssl.trust_all}")
    private boolean sslTrustAll;

    @Value("${accreditation.api.ssl.verify_hostname}")
    private boolean sslVerifyHostname;

    public String getAccreditationApiUrl() {
        return accreditationApiUrl;
    }

    public String getAccreditationApiKey() {
        return accreditationApiKey;
    }

    public String getAccreditationApiKeyHeaderName() {
        return accreditationApiKeyHeaderName;
    }

    public boolean getSslTrustAll() {
        return sslTrustAll;
    }

    public boolean getSslVerifyHostname() {
        return sslVerifyHostname;
    }
}
