package com.bka.ssi.controller.verification.company.services.models.common;

public class IdentityManagement {

    private String reference;
    private String username;
    private String email;

    public IdentityManagement(String reference, String username, String email) {
        this.reference = reference;
        this.username = username;
        this.email = email;
    }

    public IdentityManagement(String reference) {
        this.reference = reference;
    }

    public String getReference() {
        return reference;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }
}
