package listeners;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;

import base.BaseTest;
import utils.ExtentManager;
import utils.ScreenshotUtil;
import utils.ReportManager;

public class TestListener implements ITestListener {

        private static ExtentReports extent = ExtentManager.getInstance();

        private static ThreadLocal<ExtentTest> test = new ThreadLocal<>();
        @Override
        public void onTestStart(ITestResult result) {

                BaseTest base = (BaseTest) result.getInstance();

                String browserName = "N/A";

                if (result.getParameters() != null
                                && result.getParameters().length > 0) {

                        browserName = result.getParameters()[0].toString();
                }

                ExtentTest extentTest = extent.createTest(
                                result.getMethod().getRealClass().getSimpleName()
                                                + "."
                                                + result.getMethod().getMethodName()
                                                + " ["
                                                + browserName
                                                + "]");

                test.set(extentTest);
                ReportManager.setTest(extentTest); 

                test.get().info("Test Execution Started");
        }

        @Override
        public void onTestSuccess(ITestResult result) {

                test.get().pass("Test Passed");

                try {

                        BaseTest base = (BaseTest) result.getInstance();

                        String screenshotPath = ScreenshotUtil.capture(
                                        base.getPage(),
                                        result.getTestClass()
                                                        .getRealClass()
                                                        .getSimpleName(),
                                        result.getMethod()
                                                        .getMethodName(),
                                        0,
                                        "PASS");

                        test.get()
                                        .addScreenCaptureFromPath(
                                                        screenshotPath);

                } catch (Exception e) {

                        test.get().warning(
                                        "PASS Screenshot failed : "
                                                        + e.getMessage());
                }
        }

        @Override
        public void onTestFailure(ITestResult result) {

                if (test.get() == null) {

                        ExtentTest extentTest = extent.createTest(
                                        result.getMethod()
                                                        .getMethodName());

                        test.set(extentTest);
                        ReportManager.setTest(extentTest); 
                }

                test.get().fail(result.getThrowable());
                try {

                        BaseTest base = (BaseTest) result.getInstance();

                        String className = result.getTestClass()
                                        .getRealClass()
                                        .getSimpleName();

                        String methodName = result.getMethod()
                                        .getMethodName();

                        int lineNumber = 0;

                        if (result.getThrowable() != null
                                        && result.getThrowable()
                                                        .getStackTrace().length > 0) {

                                lineNumber = result.getThrowable()
                                                .getStackTrace()[0]
                                                .getLineNumber();
                        }

                        String screenshotPath = ScreenshotUtil.capture(
                                        base.getPage(),
                                        className,
                                        methodName,
                                        lineNumber,
                                        "FAIL");

                        test.get()
                                        .addScreenCaptureFromPath(
                                                        screenshotPath);

                        test.get().info(
                                        "Screenshot : "
                                                        + screenshotPath);

                } catch (Exception e) {

                        test.get().fail(
                                        "Screenshot capture failed : "
                                                        + e.getMessage());
                }
        }

        @Override
        public void onTestSkipped(ITestResult result) {

                test.get().skip("Test Skipped");
        }

        @Override
        public void onFinish(ITestContext context) {

                System.out.println(
                                "FLUSHING REPORT");

                extent.flush();
        }
}