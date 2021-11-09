package com.bka.ssi.controller.verification.company.api.v2.rest.dto.output;

import com.bka.ssi.controller.verification.company.services.enums.GuestVerificationStatus;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.ZonedDateTime;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class GuestVerificationOutputDto {
    @Size(max = 50)
    @NotEmpty
    @NotNull
    private String id;

    @NotEmpty
    @NotNull
    private BasisIdOutputDto basisId;

    @NotEmpty
    @NotNull
    private GuestCredentialOutputDto guestCredential;

    @NotNull
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private ZonedDateTime checkOutDateTime;

    @NotNull
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private ZonedDateTime checkInDateTime;

    @NotEmpty
    @NotNull
    private GuestVerificationStatus state;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public BasisIdOutputDto getBasisId() {
        return basisId;
    }

    public void setBasisId(BasisIdOutputDto basisId) {
        this.basisId = basisId;
    }

    public GuestCredentialOutputDto getGuestCredential() {
        return guestCredential;
    }

    public void setGuestCredential(GuestCredentialOutputDto guestCredential) {
        this.guestCredential = guestCredential;
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

    public GuestVerificationStatus getState() {
        return state;
    }

    public void setState(GuestVerificationStatus state) {
        this.state = state;
    }
}