package com.bka.ssi.controller.verification.company.aop.configuration.agents;

import com.bka.ssi.controller.verification.company.services.models.common.ProofRequestCredSpec;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;
import javax.annotation.PostConstruct;

@Configuration
public class CredentialsConfiguration {

    public static final String DATE_FORMAT = "EEE MMM dd HH:mm:ss Z yyyy";

    public static final String BASIS_ID_FIRST_NAME = "firstName";
    public static final String BASIS_ID_FAMILY_NAME = "familyName";
    public static final String BASIS_ID_DATE_OF_BIRTH = "dateOfBirth";
    public static final String BASIS_ID_DATE_OF_EXPIRY = "dateOfExpiry";

    public static final String GUEST_CRED_FIRST_NAME = "firstName";
    public static final String GUEST_CRED_LAST_NAME = "lastName";
    public static final String GUEST_CRED_TITLE = "title";
    public static final String GUEST_CRED_EMAIL = "email";
    public static final String GUEST_CRED_PRIMARY_PHONE_NUMBER = "primaryPhoneNumber";
    public static final String GUEST_CRED_SECONDARY_PHONE_NUMBER = "secondaryPhoneNumber";
    public static final String GUEST_CRED_COMPANY_NAME = "companyName";
    public static final String GUEST_CRED_TYPE_OF_VISIT = "typeOfVisit";
    public static final String GUEST_CRED_LOCATION = "location";
    public static final String GUEST_CRED_VALID_FROM = "validFrom";
    public static final String GUEST_CRED_VALID_UNTIL = "validUntil";
    public static final String GUEST_CRED_INVITED_BY = "invitedBy";
    public static final String GUEST_CRED_DATE_OF_BIRTH = "dateOfBirth";
    public static final String GUEST_CRED_LICENSE_PLATE_NUMBER = "licensePlateNumber";
    public static final String GUEST_CRED_COMPANY_STREET = "companyStreet";
    public static final String GUEST_CRED_COMPANY_CITY = "companyCity";
    public static final String GUEST_CRED_COMPANY_POST_CODE = "companyPostCode";
    public static final String GUEST_CRED_REFERENCE_BASIS_ID = "referenceBasisId";
    public static final String GUEST_CRED_REFERENCE_EMPLOYEE_ID = "referenceEmployeeId";
    public static final String GUEST_CRED_COMPANY_PROOF_OF_OWNERSHIP = "companyProofOfOwnership";
    public static final String GUEST_CRED_DATA_ENCRYPTION_ALGORITHM = "dataEncryptionAlgorithm";

    public static final String EMPLOYEE_CRED_FIRST_NAME = "firstName";
    public static final String EMPLOYEE_CRED_LAST_NAME = "lastName";
    public static final String EMPLOYEE_CRED_TITLE = "title";
    public static final String EMPLOYEE_CRED_PRIMARY_PHONE_NUMBER = "primaryPhoneNumber";
    public static final String EMPLOYEE_CRED_SECONDARY_PHONE_NUMBER = "secondaryPhoneNumber";
    public static final String EMPLOYEE_CRED_EMAIL = "email";
    public static final String EMPLOYEE_CRED_EMPLOYEE_STATE = "employeeState";
    public static final String EMPLOYEE_CRED_EMPLOYEE_ID = "employeeId";
    public static final String EMPLOYEE_CRED_POSITION = "position";
    public static final String EMPLOYEE_CRED_COMPANY_NAME = "companyName";
    public static final String EMPLOYEE_CRED_COMPANY_STREET = "companyStreet";
    public static final String EMPLOYEE_CRED_COMPANY_CITY = "companyCity";
    public static final String EMPLOYEE_CRED_COMPANY_POST_CODE = "companyPostCode";
    public static final String EMPLOYEE_CRED_COMPANY_PROOF_OF_OWNERSHIP = "companyProofOfOwnership";
    public static final String EMPLOYEE_CRED_SSO_REFERENCE = "ssoReference";

    public static final String BASIS_ID_ATTRIBUTE_NAME = "basisIdAttributeName";
    public static final String GUEST_CRED_ATTRIBUTE_NAME = "guestCredAttributeName";
    public static final String EMPLOYEE_CRED_ATTRIBUTE_NAME = "employeeCredAttributeName";

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

    public String getBasisIdCredentialDefinitionId() {
        return basisIdCredentialDefinitionId;
    }

    public String getBasisIdCredentialSchemaId() {
        return basisIdCredentialSchemaId;
    }

    public String getEmployeeCredentialDefinitionId() {
        return employeeCredentialDefinitionId;
    }

    public String getEmployeeCredentialSchemaId() {
        return employeeCredentialSchemaId;
    }

    public String getGuestCredentialDefinitionId() {
        return guestCredentialDefinitionId;
    }

    public String getGuestCredentialSchemaId() {
        return guestCredentialSchemaId;
    }

    public Map<String, ProofRequestCredSpec> getGuestProofCredSpec() {
        return guestProofCredSpec;
    }

    public Map<String, ProofRequestCredSpec> getEmployeeProofCredSpec() {
        return this.employeeProofCredSpec;
    }
}
