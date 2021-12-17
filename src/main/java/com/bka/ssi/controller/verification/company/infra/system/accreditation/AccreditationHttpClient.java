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

package com.bka.ssi.controller.verification.company.infra.system.accreditation;

import com.bka.ssi.controller.verification.company.aop.configuration.system.AccreditationConfiguration;
import com.bka.ssi.controller.verification.company.infra.system.accreditation.exception.AccreditationSSLException;
import com.bka.ssi.controller.verification.company.services.enums.GuestVerificationStatus;
import com.bka.ssi.controller.verification.company.services.exceptions.UnauthenticatedException;
import com.bka.ssi.controller.verification.company.services.exceptions.UnauthorizedException;
import com.bka.ssi.controller.verification.company.services.system.accreditation.AccreditationClient;
import com.bka.ssi.controller.verification.company.services.system.accreditation.dto.output.GuestAccreditationOpenOutputDto;
import com.bka.ssi.controller.verification.company.services.system.accreditation.dto.output.GuestAccreditationPrivateOutputDto;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.TrustStrategy;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.ssl.SSLContexts;
import org.slf4j.Logger;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.security.KeyManagementException;
import java.security.KeyStoreException;
import java.security.NoSuchAlgorithmException;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import javax.net.ssl.SSLContext;

/**
 * The type Accreditation http client.
 */
@Service
public class AccreditationHttpClient implements AccreditationClient {

    private final Logger logger;
    private final HttpComponentsClientHttpRequestFactory requestFactory;
    private final AccreditationConfiguration accreditationConfiguration;

    /**
     * Instantiates a new Accreditation http client.
     *
     * @param logger                     the logger
     * @param accreditationConfiguration the accreditation configuration
     * @throws AccreditationSSLException the accreditation ssl exception
     */
    public AccreditationHttpClient(Logger logger,
        AccreditationConfiguration accreditationConfiguration) throws AccreditationSSLException {
        this.logger = logger;
        this.accreditationConfiguration = accreditationConfiguration;

        HttpClientBuilder builder = HttpClients.custom();

        if (accreditationConfiguration.getSslTrustAll()) {
            try {
                TrustStrategy acceptingTrustStrategy = (x509Certificates, s) -> true;
                SSLContext sslContext =
                    SSLContexts.custom().loadTrustMaterial(null, acceptingTrustStrategy).build();

                builder = builder.setSSLContext(sslContext);
            } catch (NoSuchAlgorithmException | KeyStoreException | KeyManagementException e) {
                logger.error(e.getMessage());
                throw new AccreditationSSLException();
            }
        }

        if (!accreditationConfiguration.getSslVerifyHostname()) {
            builder.setSSLHostnameVerifier(new NoopHostnameVerifier());
        }

        CloseableHttpClient httpClient = builder.build();

        this.requestFactory = new HttpComponentsClientHttpRequestFactory();
        requestFactory.setHttpClient(httpClient);
    }

    @Override
    public GuestAccreditationPrivateOutputDto getUniqueAccreditation(
        String referenceBasisId, String firstName, String lastName, String dateOfBirth,
        String companyName, ZonedDateTime validFrom, ZonedDateTime validUntil, String invitedBy)
        throws UnauthenticatedException, UnauthorizedException {
        logger.debug("Attempting to get unique accreditation by query parameters");

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set(accreditationConfiguration.getAccreditationApiKeyHeaderName(),
            accreditationConfiguration.getAccreditationApiKey());

        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(
                accreditationConfiguration.getAccreditationApiUrl() +
                    "/api/v2/accreditation/guest/private"
            )
            .queryParam("referenceBasisId", referenceBasisId)
            .queryParam("firstName", firstName)
            .queryParam("lastName", lastName)
            .queryParam("dateOfBirth", dateOfBirth)
            .queryParam("companyName", companyName)
            .queryParam("validFrom", validFrom.format(DateTimeFormatter.ISO_OFFSET_DATE_TIME))
            .queryParam("validUntil", validUntil.format(DateTimeFormatter.ISO_OFFSET_DATE_TIME))
            .queryParam("invitedBy", invitedBy);

        HttpEntity<?> entity = new HttpEntity<>(headers);

        try {
            ResponseEntity<GuestAccreditationPrivateOutputDto> response =
                new RestTemplate(this.requestFactory).exchange(
                    builder.toUriString(),
                    HttpMethod.GET,
                    entity,
                    GuestAccreditationPrivateOutputDto.class);

            if (response
                .getStatusCode()
                .equals(HttpStatus.OK) && response.hasBody()) {
                logger.debug("Received successful response from accreditation controller");
                return response.getBody();
            }
        } catch (HttpClientErrorException.Unauthorized e) {
            /* ToDo - handle exceptions outside of client - restTemplate ErrorHandler interferes
                with RestControllerAdvice */
            logger.error(e.getMessage());
            throw new UnauthenticatedException();
        } catch (HttpClientErrorException.Forbidden e) {
            /* ToDo - handle exceptions outside of client - restTemplate ErrorHandler interferes
                with RestControllerAdvice */
            logger.error(e.getMessage());
            throw new UnauthorizedException();
        }

        return null;
    }

    @Override
    public GuestAccreditationPrivateOutputDto cleanupAccreditation(String accreditationId)
        throws UnauthenticatedException, UnauthorizedException {
        logger.debug("Cleaning up accreditation by accreditationId");

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set(accreditationConfiguration.getAccreditationApiKeyHeaderName(),
            accreditationConfiguration.getAccreditationApiKey());

        HttpEntity<?> entity = new HttpEntity<>(headers);

        String url = accreditationConfiguration.getAccreditationApiUrl()
            + "/api/v2/accreditation/guest/append/clean-guest-information/"
            + accreditationId;

        try {
            ResponseEntity<GuestAccreditationPrivateOutputDto> response =
                new RestTemplate(this.requestFactory).exchange(
                    url,
                    HttpMethod.PATCH,
                    entity,
                    GuestAccreditationPrivateOutputDto.class);

            if (response
                .getStatusCode()
                .equals(HttpStatus.OK) && response.hasBody()) {
                return response.getBody();
            }
        } catch (HttpClientErrorException.Unauthorized e) {
            /* ToDo - handle exceptions outside of client - restTemplate ErrorHandler interferes
                with RestControllerAdvice */
            logger.error(e.getMessage());
            throw new UnauthenticatedException();
        } catch (HttpClientErrorException.Forbidden e) {
            /* ToDo - handle exceptions outside of client - restTemplate ErrorHandler interferes
                with RestControllerAdvice */
            logger.error(e.getMessage());
            throw new UnauthorizedException();
        }

        return null;
    }

    @Override
    public GuestAccreditationOpenOutputDto revokeAccreditation(String accreditationId)
        throws Exception {
        logger.debug("Revoking accreditation by accreditationId");

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set(accreditationConfiguration.getAccreditationApiKeyHeaderName(),
            accreditationConfiguration.getAccreditationApiKey());

        HttpEntity<?> entity = new HttpEntity<>(headers);

        String url = accreditationConfiguration.getAccreditationApiUrl()
            + "/api/v2/accreditation/guest/revoke/checkout/"
            + accreditationId;

        try {
            ResponseEntity<GuestAccreditationOpenOutputDto> response =
                new RestTemplate(this.requestFactory).exchange(
                    url,
                    HttpMethod.PATCH,
                    entity,
                    GuestAccreditationOpenOutputDto.class);

            if (response
                .getStatusCode()
                .equals(HttpStatus.OK) && response.hasBody()) {
                return response.getBody();
            }
        } catch (HttpClientErrorException.Unauthorized e) {
            /* ToDo - handle exceptions outside of client - restTemplate ErrorHandler interferes
                with RestControllerAdvice */
            logger.error(e.getMessage());
            throw new UnauthenticatedException();
        } catch (HttpClientErrorException.Forbidden e) {
            /* ToDo - handle exceptions outside of client - restTemplate ErrorHandler interferes
                with RestControllerAdvice */
            logger.error(e.getMessage());
            throw new UnauthorizedException();
        }

        return null;
    }

    @Override
    public GuestAccreditationOpenOutputDto updateAccreditation(String accreditationId,
        GuestVerificationStatus status) throws Exception {
        logger.debug("Updating accreditation ");

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set(accreditationConfiguration.getAccreditationApiKeyHeaderName(),
            accreditationConfiguration.getAccreditationApiKey());

        HttpEntity<?> entity = new HttpEntity<>(headers);

        String url = accreditationConfiguration.getAccreditationApiUrl()
            + "/api/v2/accreditation/guest/update/"
            + accreditationId
            + "?status="
            + status.getName();

        try {
            ResponseEntity<GuestAccreditationOpenOutputDto> response =
                new RestTemplate(this.requestFactory).exchange(
                    url,
                    HttpMethod.PATCH,
                    entity,
                    GuestAccreditationOpenOutputDto.class);

            if (response
                .getStatusCode()
                .equals(HttpStatus.OK) && response.hasBody()) {
                return response.getBody();
            }
        } catch (HttpClientErrorException.Unauthorized e) {
            /* ToDo - handle exceptions outside of client - restTemplate ErrorHandler interferes
                with RestControllerAdvice */
            logger.error(e.getMessage());
            throw new UnauthenticatedException();
        } catch (HttpClientErrorException.Forbidden e) {
            /* ToDo - handle exceptions outside of client - restTemplate ErrorHandler interferes
                with RestControllerAdvice */
            logger.error(e.getMessage());
            throw new UnauthorizedException();
        }

        return null;
    }
}
