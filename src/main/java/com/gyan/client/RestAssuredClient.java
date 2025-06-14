package com.gyan.client;

import com.gyan.constants.ApiConstants;
import com.gyan.model.RequestSpec;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.util.List;
import java.util.Map;
import java.util.Objects;

public class RestAssuredClient {
    private RequestSpecification requestSpecification;
    private Map<String, String> headers;

    public RestAssuredClient() {
        this.headers = Map.of("content-type", "application/json", "x-api-key", "reqres-free-v1");
        this.requestSpecification = RestAssured
                .given()
                .filter(new CurlGenerator())
                .baseUri(ApiConstants.REGRES_CLIENT)
                .headers(headers);

    }

    public Response execute(RequestSpec requestSpec) {
        addPathParams(requestSpec.getPathParam());
        addBasePath(requestSpec.getUri());
        addQueryParams(requestSpec.getQueryParam());
        addBody(requestSpec.getRequestBody());
        switch (requestSpec.getMethod()) {
            case GET -> {
               return this.get(requestSpec);
            }
            case POST -> {
                return this.post(requestSpec);
            }
            case PUT -> {
                return this.put(requestSpec);
            }
            default ->
                throw new RuntimeException("The specified method is not currently supported in this framework");
        }
    }

    private Response post(RequestSpec requestSpec) {
        return this.requestSpecification
                .post();
    }

    private Response get(RequestSpec requestSpec) {
        return this.requestSpecification
                .get();
    }

    private Response put(RequestSpec requestSpec) {
        return this.requestSpecification
                .post();
    }

    private void addBody(Object body) {
        if(Objects.nonNull(body)) {
            requestSpecification.body(body);
        }
    }

    private void addQueryParams(Map<String, Object> queryParams) {
            if(Objects.nonNull(queryParams)) {
                requestSpecification.queryParams(queryParams);
            }

    }

    private void addBasePath(String basePath) {
        if(Objects.nonNull(basePath)) {
            requestSpecification.basePath(basePath);
        }
    }

    private void addPathParams(Map<String, String> pathParam) {
        if(Objects.nonNull(pathParam)) {
            requestSpecification.pathParams(pathParam);
        }
    }

    private void addHeaders(Map<String, String> headers) {
        if(Objects.nonNull(headers)) {
            requestSpecification.headers(headers);
        }
    }
}
