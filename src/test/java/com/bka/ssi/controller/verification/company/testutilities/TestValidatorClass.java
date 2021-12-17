package com.bka.ssi.controller.verification.company.testutilities;


import com.bka.ssi.controller.verification.company.services.models.validation.common.NoForbiddenCharacters;
import com.bka.ssi.controller.verification.company.services.models.validation.common.NoWhitespaces;

public class TestValidatorClass {

    @NoWhitespaces
    @NoForbiddenCharacters
    public String field;

    public TestValidatorClass(String field) {
        this.field = field;
    }
}
