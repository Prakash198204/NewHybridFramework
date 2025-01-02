package listener;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;

import dataprovider.ConfigReader;
import factory.BrowserFactory;
import helper.Utility;

public class ReportListener implements ITestListener {

	ExtentReports extent = ExtentManager.getInstance();
	ExtentTest extentTest;

	public void onTestStart(ITestResult result) {
		extentTest = extent.createTest(result.getMethod().getMethodName());
	}

	public void onTestSuccess(ITestResult result) {
		String screenshot = Utility.getScreenshotAsBase64(BrowserFactory.getdriverInstance());
		if (ConfigReader.getValue("screenshotOnSuccess").equalsIgnoreCase("true")) {
			extentTest.pass("Test executed successfully",
					MediaEntityBuilder.createScreenCaptureFromBase64String(screenshot).build());
		} else {
			extentTest.pass("Test executed successfully");
		}
	}

	public void onTestFailure(ITestResult result) {
		String screenshot = Utility.getScreenshotAsBase64(BrowserFactory.getdriverInstance());
		if (ConfigReader.getValue("screenshotOnFailure").equalsIgnoreCase("true")) {
			extentTest.fail("Test Failed" + result.getThrowable().getMessage(),
					MediaEntityBuilder.createScreenCaptureFromBase64String(screenshot).build());
		} else {
			extentTest.fail("Test Failed" + result.getThrowable().getMessage());
		}
	}

	public void onTestSkipped(ITestResult result) {
		String screenshot = Utility.getScreenshotAsBase64(BrowserFactory.getdriverInstance());
		if (ConfigReader.getValue("screenshotOnSkip").equalsIgnoreCase("true")) {
			extentTest.skip("Test Skipped" + result.getThrowable().getMessage());
		} else {
			extentTest.skip("Test Skipped" + result.getThrowable().getMessage(),
					MediaEntityBuilder.createScreenCaptureFromBase64String(screenshot).build());
		}

	}

	public void onFinish(ITestContext context) {
		extent.flush();
	}

}
