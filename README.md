# 🚀 API Test Automation Framework

API Test Automation Framework

This project is a zero-code API test automation framework that leverages YAML to define test cases and supports advanced validations.

✨ Key Features
1.	Data-driven Testing using YAML
•	Test cases are written entirely in YAML files, making the framework easily extensible and human-readable.
•	No need to write Java code to add or update test cases.
2.	No-Code Automation
•	Users can simply define the test case details such as URI, method, headers, body, and validations in a structured YAML format.
•	The framework parses and executes all test logic using Java under the hood.
3.	Comprehensive Validation Support
•	Schema Validation: Uses JSON Schema Draft-04 to validate response structure and types.
•	Response Validation with Placeholders: Validates response using JsonNode, supporting smart assertions like $notnull to check for dynamically generated fields.


## 📁 Project Structure

```
src
 └── test
      ├── java
      │   ├── com.gyan.test             // TestNG test classes
      │   ├── com.gyan.model            // POJOs for test data
      │   ├── com.gyan.validator        // Response validators
      │   ├── com.gyan.filter           // RestAssured request filter (curl logger)
      │   └── com.gyan.util             // Utilities: YAML loader, schema validator
      └── resources
           └── testcases.yaml           // Test data in YAML format
```

## ✅ Features

- 🔁 **Data-driven** via YAML
- 🔍 **Schema Validation** using JSON Schema
- 🧪 **Custom response validations** including `$notnull` placeholders
- 📋 **Test filtering** by `testcaseType` (e.g. `REGRESSION`, `SMOKE`)
- 🌐 **Auto-generated cURL** for each request
- 📊 **Thread-safe Extent Reports**

## 🧰 Technologies Used

| Tool             | Purpose                             |
|------------------|-------------------------------------|
| Rest Assured     | API Testing                         |
| TestNG           | Test Runner                         |
| Jackson + YAML   | YAML Data Loading                   |
| Extent Reports   | HTML Reporting                      |
| JSON Schema      | Schema Validation                   |
| Maven            | Build Management                    |

## 🛠️ How to Run Tests

### 📌 Run All Tests

```bash
mvn clean test
```

## 📋 Reports

After running tests, open the generated Extent report:

```
test-output/ExtentReports/ExtentReport.html
```

## 🧹 Clean Up

```bash
mvn clean
```

## 💬 Contribution

Feel free to fork this repository and contribute! Raise issues or feature requests via GitHub issues.