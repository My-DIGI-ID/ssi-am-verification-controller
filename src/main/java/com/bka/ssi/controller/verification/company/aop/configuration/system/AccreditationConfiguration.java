package com.bka.ssi.controller.verification.company.aop.configuration.system;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AccreditationConfiguration {

    @Value("${accreditation.api.url}")
    private String accreditationApiUrl;

    @Value("${accreditation.api.key}")
    private String accreditationApiKey;

    public String getAccreditationApiUrl() {
        return accreditationApiUrl;
    }

    public String getAccreditationApiKey() {
        return accreditationApiKey;
    }
}
