package utils;

import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;

public class ReportManager {

    private static ThreadLocal<ExtentTest> test = new ThreadLocal<>();

    public static void setTest(ExtentTest extentTest) {
        test.set(extentTest);
    }

    public static ExtentTest getTest() {
        return test.get();
    }

    public static void info(String message) {
        if (test.get() != null) {
            test.get().info(message);
        }
    }

    public static void info(String message, String screenshotPath) {
        if (test.get() != null) {
            test.get().info(message, MediaEntityBuilder.createScreenCaptureFromPath(screenshotPath).build());
        }
    }

    public static void pass(String message) {
        if (test.get() != null) {
            test.get().pass(message);
        }
    }

    public static void pass(String message, String screenshotPath) {
        if (test.get() != null) {
            test.get().pass(message, MediaEntityBuilder.createScreenCaptureFromPath(screenshotPath).build());
        }
    }

    public static void fail(String message) {
        if (test.get() != null) {
            test.get().fail(message);
        }
    }

    public static void fail(String message, String screenshotPath) {
        if (test.get() != null) {
            test.get().fail(message, MediaEntityBuilder.createScreenCaptureFromPath(screenshotPath).build());
        }
    }
}