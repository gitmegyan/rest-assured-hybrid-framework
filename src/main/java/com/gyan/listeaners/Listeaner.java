package com.gyan.listeaners;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import org.testng.ISuiteListener;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Map;
import java.util.Objects;

import static com.gyan.listeaners.ExtentReportLogger.logError;

public class Listeaner implements ITestListener, ISuiteListener {
    private static ExtentReports extentReports;

    public void onTestFailure(ITestResult tr) {
        Throwable failureReson = tr.getThrowable();
        if(Objects.nonNull(failureReson)) {
            logError("Test Failed: " + failureReson.getMessage());
            String stackTrace = Arrays.toString(failureReson.getStackTrace());
            logError("<pre>" + stackTrace + "</pre>");
        } else {
            logError("Test Failed with unknown reasons");
        }

    }

    public void onTestSuccess(ITestResult tr) {

    }

    public void onTestSkipped(ITestResult tr) {

    }

    public void onStart(ITestContext context) {
        ExtentSparkReporter sparkReporter = new ExtentSparkReporter("test-output/ExtentReport.html");
        sparkReporter.config().setReportName("Automation Test Results");
        sparkReporter.config().setDocumentTitle("Extent Report");
        extentReports = new ExtentReports();
        extentReports.attachReporter(sparkReporter);

    }


    public void onTestStart(ITestResult result) {
        String name = result.getMethod().getMethodName();
        String description = "";//
        Map<String, String> nameAndDesc = getTestCaseNameAndDescription(result);
        if (nameAndDesc != null && !nameAndDesc.isEmpty()) {
            Map.Entry<String, String> entry = nameAndDesc.entrySet().iterator().next();
            name = entry.getKey();
            description = entry.getValue();
        }

        ExtentTest extentTest = extentReports.createTest(name, description);
        ExtentReportManager.setExtentTest(extentTest);
    }


    public void onFinish(ITestContext context) {
        extentReports.flush();
        ExtentReportManager.removeExtentTest();
    }


    private Map<String, String> getTestCaseNameAndDescription(ITestResult tr) {
        Object[] parameters = tr.getParameters();
        for(Object param: parameters) {
            try {
                Class<?> clazz = param.getClass();
                Method getTestacaseId = clazz.getMethod("getTestcaseId");
                Method getTestacaseDescription = clazz.getMethod("getTestcaseDescription");
                String testcaseId = (String) getTestacaseId.invoke(param);
                String testcaseDescription = (String) getTestacaseDescription.invoke(param);
                return Map.of(testcaseId, testcaseDescription);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        return null;
    }

}
