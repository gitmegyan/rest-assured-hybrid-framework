package com.gyan.validator;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gyan.model.Validation;
import io.restassured.response.Response;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.Objects;

@Slf4j
public class JsonSchemaValidator implements Validator {

    public void validate(Response response, Validation validation) {
        if(Objects.nonNull(validation.getSchemaValidation())) {
            try {
                response.then()
                        .body(io.restassured.module.jsv.JsonSchemaValidator
                                .matchesJsonSchema(new ObjectMapper().writeValueAsString(validation.getSchemaValidation())));
            } catch (JsonProcessingException ex) {
                log.error(" Json Processing exception " + ex.getMessage());
                ex.printStackTrace();
                throw  new RuntimeException("Exception occured in proceessing" + ex.getMessage());
            }

        }
    }
}
