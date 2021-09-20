package com.bka.ssi.controller.verification.company.services.security.utilities;

import javax.servlet.http.HttpServletRequest;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

@Service
public class BearerTokenParser {

    public BearerTokenParser() {
    }

    public String getToken() {
        RequestAttributes reqAttr = RequestContextHolder.getRequestAttributes();
        ServletRequestAttributes servlReqAttr = (ServletRequestAttributes) reqAttr;
        HttpServletRequest httpRequest = servlReqAttr.getRequest();

        Map<String, List<String>> headersMap = Collections
            .list(httpRequest.getHeaderNames())
            .stream()
            .collect(Collectors.toMap(Function.identity(),
                h -> Collections.list(httpRequest.getHeaders(h))));

        List<String> authorizationHeader = headersMap
            .get("authorization");

        if (authorizationHeader != null) {
            return authorizationHeader.get(0).replace("Bearer ", "");
        }

        return null;
    }
}
