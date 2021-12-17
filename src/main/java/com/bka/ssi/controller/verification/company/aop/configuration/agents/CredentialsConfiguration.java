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

package com.bka.ssi.controller.verification.company.aop.configuration.agents;

import com.bka.ssi.controller.verification.company.services.models.common.ProofRequestCredSpec;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;
import javax.annotation.PostConstruct;

/**
 * The type Credentials configuration.
 */
@Configuration
public class CredentialsConfiguration {

    /**
     * The constant DATE_FORMAT.
     */
    public static final String DATE_FORMAT = "EEE MMM dd HH:mm:ss Z yyyy";

    /**
     * The constant BASIS_ID_FIRST_NAME.
     */
    public static final String BASIS_ID_FIRST_NAME = "firstName";
    /**
     * The constant BASIS_ID_FAMILY_NAME.
     */
    public static final String BASIS_ID_FAMILY_NAME = "familyName";
    /**
     * The constant BASIS_ID_DATE_OF_BIRTH.
     */
    public static final String BASIS_ID_DATE_OF_BIRTH = "dateOfBirth";
    /**
     * The constant BASIS_ID_DATE_OF_EXPIRY.
     */
    public static final String BASIS_ID_DATE_OF_EXPIRY = "dateOfExpiry";

    /**
     * The constant GUEST_CRED_FIRST_NAME.
     */
    public static final String GUEST_CRED_FIRST_NAME = "firstName";
    /**
     * The constant GUEST_CRED_LAST_NAME.
     */
    public static final String GUEST_CRED_LAST_NAME = "lastName";
    /**
     * The constant GUEST_CRED_TITLE.
     */
    public static final String GUEST_CRED_TITLE = "title";
    /**
     * The constant GUEST_CRED_EMAIL.
     */
    public static final String GUEST_CRED_EMAIL = "email";
    /**
     * The constant GUEST_CRED_PRIMARY_PHONE_NUMBER.
     */
    public static final String GUEST_CRED_PRIMARY_PHONE_NUMBER = "primaryPhoneNumber";
    /**
     * The constant GUEST_CRED_SECONDARY_PHONE_NUMBER.
     */
    public static final String GUEST_CRED_SECONDARY_PHONE_NUMBER = "secondaryPhoneNumber";
    /**
     * The constant GUEST_CRED_COMPANY_NAME.
     */
    public static final String GUEST_CRED_COMPANY_NAME = "companyName";
    /**
     * The constant GUEST_CRED_TYPE_OF_VISIT.
     */
    public static final String GUEST_CRED_TYPE_OF_VISIT = "typeOfVisit";
    /**
     * The constant GUEST_CRED_LOCATION.
     */
    public static final String GUEST_CRED_LOCATION = "location";
    /**
     * The constant GUEST_CRED_VALID_FROM.
     */
    public static final String GUEST_CRED_VALID_FROM = "validFrom";
    /**
     * The constant GUEST_CRED_VALID_UNTIL.
     */
    public static final String GUEST_CRED_VALID_UNTIL = "validUntil";
    /**
     * The constant GUEST_CRED_INVITED_BY.
     */
    public static final String GUEST_CRED_INVITED_BY = "invitedBy";
    /**
     * The constant GUEST_CRED_DATE_OF_BIRTH.
     */
    public static final String GUEST_CRED_DATE_OF_BIRTH = "dateOfBirth";
    /**
     * The constant GUEST_CRED_LICENSE_PLATE_NUMBER.
     */
    public static final String GUEST_CRED_LICENSE_PLATE_NUMBER = "licensePlateNumber";
    /**
     * The constant GUEST_CRED_COMPANY_STREET.
     */
    public static final String GUEST_CRED_COMPANY_STREET = "companyStreet";
    /**
     * The constant GUEST_CRED_COMPANY_CITY.
     */
    public static final String GUEST_CRED_COMPANY_CITY = "companyCity";
    /**
     * The constant GUEST_CRED_COMPANY_POST_CODE.
     */
    public static final String GUEST_CRED_COMPANY_POST_CODE = "companyPostCode";
    /**
     * The constant GUEST_CRED_REFERENCE_BASIS_ID.
     */
    public static final String GUEST_CRED_REFERENCE_BASIS_ID = "referenceBasisId";
    /**
     * The constant GUEST_CRED_REFERENCE_EMPLOYEE_ID.
     */
    public static final String GUEST_CRED_REFERENCE_EMPLOYEE_ID = "referenceEmployeeId";
    /**
     * The constant GUEST_CRED_COMPANY_PROOF_OF_OWNERSHIP.
     */
    public static final String GUEST_CRED_COMPANY_PROOF_OF_OWNERSHIP = "companyProofOfOwnership";
    /**
     * The constant GUEST_CRED_DATA_ENCRYPTION_ALGORITHM.
     */
    public static final String GUEST_CRED_DATA_ENCRYPTION_ALGORITHM = "dataEncryptionAlgorithm";

    /**
     * The constant EMPLOYEE_CRED_FIRST_NAME.
     */
    public static final String EMPLOYEE_CRED_FIRST_NAME = "firstName";
    /**
     * The constant EMPLOYEE_CRED_LAST_NAME.
     */
    public static final String EMPLOYEE_CRED_LAST_NAME = "lastName";
    /**
     * The constant EMPLOYEE_CRED_TITLE.
     */
    public static final String EMPLOYEE_CRED_TITLE = "title";
    /**
     * The constant EMPLOYEE_CRED_PRIMARY_PHONE_NUMBER.
     */
    public static final String EMPLOYEE_CRED_PRIMARY_PHONE_NUMBER = "primaryPhoneNumber";
    /**
     * The constant EMPLOYEE_CRED_SECONDARY_PHONE_NUMBER.
     */
    public static final String EMPLOYEE_CRED_SECONDARY_PHONE_NUMBER = "secondaryPhoneNumber";
    /**
     * The constant EMPLOYEE_CRED_EMAIL.
     */
    public static final String EMPLOYEE_CRED_EMAIL = "email";
    /**
     * The constant EMPLOYEE_CRED_EMPLOYEE_STATE.
     */
    public static final String EMPLOYEE_CRED_EMPLOYEE_STATE = "employeeState";
    /**
     * The constant EMPLOYEE_CRED_EMPLOYEE_ID.
     */
    public static final String EMPLOYEE_CRED_EMPLOYEE_ID = "employeeId";
    /**
     * The constant EMPLOYEE_CRED_POSITION.
     */
    public static final String EMPLOYEE_CRED_POSITION = "position";
    /**
     * The constant EMPLOYEE_CRED_COMPANY_NAME.
     */
    public static final String EMPLOYEE_CRED_COMPANY_NAME = "companyName";
    /**
     * The constant EMPLOYEE_CRED_COMPANY_STREET.
     */
    public static final String EMPLOYEE_CRED_COMPANY_STREET = "companyStreet";
    /**
     * The constant EMPLOYEE_CRED_COMPANY_CITY.
     */
    public static final String EMPLOYEE_CRED_COMPANY_CITY = "companyCity";
    /**
     * The constant EMPLOYEE_CRED_COMPANY_POST_CODE.
     */
    public static final String EMPLOYEE_CRED_COMPANY_POST_CODE = "companyPostCode";
    /**
     * The constant EMPLOYEE_CRED_COMPANY_PROOF_OF_OWNERSHIP.
     */
    public static final String EMPLOYEE_CRED_COMPANY_PROOF_OF_OWNERSHIP = "companyProofOfOwnership";
    /**
     * The constant EMPLOYEE_CRED_SSO_REFERENCE.
     */
    public static final String EMPLOYEE_CRED_SSO_REFERENCE = "ssoReference";

    /**
     * The constant BASIS_ID_ATTRIBUTE_NAME.
     */
    public static final String BASIS_ID_ATTRIBUTE_NAME = "basisIdAttributeName";
    /**
     * The constant GUEST_CRED_ATTRIBUTE_NAME.
     */
    public static final String GUEST_CRED_ATTRIBUTE_NAME = "guestCredAttributeName";
    /**
     * The constant EMPLOYEE_CRED_ATTRIBUTE_NAME.
     */
    public static final String EMPLOYEE_CRED_ATTRIBUTE_NAME = "employeeCredAttributeName";

    /**
     * The constant RESTRICTION_CRED_DEF_ID.
     */
    public static final String RESTRICTION_CRED_DEF_ID = "cred_def_id";

    @Value("${bdr.credentials.basis_id.credential_definition_id}")
    private String basisIdCredentialDefinitionId;

    @Value("${bdr.credentials.basis_id.employee.schema_id}")
    private String basisIdCredentialSchemaId;

    @Value("${verification.credentials.employee.credential_definition_id}")
    private String employeeCredentialDefinitionId;

    @Value("${verification.credentials.employee.schema_id}")
    private String employeeCredentialSchemaId;

    @Value("${verification.credentials.guest.credential_definition_id}")
    private String guestCredentialDefinitionId;

    @Value("${verification.credentials.guest.schema_id}")
    private String guestCredentialSchemaId;

    private Map<String, ProofRequestCredSpec> guestProofCredSpec;
    private Map<String, ProofRequestCredSpec> employeeProofCredSpec;

    @PostConstruct
    private void initCredSpec() {
        initGuestCredSpec();
        initEmployeeCredSpec();
    }

    private void initGuestCredSpec() {
        this.guestProofCredSpec = new HashMap<>();

        // Set requested attributes Basis ID
        Map<String, String> basisIdRestrictionItem = new HashMap<>();
        basisIdRestrictionItem.put(RESTRICTION_CRED_DEF_ID, getBasisIdCredentialDefinitionId());

        ProofRequestCredSpec basisIdCredSpec = new ProofRequestCredSpec()
            .addNamesItem(BASIS_ID_FIRST_NAME)
            .addNamesItem(BASIS_ID_FAMILY_NAME)
            .addNamesItem(BASIS_ID_DATE_OF_BIRTH)
            .addNamesItem(BASIS_ID_DATE_OF_EXPIRY)
            .addRestrictionsItem(basisIdRestrictionItem);

        this.guestProofCredSpec.put(BASIS_ID_ATTRIBUTE_NAME, basisIdCredSpec);

        // Set requested attributes Guest Credential
        Map<String, String> guestCredRestrictionItem = new HashMap<>();
        guestCredRestrictionItem.put(RESTRICTION_CRED_DEF_ID, getGuestCredentialDefinitionId());

        ProofRequestCredSpec guestCredSpec = new ProofRequestCredSpec()
            .addNamesItem(GUEST_CRED_FIRST_NAME)
            .addNamesItem(GUEST_CRED_LAST_NAME)
            .addNamesItem(GUEST_CRED_TITLE)
            .addNamesItem(GUEST_CRED_EMAIL)
            .addNamesItem(GUEST_CRED_PRIMARY_PHONE_NUMBER)
            .addNamesItem(GUEST_CRED_SECONDARY_PHONE_NUMBER)
            .addNamesItem(GUEST_CRED_COMPANY_NAME)
            .addNamesItem(GUEST_CRED_TYPE_OF_VISIT)
            .addNamesItem(GUEST_CRED_LOCATION)
            .addNamesItem(GUEST_CRED_VALID_FROM)
            .addNamesItem(GUEST_CRED_VALID_UNTIL)
            .addNamesItem(GUEST_CRED_INVITED_BY)
            .addNamesItem(GUEST_CRED_DATE_OF_BIRTH)
            .addNamesItem(GUEST_CRED_LICENSE_PLATE_NUMBER)
            .addNamesItem(GUEST_CRED_COMPANY_STREET)
            .addNamesItem(GUEST_CRED_COMPANY_CITY)
            .addNamesItem(GUEST_CRED_COMPANY_POST_CODE)
            .addNamesItem(GUEST_CRED_REFERENCE_BASIS_ID)
            .addNamesItem(GUEST_CRED_REFERENCE_EMPLOYEE_ID)
            .addNamesItem(GUEST_CRED_COMPANY_PROOF_OF_OWNERSHIP)
            .addNamesItem(GUEST_CRED_DATA_ENCRYPTION_ALGORITHM)
            .addRestrictionsItem(guestCredRestrictionItem);

        this.guestProofCredSpec.put(GUEST_CRED_ATTRIBUTE_NAME, guestCredSpec);
    }

    private void initEmployeeCredSpec() {
        this.employeeProofCredSpec = new HashMap<>();

        // Set requested attributes Employee Credential
        Map<String, String> employeeCredRestrictionItem = new HashMap<>();
        employeeCredRestrictionItem.put(RESTRICTION_CRED_DEF_ID,
            getEmployeeCredentialDefinitionId());

        ProofRequestCredSpec employeeCredSpec = new ProofRequestCredSpec()
            .addNamesItem(EMPLOYEE_CRED_FIRST_NAME)
            .addNamesItem(EMPLOYEE_CRED_LAST_NAME)
            .addNamesItem(EMPLOYEE_CRED_TITLE)
            .addNamesItem(EMPLOYEE_CRED_PRIMARY_PHONE_NUMBER)
            .addNamesItem(EMPLOYEE_CRED_SECONDARY_PHONE_NUMBER)
            .addNamesItem(EMPLOYEE_CRED_EMAIL)
            .addNamesItem(EMPLOYEE_CRED_EMPLOYEE_STATE)
            .addNamesItem(EMPLOYEE_CRED_EMPLOYEE_ID)
            .addNamesItem(EMPLOYEE_CRED_POSITION)
            .addNamesItem(EMPLOYEE_CRED_COMPANY_NAME)
            .addNamesItem(EMPLOYEE_CRED_COMPANY_STREET)
            .addNamesItem(EMPLOYEE_CRED_COMPANY_CITY)
            .addNamesItem(EMPLOYEE_CRED_COMPANY_POST_CODE)
            .addNamesItem(EMPLOYEE_CRED_COMPANY_PROOF_OF_OWNERSHIP)
            .addNamesItem(EMPLOYEE_CRED_SSO_REFERENCE)
            .addRestrictionsItem(employeeCredRestrictionItem);

        this.employeeProofCredSpec.put(EMPLOYEE_CRED_ATTRIBUTE_NAME, employeeCredSpec);
    }

    /**
     * Gets basis id credential definition id.
     *
     * @return the basis id credential definition id
     */
    public String getBasisIdCredentialDefinitionId() {
        return basisIdCredentialDefinitionId;
    }

    /**
     * Gets basis id credential schema id.
     *
     * @return the basis id credential schema id
     */
    public String getBasisIdCredentialSchemaId() {
        return basisIdCredentialSchemaId;
    }

    /**
     * Gets employee credential definition id.
     *
     * @return the employee credential definition id
     */
    public String getEmployeeCredentialDefinitionId() {
        return employeeCredentialDefinitionId;
    }

    /**
     * Gets employee credential schema id.
     *
     * @return the employee credential schema id
     */
    public String getEmployeeCredentialSchemaId() {
        return employeeCredentialSchemaId;
    }

    /**
     * Gets guest credential definition id.
     *
     * @return the guest credential definition id
     */
    public String getGuestCredentialDefinitionId() {
        return guestCredentialDefinitionId;
    }

    /**
     * Gets guest credential schema id.
     *
     * @return the guest credential schema id
     */
    public String getGuestCredentialSchemaId() {
        return guestCredentialSchemaId;
    }

    /**
     * Gets guest proof cred spec.
     *
     * @return the guest proof cred spec
     */
    public Map<String, ProofRequestCredSpec> getGuestProofCredSpec() {
        return guestProofCredSpec;
    }

    /**
     * Gets employee proof cred spec.
     *
     * @return the employee proof cred spec
     */
    public Map<String, ProofRequestCredSpec> getEmployeeProofCredSpec() {
        return this.employeeProofCredSpec;
    }
}
