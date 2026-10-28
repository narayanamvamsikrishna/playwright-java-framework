package utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentManager {

    private static ExtentReports extent;

    public static String executionFolder =
            LocalDateTime.now()
                    .format(
                            DateTimeFormatter.ofPattern(
                                    "yyyyMMdd_HHmmss"));

    public static ExtentReports getInstance() {

        if (extent == null) {

            String reportPath =
                    "reports/"
                    + executionFolder
                    + "/ExtentReport.html";

            ExtentSparkReporter spark =
                    new ExtentSparkReporter(reportPath);

            extent = new ExtentReports();

            extent.attachReporter(spark);

            extent.setSystemInfo(
                    "Framework",
                    "Playwright + Java + TestNG");
        }

        return extent;
    }
}