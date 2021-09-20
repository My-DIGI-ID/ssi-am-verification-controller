package com.bka.ssi.controller.verification.company.services.system.accreditation.dto.output;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class GuestAccreditationPrivateOutputDto extends GuestAccreditationOpenOutputDto {

    @NotEmpty
    @NotNull
    private GuestPrivateOutputDto guest;

    @Override
    public GuestPrivateOutputDto getGuest() {
        return guest;
    }

    public void setGuest(
        GuestPrivateOutputDto guest) {
        this.guest = guest;
    }

    @Override
    public String toString() {
        return "GuestAccreditationPrivateOutputDto{" +
            "guest=" + guest +
            '}';
    }
}
