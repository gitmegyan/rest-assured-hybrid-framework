package com.gyan.client;

import io.restassured.filter.Filter;
import io.restassured.filter.FilterContext;
import io.restassured.http.Header;
import io.restassured.response.Response;
import io.restassured.specification.FilterableRequestSpecification;
import io.restassured.specification.FilterableResponseSpecification;

import java.util.Map;
import java.util.Objects;

public class CurlGenerator implements Filter {
    public Response filter(FilterableRequestSpecification requestSpecification,
                           FilterableResponseSpecification filterableResponseSpecification,
                           FilterContext filterContext) {
        StringBuilder curl = new StringBuilder("curl");
        curl.append(" -X ").append(requestSpecification.getMethod());
        for(Header header: requestSpecification.getHeaders().asList()) {
            curl.append(" -H \"")
                    .append(header.getName())
                    .append(": ")
                    .append(header.getValue())
                    .append("\"");
        }

        if(Objects.nonNull(requestSpecification.getBody())) {
            String body = requestSpecification.getBody().toString()
                    .replace("\"", "\\\"");
            curl.append(" -d \"")
                    .append(body)
                    .append("\"");

        }
        curl.append(" \"")
                .append(requestSpecification.getURI()).append("\"");
        System.out.println("Generated cURL: \n" + curl);

        return filterContext.next(requestSpecification, filterableResponseSpecification);
    }
}
