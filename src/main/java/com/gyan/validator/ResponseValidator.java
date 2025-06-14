package com.gyan.validator;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gyan.model.ResponseValidation;
import com.gyan.model.Validation;
import io.restassured.response.Response;
import lombok.extern.slf4j.Slf4j;
import org.json.JSONException;
import org.skyscreamer.jsonassert.JSONAssert;
import org.testng.Assert;

import java.util.Iterator;
import java.util.Objects;

@Slf4j
public class ResponseValidator implements Validator{

    @Override
    public void validate(Response response, Validation validation) {
        if(Objects.nonNull(validation.getResponseValidation())) {
            ResponseValidation responseValidation = validation.getResponseValidation();
            if(Objects.nonNull(responseValidation.getStatusCode())) {
                Assert.assertEquals(response.getStatusCode(), responseValidation.getStatusCode(),
                        "The status code mismatch");
            }
            if(Objects.nonNull(responseValidation.getBody())) {
                ObjectMapper mapper = new ObjectMapper();
                try {
                    JsonNode expected = mapper.readTree(responseValidation.getBody().toString());
                    JsonNode actual = mapper.readTree(response.getBody().asString());
                    validateJsonWithPlaceholders(expected, actual);
                } catch (JsonProcessingException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    private void validateJsonWithPlaceholders(JsonNode expected, JsonNode actual) {
        Iterator<String> fieldNames = expected.fieldNames();

        while (fieldNames.hasNext()) {
            String field = fieldNames.next();
            JsonNode expectedValue = expected.get(field);
            JsonNode actualValue = actual.get(field);

            // Check if field exists in actual
            Assert.assertTrue(actual.has(field), "Field '" + field + "' is missing in actual response");

            // Handle $notnull placeholder
            if (expectedValue.isTextual() && "$notnull".equals(expectedValue.asText())) {
                Assert.assertNotNull(actualValue, "Expected field '" + field + "' to be not null");
                Assert.assertFalse(actualValue.isNull(), "Field '" + field + "' is null");
                Assert.assertFalse(actualValue.asText().isEmpty(), "Field '" + field + "' is empty");
            }

            // Recurse if object
            else if (expectedValue.isObject()) {
                Assert.assertTrue(actualValue.isObject(), "Field '" + field + "' is not an object as expected");
                validateJsonWithPlaceholders(expectedValue, actualValue);
            }

            // Recurse if array
            else if (expectedValue.isArray()) {
                Assert.assertTrue(actualValue.isArray(), "Field '" + field + "' is not an array as expected");
                Assert.assertEquals(expectedValue.size(), actualValue.size(), "Array size mismatch in field '" + field + "'");
                for (int i = 0; i < expectedValue.size(); i++) {
                    validateJsonWithPlaceholders(expectedValue.get(i), actualValue.get(i));
                }
            }

            // Else exact value match
            else {
                    // Compare values as strings, numbers, booleans etc.
                    if (expectedValue.isTextual()) {
                        Assert.assertEquals(actualValue.asText(), expectedValue.asText(),
                                "Mismatch in field '" + field + "'");
                    } else if (expectedValue.isNumber()) {
                        Assert.assertEquals(actualValue.numberValue(), expectedValue.numberValue(),
                                "Mismatch in field '" + field + "'");
                    } else if (expectedValue.isBoolean()) {
                        Assert.assertEquals(actualValue.booleanValue(), expectedValue.booleanValue(),
                                "Mismatch in field '" + field + "'");
                    } else {
                        // Fallback to deep comparison
                        Assert.assertEquals(actualValue, expectedValue,
                                "Mismatch in field '" + field + "'");
                    }
                }

        }
    }
}
