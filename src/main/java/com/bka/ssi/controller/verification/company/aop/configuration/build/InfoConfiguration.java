package com.bka.ssi.controller.verification.company.aop.configuration.build;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class InfoConfiguration {

    @Value("${verification.info.title}")
    private String title;

    @Value("${verification.info.description}")
    private String description;

    @Value("${verification.info.version}")
    private String version;

    @Value("${verification.info.contact.name}")
    private String name;

    @Value("${verification.info.contact.url}")
    private String url;

    @Value("${verification.info.contact.email}")
    private String email;

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getVersion() {
        return version;
    }

    public String getName() {
        return name;
    }

    public String getUrl() {
        return url;
    }

    public String getEmail() {
        return email;
    }
}
