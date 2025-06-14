package com.gyan.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.restassured.specification.RequestSpecification;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class TestDataModel {
    private String testcaseId;
    private String storyId;
    private String testcaseDescription;
    private List<TestcaseType> testcaseType;
    private String priority;
    private String expectedResult;
    private RequestSpec requestSpec;
    private Validation validation;

}
