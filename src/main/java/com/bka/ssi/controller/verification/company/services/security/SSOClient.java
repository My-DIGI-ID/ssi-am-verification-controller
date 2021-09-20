package com.bka.ssi.controller.verification.company.services.security;

public interface SSOClient {

    boolean verifyPermission(String token, String transaction) throws Exception;

    boolean verifyToken(String token) throws Exception;
}
