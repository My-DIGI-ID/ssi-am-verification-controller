package com.bka.ssi.controller.verification.company.aop.utilities;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Component;

import java.util.Locale;

/**
 * The type 18 n message builder.
 */
@Component
public class I18nMessageBuilder {

    private final MessageSource messageSource;

    /**
     * Instantiates a new 18 n message builder.
     *
     * @param messageSource the message source
     */
    public I18nMessageBuilder(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    /**
     * Create string.
     *
     * @param messageKey the message key
     * @return the string
     */
    public String create(String messageKey) {
        String errorMessage;
        Locale locale = LocaleContextHolder.getLocale();

        try {
            errorMessage =
                    messageSource.getMessage(messageKey, null, locale);
        } catch (Exception e) {
            errorMessage = "No message available";
        }

        return errorMessage;
    }
}
