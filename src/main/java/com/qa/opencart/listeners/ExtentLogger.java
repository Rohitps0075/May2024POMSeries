package com.qa.opencart.listeners;

import com.qa.opencart.listeners.ExtentReportListener;
import com.aventstack.extentreports.Status;

public class ExtentLogger {

    public static void info(String message) {
        ExtentReportListener.test.get().log(Status.INFO, message);
    }

    public static void pass(String message) {
        ExtentReportListener.test.get().log(Status.PASS, message);
    }

    public static void fail(String message) {
        ExtentReportListener.test.get().log(Status.FAIL, message);
    }

    public static void warning(String message) {
        ExtentReportListener.test.get().log(Status.WARNING, message);
    }

    public static void skip(String message) {
        ExtentReportListener.test.get().log(Status.SKIP, message);
    }
}