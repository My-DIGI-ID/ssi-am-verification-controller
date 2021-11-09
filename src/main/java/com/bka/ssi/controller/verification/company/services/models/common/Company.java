package com.bka.ssi.controller.verification.company.services.models.common;

public class Company {

    private String name;
    private Address address;
    private String subject;

    public Company(String name, Address address, String subject) {
        this.name = name;
        this.address = address;
        this.subject = subject;
    }

    public Company(String name, Address address) {
        this.name = name;
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public Address getAddress() {
        return address;
    }

    public String getSubject() {
        return subject;
    }
}
