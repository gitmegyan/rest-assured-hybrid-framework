package com.gyan.listeaners;

import lombok.experimental.UtilityClass;

@UtilityClass
public final class ExtentReportLogger {

    public static void logInfo(String message) {
        ExtentReportManager.getExtentTest().info(message);
    }

    public static void logError(String message) {
        ExtentReportManager.getExtentTest().fail(message);
    }

    public static void logPass(String message) {
        ExtentReportManager.getExtentTest().pass(message);
    }
}
