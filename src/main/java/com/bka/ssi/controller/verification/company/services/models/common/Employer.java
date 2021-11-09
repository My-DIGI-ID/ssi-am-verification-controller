package com.bka.ssi.controller.verification.company.services.models.common;

public class Employer extends Company {

    private String proofOfOwnership;

    public Employer(String name, Address address, String subject, String proofOfOwnership) {
        super(name, address, subject);

        this.proofOfOwnership = proofOfOwnership;
    }

    public Employer(String name, Address address, String subject) {
        super(name, address, subject);
    }

    public Employer(String name, Address address) {
        super(name, address);
    }

    public String getProofOfOwnership() {
        return proofOfOwnership;
    }
}
