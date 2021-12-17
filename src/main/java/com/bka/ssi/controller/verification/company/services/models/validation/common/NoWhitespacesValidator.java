package com.bka.ssi.controller.verification.company.services.models.validation.common;

import com.bka.ssi.controller.verification.company.aop.utilities.I18nMessageBuilder;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * The type No whitespaces validator.
 */
public class NoWhitespacesValidator implements ConstraintValidator<NoWhitespaces, String> {

    private final static String PATTERN = "(?i).*[\\s].*";

    private final I18nMessageBuilder i18nMessageBuilder;

    /**
     * Instantiates a new No whitespaces validator.
     *
     * @param i18nMessageBuilder the 18 n message builder
     */
    public NoWhitespacesValidator(I18nMessageBuilder i18nMessageBuilder) {
        this.i18nMessageBuilder = i18nMessageBuilder;
    }

    @Override
    public void initialize(NoWhitespaces constraint) {
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null) {
            return true;
        }

        context.disableDefaultConstraintViolation();

        String errorMessage = this.i18nMessageBuilder
                .create("message.common.validations.error.no_whitespaces");
        context.buildConstraintViolationWithTemplate(errorMessage).addConstraintViolation();

        Pattern pattern = Pattern.compile(PATTERN);
        Matcher matcher = pattern.matcher(value);
        return !matcher.lookingAt();
    }
}
