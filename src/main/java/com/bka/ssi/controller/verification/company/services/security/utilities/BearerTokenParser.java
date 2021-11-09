package com.bka.ssi.controller.verification.company.services.security.utilities;

import com.bka.ssi.controller.verification.company.services.utilities.http.HttpHeaderUtility;
import org.springframework.stereotype.Component;

@Component
public class BearerTokenParser {

    public BearerTokenParser() {

    }

    public String getToken() {
        String token = HttpHeaderUtility.getHttpHeader("authorization");

        if (token != null) {
            return token.replace("Bearer ", "");
        }

        return null;
    }
}
