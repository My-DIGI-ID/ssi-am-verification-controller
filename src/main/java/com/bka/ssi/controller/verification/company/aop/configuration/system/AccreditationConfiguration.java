/*
 * Copyright 2021 Bundesrepublik Deutschland
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.bka.ssi.controller.verification.company.aop.configuration.system;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * The type Accreditation configuration.
 */
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

    /**
     * Gets accreditation api url.
     *
     * @return the accreditation api url
     */
    public String getAccreditationApiUrl() {
        return accreditationApiUrl;
    }

    /**
     * Gets accreditation api key.
     *
     * @return the accreditation api key
     */
    public String getAccreditationApiKey() {
        return accreditationApiKey;
    }

    /**
     * Gets accreditation api key header name.
     *
     * @return the accreditation api key header name
     */
    public String getAccreditationApiKeyHeaderName() {
        return accreditationApiKeyHeaderName;
    }

    /**
     * Gets ssl trust all.
     *
     * @return the ssl trust all
     */
    public boolean getSslTrustAll() {
        return sslTrustAll;
    }

    /**
     * Gets ssl verify hostname.
     *
     * @return the ssl verify hostname
     */
    public boolean getSslVerifyHostname() {
        return sslVerifyHostname;
    }
}
