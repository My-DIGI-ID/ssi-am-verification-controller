package com.bka.ssi.controller.verification.company.infra.db.mongo.documents;

import com.bka.ssi.controller.verification.company.infra.db.mongo.values.BasisIdMongoDbValue;
import com.bka.ssi.controller.verification.company.infra.db.mongo.values.GuestCredentialMongoDbValue;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.ZonedDateTime;

@Document(collection = "guestVerifications")
public class GuestVerificationMongoDbDocument extends VerificationMongoDbDocument {

    @Field("accreditationId")
    private String accreditationId;

    @Field("basisId")
    private BasisIdMongoDbValue basisIdMongoDbValue;

    @Field("guestCredential")
    private GuestCredentialMongoDbValue guestCredentialMongoDbValue;

    @Field("checkOutDateTime")
    private ZonedDateTime checkOutDateTime;

    @Field("checkInDateTime")
    private ZonedDateTime checkInDateTime;

    public GuestVerificationMongoDbDocument() {
    }

    public String getAccreditationId() {
        return accreditationId;
    }

    public void setAccreditationId(String accreditationId) {
        this.accreditationId = accreditationId;
    }

    public BasisIdMongoDbValue getBasisIdDbValue() {
        return basisIdMongoDbValue;
    }

    public void setBasisIdDbValue(BasisIdMongoDbValue basisIdMongoDbValue) {
        this.basisIdMongoDbValue = basisIdMongoDbValue;
    }

    public GuestCredentialMongoDbValue getGuestCredentialDbValue() {
        return guestCredentialMongoDbValue;
    }

    public void setGuestCredentialDbValue(GuestCredentialMongoDbValue guestCredentialMongoDbValue) {
        this.guestCredentialMongoDbValue = guestCredentialMongoDbValue;
    }

    public ZonedDateTime getCheckOutDateTime() {
        return checkOutDateTime;
    }

    public void setCheckOutDateTime(ZonedDateTime checkOutDateTime) {
        this.checkOutDateTime = checkOutDateTime;
    }

    public ZonedDateTime getCheckInDateTime() {
        return checkInDateTime;
    }

    public void setCheckInDateTime(ZonedDateTime checkInDateTime) {
        this.checkInDateTime = checkInDateTime;
    }
}
