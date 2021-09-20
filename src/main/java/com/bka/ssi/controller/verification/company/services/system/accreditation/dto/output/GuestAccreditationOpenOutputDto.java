package com.bka.ssi.controller.verification.company.services.system.accreditation.dto.output;


import com.bka.ssi.controller.verification.company.services.system.accreditation.enums.AccreditationStatus;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class GuestAccreditationOpenOutputDto {

    @NotEmpty
    @NotNull
    private String id;

    @NotEmpty
    @NotNull
    private String invitationEmail;

    @NotEmpty
    @NotNull
    private String invitationLink;

    @NotEmpty
    @NotNull
    private GuestOpenOutputDto guest;

    @NotEmpty
    @NotNull
    private String status;

    private String connectionQrCode;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getInvitationEmail() {
        return invitationEmail;
    }

    public void setInvitationEmail(String invitationEmail) {
        this.invitationEmail = invitationEmail;
    }

    public String getInvitationLink() {
        return invitationLink;
    }

    public void setInvitationLink(String invitationLink) {
        this.invitationLink = invitationLink;
    }

    public GuestOpenOutputDto getGuest() {
        return guest;
    }

    public void setGuest(
        GuestOpenOutputDto guest) {
        this.guest = guest;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(
        String status) {
        this.status = status;
    }

    public String getConnectionQrCode() {
        return connectionQrCode;
    }

    public void setConnectionQrCode(String connectionQrCode) {
        this.connectionQrCode = connectionQrCode;
    }
}

