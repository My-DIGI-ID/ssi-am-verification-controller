package com.bka.ssi.controller.verification.company.services.models.validation.common;

import com.bka.ssi.controller.verification.company.aop.utilities.I18nMessageBuilder;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * The type No forbidden characters validator.
 */
public class NoForbiddenCharactersValidator
        implements ConstraintValidator<NoForbiddenCharacters, String> {

    private final static String PATTERN = "(?i).*[^\\sa-z0-9_.&äáâàăçéëêèïíìñóöôòøșțüúûùß-].*";

    private final I18nMessageBuilder i18nMessageBuilder;

    /**
     * Instantiates a new No forbidden characters validator.
     *
     * @param i18nMessageBuilder the 18 n message builder
     */
    public NoForbiddenCharactersValidator(I18nMessageBuilder i18nMessageBuilder) {
        this.i18nMessageBuilder = i18nMessageBuilder;
    }

    @Override
    public void initialize(NoForbiddenCharacters constraint) {
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (value == null) {
            return true;
        }

        context.disableDefaultConstraintViolation();

        String errorMessage = this.i18nMessageBuilder
                .create("message.common.validations.error.no_forbidden_characters");
        context.buildConstraintViolationWithTemplate(errorMessage).addConstraintViolation();

        Pattern pattern = Pattern.compile(PATTERN);
        Matcher matcher = pattern.matcher(value);
        return !matcher.lookingAt();
    }
}
