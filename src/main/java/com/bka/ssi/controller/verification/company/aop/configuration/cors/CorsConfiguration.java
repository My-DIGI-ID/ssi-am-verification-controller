package com.bka.ssi.controller.verification.company.aop.configuration.cors;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
public class CorsConfiguration implements WebMvcConfigurer {

    @Value("${verification.ui.hostA}")
    private String allowedOriginHostA;

    @Value("${verification.ui.hostB}")
    private String allowedOriginHostB;

    @Value("${verification.ui.hostC}")
    private String allowedOriginHostC;

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/api/**")
            .allowedOrigins(
                allowedOriginHostA,
                allowedOriginHostB,
                allowedOriginHostC
            )
            .allowedMethods("POST", "GET", "DELETE", "PUT", "PATCH", "OPTIONS");
    }
}
