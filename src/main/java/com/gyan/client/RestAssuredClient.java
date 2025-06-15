package com.gyan.client;

import com.gyan.constants.ApiConstants;
import com.gyan.model.RequestSpec;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.util.List;
import java.util.Map;
import java.util.Objects;

public final class RestAssuredClient {

    public Response execute(RequestSpec requestSpec) {
        RequestSpecification requestSpecification = RestAssured
                .given()
                .filter(new CurlGenerator())
                .baseUri(ApiConstants.REGRES_CLIENT)
                .headers( Map.of("content-type", "application/json", "x-api-key", "reqres-free-v1"));
        addPathParams(requestSpec.getPathParam(), requestSpecification);
        addBasePath(requestSpec.getUri(), requestSpecification);
        addQueryParams(requestSpec.getQueryParam(), requestSpecification);
        addBody(requestSpec.getRequestBody(), requestSpecification);
        switch (requestSpec.getMethod()) {
            case GET -> {
               return this.get(requestSpecification);
            }
            case POST -> {
                return this.post(requestSpecification);
            }
            case PUT -> {
                return this.put(requestSpecification);
            }
            default ->
                throw new RuntimeException("The specified method is not currently supported in this framework");
        }
    }

    private Response post(RequestSpecification requestSpec) {
        return requestSpec
                .post();
    }

    private Response get(RequestSpecification requestSpec) {
        return requestSpec
                .get();
    }

    private Response put(RequestSpecification requestSpec) {
        return  requestSpec
                .put();
    }

    private void addBody(Object body, RequestSpecification requestSpec) {
        if(Objects.nonNull(body)) {
            requestSpec.body(body);
        }
    }

    private void addQueryParams(Map<String, Object> queryParams, RequestSpecification requestSpec) {
            if(Objects.nonNull(queryParams)) {
                requestSpec.queryParams(queryParams);
            }

    }

    private void addBasePath(String basePath, RequestSpecification requestSpec ) {
        if(Objects.nonNull(basePath)) {
            requestSpec.basePath(basePath);
        }
    }

    private void addPathParams(Map<String, String> pathParam, RequestSpecification requestSpec) {
        if(Objects.nonNull(pathParam)) {
            requestSpec.pathParams(pathParam);
        }
    }

    private void addHeaders(Map<String, String> headers, RequestSpecification requestSpec) {
        if(Objects.nonNull(headers)) {
            requestSpec.headers(headers);
        }
    }
}
