package com.bka.ssi.controller.verification.company.api.common.rest.exceptions.handlers;

import com.bka.ssi.controller.verification.company.api.common.rest.controllers.ACAPYWebhookController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.annotation.Priority;

@Priority(1)
@RestControllerAdvice(basePackageClasses = ACAPYWebhookController.class)
public class ACAPYWebhookExceptionsHandler {
    
}
