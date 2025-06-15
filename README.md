# 🚀 API Test Automation Framework

This is a modular and extensible REST API test automation framework built using:

- **Rest Assured** for API testing
- **TestNG** for test orchestration
- **Extent Reports** for rich HTML reporting
- **Jackson + SnakeYAML** for reading test data from `.yaml` files
- **Maven** for build and dependency management

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