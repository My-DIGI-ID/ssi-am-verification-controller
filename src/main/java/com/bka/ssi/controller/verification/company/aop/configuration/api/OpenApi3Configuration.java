package com.bka.ssi.controller.verification.company.aop.configuration.api;

import com.bka.ssi.controller.verification.company.aop.configuration.agents.ACAPYConfiguration;
import com.bka.ssi.controller.verification.company.aop.configuration.build.InfoConfiguration;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

@Configuration
public class OpenApi3Configuration {

    private final InfoConfiguration infoConfiguration;
    private final ACAPYConfiguration acapyConfiguration;

    public OpenApi3Configuration(
        InfoConfiguration infoConfiguration,
        ACAPYConfiguration acapyConfiguration) {
        this.infoConfiguration = infoConfiguration;
        this.acapyConfiguration = acapyConfiguration;
    }

    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI()
            .components(new Components()
                .addSecuritySchemes("api_key_webhook_api", new SecurityScheme()
                    .type(SecurityScheme.Type.APIKEY)
                    .description("Api Key: Webhook API")
                    .in(SecurityScheme.In.HEADER)
                    .name(this.acapyConfiguration.getApiKeyHeaderName())
                ))
            .security(Arrays.asList(
                new SecurityRequirement().addList("api_key_webhook_api")))
            .info(new Info()
                .title(this.infoConfiguration.getTitle())
                .description(this.infoConfiguration.getDescription())
                .version(this.infoConfiguration.getVersion())
                .contact(new Contact()
                    .name(this.infoConfiguration.getName())
                    .url(this.infoConfiguration.getUrl())
                    .email(this.infoConfiguration.getEmail())));
    }
}
