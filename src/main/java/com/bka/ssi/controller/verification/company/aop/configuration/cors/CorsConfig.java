package com.bka.ssi.controller.verification.company.aop.configuration.cors;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
public class CorsConfig implements WebMvcConfigurer {

    @Value("${verification.ui.hostA}")
    private String hostAllowedOriginA;

    @Value("${verification.ui.portA}")
    private String portAllowedOriginA;

    @Value("${verification.ui.hostB}")
    private String hostAllowedOriginB;

    @Value("${verification.ui.portB}")
    private String portAllowedOriginB;

    @Value("${verification.ui.hostC}")
    private String hostAllowedOriginC;

    @Value("${verification.ui.portC}")
    private String portAllowedOriginC;

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/api/**")
            .allowedOrigins(
                hostAllowedOriginA + ":" + portAllowedOriginA,
                hostAllowedOriginB + ":" + portAllowedOriginB,
                hostAllowedOriginC + ":" + portAllowedOriginC
            )
            .allowedMethods("POST", "GET");
    }
}
