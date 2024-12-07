package io.weconnect.automation.reporting;

import java.io.File;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class TestNGListener implements ITestListener {
    private static ExtentReports extentReports;
    private static ExtentTest test;
    private static final String BASE_TEST_RESULT_DIRECTORY = "./ScreenshotsAndReport/";

    // Static block to initialize ExtentReports and ensure the result directory exists
    static {
        File directory = new File(BASE_TEST_RESULT_DIRECTORY);
        if (!directory.exists()) {
            boolean created = directory.mkdir();
            System.out.println(" create result directory: " + BASE_TEST_RESULT_DIRECTORY);

            if (!created) {
                System.out.println("Failed to create result directory: " + BASE_TEST_RESULT_DIRECTORY);
            }
        }

        extentReports = new ExtentReports();
        ExtentSparkReporter extentSparkReporter = new ExtentSparkReporter(BASE_TEST_RESULT_DIRECTORY + "ExtentReport.html");
        extentReports.attachReporter(extentSparkReporter);
    }

    @Override
    public void onTestStart(ITestResult result) {
        String testName = result.getMethod().getMethodName();
        String testDescription = result.getMethod().getDescription();
        test = extentReports.createTest(testName, testDescription);
        System.out.println("Test Started: " + testName);
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        String testName = result.getMethod().getMethodName();
        test.pass("Test passed: " + testName);
        System.out.println("Test Passed: " + testName);
    }

    @Override
    public void onTestFailure(ITestResult result) {
        String testName = result.getMethod().getMethodName();
        test.fail("Test failed: " + testName);
        if (result.getThrowable() != null) {
            test.fail(result.getThrowable());
        }
        System.out.println("Test Failed: " + testName);
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        String testName = result.getMethod().getMethodName();
        test.skip("Test skipped: " + testName);
        if (result.getThrowable() != null) {
            test.skip(result.getThrowable());
        }
        System.out.println("Test Skipped: " + testName);
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        // Optional: Handle partially successful tests if required
    }

    @Override
    public void onTestFailedWithTimeout(ITestResult result) {
        String testName = result.getMethod().getMethodName();
        test.fail("Test failed with timeout: " + testName);
        if (result.getThrowable() != null) {
            test.fail(result.getThrowable());
        }
        System.out.println("Test Failed with Timeout: " + testName);
    }

    @Override
    public void onStart(ITestContext context) {
        System.out.println("Test Execution Started: " + context.getName());
    }

    @Override
    public void onFinish(ITestContext context) {
        System.out.println("Test Execution Finished: " + context.getName());
        extentReports.flush(); // Write the test results to the report
    }
}
