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

package com.bka.ssi.controller.verification.company.services.system.accreditation.dto.output;

/**
 * The type Guest accreditation open output dto.
 */
public class GuestAccreditationOpenOutputDto {

    private String id;
    private String invitationEmail;
    private String invitationLink;
    private GuestOpenOutputDto guest;
    private String status;
    private String connectionQrCode;

    /**
     * Gets id.
     *
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * Sets id.
     *
     * @param id the id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Gets invitation email.
     *
     * @return the invitation email
     */
    public String getInvitationEmail() {
        return invitationEmail;
    }

    /**
     * Sets invitation email.
     *
     * @param invitationEmail the invitation email
     */
    public void setInvitationEmail(String invitationEmail) {
        this.invitationEmail = invitationEmail;
    }

    /**
     * Gets invitation link.
     *
     * @return the invitation link
     */
    public String getInvitationLink() {
        return invitationLink;
    }

    /**
     * Sets invitation link.
     *
     * @param invitationLink the invitation link
     */
    public void setInvitationLink(String invitationLink) {
        this.invitationLink = invitationLink;
    }

    /**
     * Gets guest.
     *
     * @return the guest
     */
    public GuestOpenOutputDto getGuest() {
        return guest;
    }

    /**
     * Sets guest.
     *
     * @param guest the guest
     */
    public void setGuest(
        GuestOpenOutputDto guest) {
        this.guest = guest;
    }

    /**
     * Gets status.
     *
     * @return the status
     */
    public String getStatus() {
        return status;
    }

    /**
     * Sets status.
     *
     * @param status the status
     */
    public void setStatus(
        String status) {
        this.status = status;
    }

    /**
     * Gets connection qr code.
     *
     * @return the connection qr code
     */
    public String getConnectionQrCode() {
        return connectionQrCode;
    }

    /**
     * Sets connection qr code.
     *
     * @param connectionQrCode the connection qr code
     */
    public void setConnectionQrCode(String connectionQrCode) {
        this.connectionQrCode = connectionQrCode;
    }
}

