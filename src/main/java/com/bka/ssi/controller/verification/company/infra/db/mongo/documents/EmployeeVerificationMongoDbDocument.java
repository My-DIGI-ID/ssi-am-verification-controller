package com.bka.ssi.controller.verification.company.infra.db.mongo.documents;

import com.bka.ssi.controller.verification.company.infra.db.mongo.values.EmployeeCredentialMongoDbValue;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Document(collection = "employeeVerifications")
public class EmployeeVerificationMongoDbDocument extends VerificationMongoDbDocument {

    @Field("employeeCredential")
    private EmployeeCredentialMongoDbValue employeeCredentialMongoDbValue;

    public EmployeeVerificationMongoDbDocument() {
    }

    public EmployeeCredentialMongoDbValue getEmployeeCredentialMongoDbValue() {
        return employeeCredentialMongoDbValue;
    }

    public void setEmployeeCredentialMongoDbValue(
        EmployeeCredentialMongoDbValue employeeCredentialMongoDbValue) {
        this.employeeCredentialMongoDbValue = employeeCredentialMongoDbValue;
    }
}
