package com.bka.ssi.controller.verification.company.api.v2.rest.dto.output;

import org.springframework.format.annotation.DateTimeFormat;

import com.bka.ssi.controller.verification.company.services.enums.VerificationState;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

public class VerificationOutputDto {
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
    private Date checkOutDateTime;

    @NotNull
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private Date checkInDateTime;

    @NotEmpty
    @NotNull
    private VerificationState state;

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

    public Date getCheckOutDateTime() {
        return checkOutDateTime;
    }

    public void setCheckOutDateTime(Date checkOutDateTime) {
        this.checkOutDateTime = checkOutDateTime;
    }

    public Date getCheckInDateTime() {
        return checkInDateTime;
    }

    public void setCheckInDateTime(Date checkInDateTime) {
        this.checkInDateTime = checkInDateTime;
    }

    public VerificationState getState() {
        return state;
    }

    public void setState(VerificationState state) {
        this.state = state;
    }
}