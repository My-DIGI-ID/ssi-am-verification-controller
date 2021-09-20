package com.bka.ssi.controller.verification.company.infra.system.accreditation;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.bka.ssi.controller.verification.company.aop.configuration.system.AccreditationConfiguration;
import com.bka.ssi.controller.verification.company.services.exceptions.UnauthenticatedException;
import com.bka.ssi.controller.verification.company.services.exceptions.UnauthorizedException;
import com.bka.ssi.controller.verification.company.services.system.accreditation.dto.output.GuestAccreditationPrivateOutputDto;
import com.bka.ssi.controller.verification.company.services.system.accreditation.AccreditationClient;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Service
public class AccreditationHttpClient implements AccreditationClient {

    private final Logger logger;
    private final RestTemplate restTemplate;
    private final AccreditationConfiguration accreditationConfiguration;

    public AccreditationHttpClient(Logger logger,
        RestTemplateBuilder restTemplateBuilder,
        AccreditationConfiguration accreditationConfiguration) {
        this.logger = logger;
        this.restTemplate = restTemplateBuilder.build();
        this.accreditationConfiguration = accreditationConfiguration;
    }

    @Override
    public GuestAccreditationPrivateOutputDto getUniqueAccreditation(
        String referenceBasisId, String firstName, String lastName, String dateOfBirth,
        String companyName, Date validFromDate, Date validUntilDate, String invitedBy)
        throws UnauthenticatedException, UnauthorizedException {
        logger.debug("Attempting to get unique accreditation by query parameters");

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        headers.set("X-API-Key", accreditationConfiguration.getAccreditationApiKey());

        DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        
        UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl(
            accreditationConfiguration.getAccreditationApiUrl() + "/api/v2/accreditation/guest/private"
        )
            .queryParam("referenceBasisId", referenceBasisId)
            .queryParam("firstName", firstName)
            .queryParam("lastName", lastName)
            .queryParam("dateOfBirth", dateOfBirth)
            .queryParam("companyName", companyName)
            .queryParam("validFromDate", format.format(validFromDate))
            .queryParam("validUntilDate", format.format(validUntilDate))
            .queryParam("invitedBy", invitedBy);

        HttpEntity<?> entity = new HttpEntity<>(headers);

        try {
            ResponseEntity<GuestAccreditationPrivateOutputDto> response = restTemplate.exchange(
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
        headers.set("X-API-Key", accreditationConfiguration.getAccreditationApiKey());

        HttpEntity<?> entity = new HttpEntity<>(headers);

        String url = accreditationConfiguration.getAccreditationApiUrl() +
            "/api/v2/accreditation/guest/append/clean-guest-information/" +
            accreditationId;

        try {
            ResponseEntity<GuestAccreditationPrivateOutputDto> response = restTemplate.exchange(
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
}
