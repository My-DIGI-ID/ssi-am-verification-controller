package com.bka.ssi.controller.verification.company.services.scripts;

public interface Buildable<T> {

    void init();

    T build();
}
