package com.bka.ssi.controller.verification.company.aop.configuration.validation;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

/**
 * The type Validation configuration.
 */
@Configuration
public class ValidationConfiguration {

    private final String basename;
    private final String encoding = "UTF-8";

    /**
     * Instantiates a new Validation configuration.
     *
     * @param basename the basename
     */
    public ValidationConfiguration(@Value("${spring.messages.basename}") final String basename) {
        this.basename = basename;
    }

    /**
     * Message source message source.
     *
     * @return the message source
     */
    @Bean
    public MessageSource messageSource() {
        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
        messageSource.setBasename("classpath:" + basename);
        messageSource.setDefaultEncoding(encoding);
        return messageSource;
    }

    /**
     * Gets validator.
     *
     * @return the validator
     */
    @Bean
    @Primary
    public LocalValidatorFactoryBean getValidator() {
        LocalValidatorFactoryBean bean = new LocalValidatorFactoryBean();
        bean.setValidationMessageSource(messageSource());
        return bean;
    }
}
