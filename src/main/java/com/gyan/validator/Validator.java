package com.gyan.validator;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.gyan.model.Validation;
import io.restassured.response.Response;

public interface Validator {
    void validate(Response response, Validation validation);
}
