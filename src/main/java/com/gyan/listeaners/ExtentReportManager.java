package com.gyan.listeaners;

import com.aventstack.extentreports.ExtentTest;

public final class ExtentReportManager {
    private static ThreadLocal<ExtentTest> extentTest = new ThreadLocal<>();

    private ExtentReportManager() {

    }

    public static ExtentTest getExtentTest() {
        return extentTest.get();
    }

    public static void setExtentTest(ExtentTest test) {
        extentTest.set(test);
    }

    public static void removeExtentTest() {
        extentTest.remove();
    }
}
