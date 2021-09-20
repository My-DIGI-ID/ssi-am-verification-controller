package com.bka.ssi.controller.verification.company.services.models.abstractions;

public abstract class Record {

    private String id;

    public Record(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }
}
