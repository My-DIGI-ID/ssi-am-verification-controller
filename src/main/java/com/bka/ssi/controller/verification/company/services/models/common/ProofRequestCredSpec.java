package com.bka.ssi.controller.verification.company.services.models.common;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ProofRequestCredSpec {
    private List<Map<String, String>> restrictions;
    private List<String> names;

    public ProofRequestCredSpec addNamesItem(String namesItem) {
        if (this.names == null) {
            this.names = new ArrayList<>();
        }

        this.names.add(namesItem);
        return this;
    }

    public ProofRequestCredSpec addRestrictionsItem(Map<String, String> restrictionsItem) {
        if (this.restrictions == null) {
            this.restrictions = new ArrayList<>();
        }

        this.restrictions.add(restrictionsItem);
        return this;
    }

    public List<Map<String, String>> getRestrictions() {
        return restrictions;
    }

    public List<String> getNames() {
        return names;
    }
}
