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

package com.bka.ssi.controller.verification.company.services.scripts.acapy.utilities;

import static com.bka.ssi.controller.verification.company.aop.configuration.agents.CredentialsConfiguration.BASIS_ID_ATTRIBUTE_NAME;
import static com.bka.ssi.controller.verification.company.aop.configuration.agents.CredentialsConfiguration.BASIS_ID_DATE_OF_BIRTH;
import static com.bka.ssi.controller.verification.company.aop.configuration.agents.CredentialsConfiguration.BASIS_ID_DATE_OF_EXPIRY;
import static com.bka.ssi.controller.verification.company.aop.configuration.agents.CredentialsConfiguration.BASIS_ID_FAMILY_NAME;
import static com.bka.ssi.controller.verification.company.aop.configuration.agents.CredentialsConfiguration.BASIS_ID_FIRST_NAME;
import static com.bka.ssi.controller.verification.company.aop.configuration.agents.CredentialsConfiguration.DATE_FORMAT;
import static com.bka.ssi.controller.verification.company.aop.configuration.agents.CredentialsConfiguration.EMPLOYEE_CRED_ATTRIBUTE_NAME;
import static com.bka.ssi.controller.verification.company.aop.configuration.agents.CredentialsConfiguration.EMPLOYEE_CRED_COMPANY_CITY;
import static com.bka.ssi.controller.verification.company.aop.configuration.agents.CredentialsConfiguration.EMPLOYEE_CRED_COMPANY_NAME;
import static com.bka.ssi.controller.verification.company.aop.configuration.agents.CredentialsConfiguration.EMPLOYEE_CRED_COMPANY_POST_CODE;
import static com.bka.ssi.controller.verification.company.aop.configuration.agents.CredentialsConfiguration.EMPLOYEE_CRED_COMPANY_PROOF_OF_OWNERSHIP;
import static com.bka.ssi.controller.verification.company.aop.configuration.agents.CredentialsConfiguration.EMPLOYEE_CRED_COMPANY_STREET;
import static com.bka.ssi.controller.verification.company.aop.configuration.agents.CredentialsConfiguration.EMPLOYEE_CRED_EMAIL;
import static com.bka.ssi.controller.verification.company.aop.configuration.agents.CredentialsConfiguration.EMPLOYEE_CRED_EMPLOYEE_ID;
import static com.bka.ssi.controller.verification.company.aop.configuration.agents.CredentialsConfiguration.EMPLOYEE_CRED_EMPLOYEE_STATE;
import static com.bka.ssi.controller.verification.company.aop.configuration.agents.CredentialsConfiguration.EMPLOYEE_CRED_FIRST_NAME;
import static com.bka.ssi.controller.verification.company.aop.configuration.agents.CredentialsConfiguration.EMPLOYEE_CRED_LAST_NAME;
import static com.bka.ssi.controller.verification.company.aop.configuration.agents.CredentialsConfiguration.EMPLOYEE_CRED_POSITION;
import static com.bka.ssi.controller.verification.company.aop.configuration.agents.CredentialsConfiguration.EMPLOYEE_CRED_PRIMARY_PHONE_NUMBER;
import static com.bka.ssi.controller.verification.company.aop.configuration.agents.CredentialsConfiguration.EMPLOYEE_CRED_SECONDARY_PHONE_NUMBER;
import static com.bka.ssi.controller.verification.company.aop.configuration.agents.CredentialsConfiguration.EMPLOYEE_CRED_SSO_REFERENCE;
import static com.bka.ssi.controller.verification.company.aop.configuration.agents.CredentialsConfiguration.EMPLOYEE_CRED_TITLE;
import static com.bka.ssi.controller.verification.company.aop.configuration.agents.CredentialsConfiguration.GUEST_CRED_ATTRIBUTE_NAME;
import static com.bka.ssi.controller.verification.company.aop.configuration.agents.CredentialsConfiguration.GUEST_CRED_COMPANY_CITY;
import static com.bka.ssi.controller.verification.company.aop.configuration.agents.CredentialsConfiguration.GUEST_CRED_COMPANY_NAME;
import static com.bka.ssi.controller.verification.company.aop.configuration.agents.CredentialsConfiguration.GUEST_CRED_COMPANY_POST_CODE;
import static com.bka.ssi.controller.verification.company.aop.configuration.agents.CredentialsConfiguration.GUEST_CRED_COMPANY_PROOF_OF_OWNERSHIP;
import static com.bka.ssi.controller.verification.company.aop.configuration.agents.CredentialsConfiguration.GUEST_CRED_COMPANY_STREET;
import static com.bka.ssi.controller.verification.company.aop.configuration.agents.CredentialsConfiguration.GUEST_CRED_DATA_ENCRYPTION_ALGORITHM;
import static com.bka.ssi.controller.verification.company.aop.configuration.agents.CredentialsConfiguration.GUEST_CRED_DATE_OF_BIRTH;
import static com.bka.ssi.controller.verification.company.aop.configuration.agents.CredentialsConfiguration.GUEST_CRED_EMAIL;
import static com.bka.ssi.controller.verification.company.aop.configuration.agents.CredentialsConfiguration.GUEST_CRED_FIRST_NAME;
import static com.bka.ssi.controller.verification.company.aop.configuration.agents.CredentialsConfiguration.GUEST_CRED_INVITED_BY;
import static com.bka.ssi.controller.verification.company.aop.configuration.agents.CredentialsConfiguration.GUEST_CRED_LAST_NAME;
import static com.bka.ssi.controller.verification.company.aop.configuration.agents.CredentialsConfiguration.GUEST_CRED_LICENSE_PLATE_NUMBER;
import static com.bka.ssi.controller.verification.company.aop.configuration.agents.CredentialsConfiguration.GUEST_CRED_LOCATION;
import static com.bka.ssi.controller.verification.company.aop.configuration.agents.CredentialsConfiguration.GUEST_CRED_PRIMARY_PHONE_NUMBER;
import static com.bka.ssi.controller.verification.company.aop.configuration.agents.CredentialsConfiguration.GUEST_CRED_REFERENCE_BASIS_ID;
import static com.bka.ssi.controller.verification.company.aop.configuration.agents.CredentialsConfiguration.GUEST_CRED_REFERENCE_EMPLOYEE_ID;
import static com.bka.ssi.controller.verification.company.aop.configuration.agents.CredentialsConfiguration.GUEST_CRED_SECONDARY_PHONE_NUMBER;
import static com.bka.ssi.controller.verification.company.aop.configuration.agents.CredentialsConfiguration.GUEST_CRED_TITLE;
import static com.bka.ssi.controller.verification.company.aop.configuration.agents.CredentialsConfiguration.GUEST_CRED_TYPE_OF_VISIT;
import static com.bka.ssi.controller.verification.company.aop.configuration.agents.CredentialsConfiguration.GUEST_CRED_VALID_FROM;
import static com.bka.ssi.controller.verification.company.aop.configuration.agents.CredentialsConfiguration.GUEST_CRED_VALID_UNTIL;

import com.bka.ssi.controller.verification.company.aop.configuration.agents.ACAPYConfiguration;
import com.bka.ssi.controller.verification.company.aop.configuration.agents.CredentialsConfiguration;
import com.bka.ssi.controller.verification.company.services.models.common.Address;
import com.bka.ssi.controller.verification.company.services.models.common.Base64Payload;
import com.bka.ssi.controller.verification.company.services.models.common.BasisId;
import com.bka.ssi.controller.verification.company.services.models.common.ConnectionlessProofRequest;
import com.bka.ssi.controller.verification.company.services.models.common.ContactInformation;
import com.bka.ssi.controller.verification.company.services.models.common.Employer;
import com.bka.ssi.controller.verification.company.services.models.common.EmptyDTO;
import com.bka.ssi.controller.verification.company.services.models.common.IdentityManagement;
import com.bka.ssi.controller.verification.company.services.models.common.Persona;
import com.bka.ssi.controller.verification.company.services.models.common.Position;
import com.bka.ssi.controller.verification.company.services.models.common.ProofRequestCredSpec;
import com.bka.ssi.controller.verification.company.services.models.common.ProofRequestService;
import com.bka.ssi.controller.verification.company.services.models.common.ProofRequestThread;
import com.bka.ssi.controller.verification.company.services.models.common.RequestPresentationAttach;
import com.bka.ssi.controller.verification.company.services.models.credentials.EmployeeCredential;
import com.bka.ssi.controller.verification.company.services.models.credentials.GuestCredential;
import com.bka.ssi.controller.verification.company.services.utilities.NonceGenerator;
import io.github.my_digi_id.acapy_client.model.IndyProofReqAttrSpec;
import io.github.my_digi_id.acapy_client.model.IndyProofRequest;
import io.github.my_digi_id.acapy_client.model.IndyProofRequestNonRevoked;
import io.github.my_digi_id.acapy_client.model.IndyProofRequestedProofRevealedAttrGroup;
import io.github.my_digi_id.acapy_client.model.RawEncoded;
import io.github.my_digi_id.acapy_client.model.V10PresentationCreateRequestRequest;
import io.github.my_digi_id.acapy_client.model.V10PresentationExchange;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;

import java.lang.reflect.InvocationTargetException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * The type Acapy utilities.
 */
@Component
public class ACAPYUtilities {

    private final CredentialsConfiguration credentialsConfiguration;
    private final ACAPYConfiguration acapyConfiguration;

    private ACAPYUtilities(CredentialsConfiguration credentialsConfiguration,
        ACAPYConfiguration acapyConfiguration) {
        this.credentialsConfiguration = credentialsConfiguration;
        this.acapyConfiguration = acapyConfiguration;
    }

    /**
     * Create presentation request v 10 presentation create request request.
     *
     * @param comment   the comment
     * @param trace     the trace
     * @param name      the name
     * @param credSpecs the cred specs
     * @return the v 10 presentation create request request
     * @throws InvocationTargetException the invocation target exception
     * @throws IllegalAccessException    the illegal access exception
     * @throws NoSuchMethodException     the no such method exception
     */
    public V10PresentationCreateRequestRequest createPresentationRequest(String comment,
        boolean trace, String name, Map<String,
        ProofRequestCredSpec> credSpecs)
        throws InvocationTargetException, IllegalAccessException, NoSuchMethodException {

        // Create nonce
        String nonce = String.valueOf(NonceGenerator.nextPositiveInt());

        V10PresentationCreateRequestRequest presentationCreateRequestRequest =
            new V10PresentationCreateRequestRequest();

        presentationCreateRequestRequest.setComment(comment);
        presentationCreateRequestRequest.setTrace(trace);

        IndyProofRequestNonRevoked indyProofRequestNonRevoked = new IndyProofRequestNonRevoked();
        indyProofRequestNonRevoked.from(0);
        indyProofRequestNonRevoked.to((int) Instant.now().getEpochSecond());

        IndyProofRequest indyProofRequest = new IndyProofRequest();
        indyProofRequest.setName(name);
        indyProofRequest.setNonce(nonce);
        indyProofRequest.setVersion("1.0");
        indyProofRequest.setRequestedAttributes(new HashMap<>());
        indyProofRequest.setRequestedPredicates(new HashMap<>());
        indyProofRequest.setNonRevoked(indyProofRequestNonRevoked);

        presentationCreateRequestRequest.setProofRequest(indyProofRequest);

        for (Map.Entry<String, ProofRequestCredSpec> entry :
            credSpecs.entrySet()) {

            IndyProofReqAttrSpec indyProofReqAttrSpec = new IndyProofReqAttrSpec();

            // Add names
            List<String> names = entry.getValue().getNames();
            if (names != null) {
                for (String nameItem :
                    names) {
                    indyProofReqAttrSpec.addNamesItem(nameItem);
                }
            }

            // Add restrictions
            List<Map<String, String>> restrictions = entry.getValue().getRestrictions();
            if (restrictions != null) {
                for (Map<String, String> restrictionItem :
                    restrictions) {
                    indyProofReqAttrSpec.addRestrictionsItem(restrictionItem);
                }
            }

            indyProofRequest.putRequestedAttributesItem(entry.getKey(), indyProofReqAttrSpec);
        }

        return presentationCreateRequestRequest;
    }

    /**
     * Create connectionless proof request connectionless proof request.
     *
     * @param presentationExchange the presentation exchange
     * @return the connectionless proof request
     */
    public ConnectionlessProofRequest createConnectionlessProofRequest(
        V10PresentationExchange presentationExchange) {

        ConnectionlessProofRequest connectionlessProofRequest = new ConnectionlessProofRequest();
        connectionlessProofRequest.setId(presentationExchange.getThreadId());
        connectionlessProofRequest.setType(this.acapyConfiguration.getAriesMessageType());
        RequestPresentationAttach requestPresentationAttach = new RequestPresentationAttach();
        requestPresentationAttach.setId(this.acapyConfiguration.getAriesAttachId());
        requestPresentationAttach.setMimeType(MediaType.APPLICATION_JSON_VALUE);

        Base64Payload base64Payload = new Base64Payload();
        base64Payload.setBase64(presentationExchange.getPresentationRequestDict()
            .getRequestPresentationsTildeAttach().get(0).getData().getBase64());
        requestPresentationAttach.setData(base64Payload);

        RequestPresentationAttach[] requestPresentationAttaches = new RequestPresentationAttach[1];
        requestPresentationAttaches[0] = requestPresentationAttach;
        connectionlessProofRequest.setRequestPresentationAttach(requestPresentationAttaches);

        ProofRequestService proofRequestService = new ProofRequestService();
        String[] keys = new String[1];
        keys[0] = this.acapyConfiguration.getVerKey();
        proofRequestService.setRecipientKeys(keys);
        String[] routingKeys = new String[0];
        proofRequestService.setRoutingKeys(routingKeys);
        proofRequestService.setServiceEndpoint(this.acapyConfiguration.getEndpoint());
        proofRequestService.setEndpointName(this.acapyConfiguration.getName());
        connectionlessProofRequest.setService(proofRequestService);

        ProofRequestThread proofRequestThread = new ProofRequestThread();
        EmptyDTO empty = new EmptyDTO();
        proofRequestThread.setReceivedOrders(empty);
        proofRequestThread.setThreadId(presentationExchange.getThreadId());
        connectionlessProofRequest.setThread(proofRequestThread);

        return connectionlessProofRequest;
    }

    /**
     * Gets basis id.
     *
     * @param presentationExchange the presentation exchange
     * @return the basis id
     */
    public BasisId getBasisId(V10PresentationExchange presentationExchange) {

        Map<String, String> attributes = getRevealedAttributeGroup(presentationExchange,
            BASIS_ID_ATTRIBUTE_NAME);

        BasisId basisId = new BasisId();
        basisId.setFirstName(attributes.get(BASIS_ID_FIRST_NAME));
        basisId.setFamilyName(attributes.get(BASIS_ID_FAMILY_NAME));
        basisId.setDateOfBirth(attributes.get(BASIS_ID_DATE_OF_BIRTH));
        basisId.setDateOfExpiry(attributes.get(BASIS_ID_DATE_OF_EXPIRY));

        return basisId;
    }

    /**
     * Gets guest credential.
     *
     * @param presentationExchange the presentation exchange
     * @return the guest credential
     * @throws ParseException the parse exception
     */
    public GuestCredential getGuestCredential(V10PresentationExchange presentationExchange)
        throws ParseException {
        DateFormat format = new SimpleDateFormat(DATE_FORMAT);

        Map<String, String> attributes = getRevealedAttributeGroup(presentationExchange,
            GUEST_CRED_ATTRIBUTE_NAME);

        GuestCredential guestCredential = new GuestCredential();
        guestCredential.setFirstName(attributes.get(GUEST_CRED_FIRST_NAME));
        guestCredential.setLastName(attributes.get(GUEST_CRED_LAST_NAME));
        guestCredential.setTitle(attributes.get(GUEST_CRED_TITLE));
        guestCredential.setEmail(attributes.get(GUEST_CRED_EMAIL));
        guestCredential.setPrimaryPhoneNumber(attributes.get(GUEST_CRED_PRIMARY_PHONE_NUMBER));
        guestCredential.setSecondaryPhoneNumber(attributes.get(GUEST_CRED_SECONDARY_PHONE_NUMBER));
        guestCredential.setCompanyName(attributes.get(GUEST_CRED_COMPANY_NAME));
        guestCredential.setTypeOfVisit(attributes.get(GUEST_CRED_TYPE_OF_VISIT));
        guestCredential.setLocation(attributes.get(GUEST_CRED_LOCATION));

        guestCredential.setValidFrom(
            ZonedDateTime.parse(attributes.get(GUEST_CRED_VALID_FROM),
                DateTimeFormatter.ISO_OFFSET_DATE_TIME));
        guestCredential.setValidUntil(
            ZonedDateTime.parse(attributes.get(GUEST_CRED_VALID_UNTIL),
                DateTimeFormatter.ISO_OFFSET_DATE_TIME));

        guestCredential.setInvitedBy(attributes.get(GUEST_CRED_INVITED_BY));
        guestCredential.setDateOfBirth(attributes.get(GUEST_CRED_DATE_OF_BIRTH));
        guestCredential.setLicensePlateNumber(attributes.get(GUEST_CRED_LICENSE_PLATE_NUMBER));
        guestCredential.setCompanyCity(attributes.get(GUEST_CRED_COMPANY_CITY));
        guestCredential.setCompanyStreet(attributes.get(GUEST_CRED_COMPANY_STREET));
        guestCredential.setCompanyPostCode(attributes.get(GUEST_CRED_COMPANY_POST_CODE));
        guestCredential.setReferenceBasisId(attributes.get(GUEST_CRED_REFERENCE_BASIS_ID));
        guestCredential.setReferenceEmployeeId(attributes.get(GUEST_CRED_REFERENCE_EMPLOYEE_ID));
        guestCredential.setCompanyProofOfOwnership(
            attributes.get(GUEST_CRED_COMPANY_PROOF_OF_OWNERSHIP));
        guestCredential.setDataEncryptionAlgorithm(
            attributes.get(GUEST_CRED_DATA_ENCRYPTION_ALGORITHM));

        return guestCredential;
    }

    /**
     * Gets employee credential.
     *
     * @param presentationExchange the presentation exchange
     * @return the employee credential
     */
    public EmployeeCredential getEmployeeCredential(V10PresentationExchange presentationExchange) {

        DateFormat format = new SimpleDateFormat(DATE_FORMAT);

        Map<String, String> attributes = getRevealedAttributeGroup(presentationExchange,
            EMPLOYEE_CRED_ATTRIBUTE_NAME);

        EmployeeCredential employeeCredential = new EmployeeCredential();

        Persona persona = new Persona(
            attributes.get(EMPLOYEE_CRED_TITLE),
            attributes.get(EMPLOYEE_CRED_FIRST_NAME),
            attributes.get(EMPLOYEE_CRED_LAST_NAME));
        employeeCredential.setPersona(persona);

        List<String> emails = new ArrayList<>();
        String email = attributes.get(EMPLOYEE_CRED_EMAIL);
        if (email != null) {
            emails.add(email);
        }

        List<String> phoneNumbers = new ArrayList<>();
        String primaryPhoneNumber = attributes.get(EMPLOYEE_CRED_PRIMARY_PHONE_NUMBER);
        if (primaryPhoneNumber != null) {
            phoneNumbers.add(primaryPhoneNumber);
        }
        String secondaryPhoneNumber = attributes.get(EMPLOYEE_CRED_SECONDARY_PHONE_NUMBER);
        if (secondaryPhoneNumber != null) {
            phoneNumbers.add(secondaryPhoneNumber);
        }

        ContactInformation contactInformation = new ContactInformation(emails, phoneNumbers);
        employeeCredential.setContactInformation(contactInformation);

        employeeCredential.setEmployeeState(attributes.get(EMPLOYEE_CRED_EMPLOYEE_STATE));
        employeeCredential.setEmployeeId(attributes.get(EMPLOYEE_CRED_EMPLOYEE_ID));

        Position position = new Position(attributes.get(EMPLOYEE_CRED_POSITION));
        employeeCredential.setPosition(position);

        Address address = new Address(
            attributes.get(EMPLOYEE_CRED_COMPANY_POST_CODE),
            attributes.get(EMPLOYEE_CRED_COMPANY_CITY),
            attributes.get(EMPLOYEE_CRED_COMPANY_STREET));

        Employer employer = new Employer(
            attributes.get(EMPLOYEE_CRED_COMPANY_NAME),
            address,
            null,
            attributes.get(EMPLOYEE_CRED_COMPANY_PROOF_OF_OWNERSHIP));
        employeeCredential.setEmployer(employer);

        IdentityManagement identityManagement =
            new IdentityManagement(attributes.get(EMPLOYEE_CRED_SSO_REFERENCE));
        employeeCredential.setIdentityManagement(identityManagement);

        return employeeCredential;
    }

    private Map<String, String> getRevealedAttributeGroup(
        V10PresentationExchange presentationExchange, String groupName) {

        Map<String, IndyProofRequestedProofRevealedAttrGroup> revealedAttrGroups =
            presentationExchange.getPresentation().getRequestedProof().getRevealedAttrGroups();

        for (Map.Entry<String, IndyProofRequestedProofRevealedAttrGroup> attrGroupEntry : revealedAttrGroups
            .entrySet()) {
            Map<String, RawEncoded> attrGroupValues = attrGroupEntry.getValue().getValues();

            if (groupName.equals(attrGroupEntry.getKey())) {
                Map<String, String> rawAttrValues = new HashMap<>();

                for (Map.Entry<String, RawEncoded> entry : attrGroupValues.entrySet()) {

                    rawAttrValues.put(entry.getKey(), entry.getValue().getRaw());
                }

                return rawAttrValues;
            }
        }

        return new HashMap<>();
    }
}
