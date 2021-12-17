package com.bka.ssi.controller.verification.company.services.models.validation.common;


import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyString;

import com.bka.ssi.controller.verification.company.aop.utilities.I18nMessageBuilder;
import com.bka.ssi.controller.verification.company.testutilities.TestValidatorClass;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import javax.validation.ConstraintValidatorContext;

public class NoForbiddenCharactersValidationTest {

    private final static String VALID_STRING = "&äáâàăçéëêèïíììñóöôòøöșțüúüûùß-_.123";
    private final static String INVALID_STRING = "{&äáâàăçéëêèïíììñóöôòøöșțüúüûùß-_.123}";

    private static NoForbiddenCharacters noForbiddenCharacters;
    private static ConstraintValidatorContext constraintValidatorContext;
    private static I18nMessageBuilder i18nMessageBuilder;
    private static ConstraintValidatorContext.ConstraintViolationBuilder constraintViolationBuilder;
    private static NoForbiddenCharactersValidator noForbiddenCharactersValidator;

    @BeforeAll
    static void init() {
        noForbiddenCharacters = Mockito.mock(NoForbiddenCharacters.class);
        constraintValidatorContext = Mockito.mock(ConstraintValidatorContext.class);
        constraintViolationBuilder =
                Mockito.mock(ConstraintValidatorContext.ConstraintViolationBuilder.class);
        i18nMessageBuilder = Mockito.mock(I18nMessageBuilder.class);
        noForbiddenCharactersValidator = new NoForbiddenCharactersValidator(i18nMessageBuilder);

        noForbiddenCharactersValidator.initialize(noForbiddenCharacters);
        Mockito.when(i18nMessageBuilder.create(anyString())).thenReturn("Public error message");
        Mockito.when(
                        constraintValidatorContext.buildConstraintViolationWithTemplate(Mockito.anyString()))
                .thenReturn(constraintViolationBuilder);
    }

    @BeforeEach
    void setup() {
    }

    @Test
    public void shouldHaveNoViolations() {
        // given:
        TestValidatorClass testObject = new TestValidatorClass(VALID_STRING);

        // when:
        boolean result = noForbiddenCharactersValidator.isValid(testObject.field,
                constraintValidatorContext);

        // then:
        assertTrue(result);
    }

    @Test
    public void shouldHaveViolations() {
        // given:
        TestValidatorClass testObject = new TestValidatorClass(INVALID_STRING);

        // when:
        boolean result = noForbiddenCharactersValidator.isValid(testObject.field,
                constraintValidatorContext);

        // then:
        assertFalse(result);
    }
}
