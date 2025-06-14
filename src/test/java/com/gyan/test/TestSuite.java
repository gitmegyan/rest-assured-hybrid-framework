package com.gyan.test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gyan.client.RestAssuredClient;
import com.gyan.dataprovider.TestCaseProvider;
import com.gyan.model.TestDataModel;
import com.gyan.model.Validation;
import com.gyan.validator.JsonSchemaValidator;
import com.gyan.validator.ResponseValidator;
import com.gyan.validator.Validator;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.ResponseSpecification;
import org.testng.annotations.Test;

public class TestSuite {

    private final RestAssuredClient restAssuredClient = new RestAssuredClient();
    Validator jsonSchemaValidator = new JsonSchemaValidator();
    Validator responseValidator = new ResponseValidator();


    @Test(dataProviderClass = TestCaseProvider.class, dataProvider = "testcaseProvider")
    public void testSuite(TestDataModel testDataModel) {
       Response response = restAssuredClient.execute(testDataModel.getRequestSpec());
        jsonSchemaValidator.validate(response, testDataModel.getValidation());
        responseValidator.validate(response, testDataModel.getValidation());
    }
}
