package utilities;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;
import com.aventstack.extentreports.MediaEntityBuilder;

public class CustomAssert {

	SoftAssert softAssert = new SoftAssert();
	WebDriver webDriver;

	public CustomAssert(WebDriver webDriver) {
		this.webDriver = webDriver;
	}

	// Hard assert 2 String values
	public void assertEquals(String actualResult, String expectedResult, String assertDescription) {
		try {
			Assert.assertEquals(actualResult, expectedResult, assertDescription);
			ExtentTestManager.getExtentTest().pass(
					"<b>Assertion Point - </b>" + assertDescription + " - Passed  <br><b> Expected : </b>"
							+ expectedResult + "<br><b> Actual : </b>" + actualResult + "<br>",
					MediaEntityBuilder.createScreenCaptureFromBase64String(returnBase64ScreenCapture()).build());
		} catch (AssertionError assertionError) {
			ExtentTestManager.getExtentTest().fail(
					"<b>Assertion Point - </b>" + assertDescription + " - Failed  <br><b> Expected : </b>"
							+ expectedResult + "<br><b> Actual : </b>" + actualResult + "<br>",
					MediaEntityBuilder.createScreenCaptureFromBase64String(returnBase64ScreenCapture()).build());
			throw assertionError;
		} catch (Exception exception) {
			ExtentTestManager.getExtentTest().fail(" An unknown exception occured - <b>" + exception,
					MediaEntityBuilder.createScreenCaptureFromBase64String(returnBase64ScreenCapture()).build());
			throw exception;
		}
	}

	// Hard assert 2 boolean values
	public void assertEquals(boolean actualResult, boolean expectedResult, String assertDescription) {
		try {
			Assert.assertEquals(actualResult, expectedResult, assertDescription);
			ExtentTestManager.getExtentTest().pass(
					"<b>Assertion Point - </b>" + assertDescription + " - Passed  <br><b> Expected : </b>"
							+ expectedResult + "<br><b> Actual : </b>" + actualResult + "<br>",
					MediaEntityBuilder.createScreenCaptureFromBase64String(returnBase64ScreenCapture()).build());
		} catch (AssertionError assertionError) {
			ExtentTestManager.getExtentTest().fail(
					"<b>Assertion Point - </b>" + assertDescription + " - Failed  <br><b> Expected : </b>"
							+ expectedResult + "  <b> Actual : </b>" + actualResult + "<br>",
					MediaEntityBuilder.createScreenCaptureFromBase64String(returnBase64ScreenCapture()).build());
			throw assertionError;
		} catch (Exception exception) {
			ExtentTestManager.getExtentTest().fail(" An unknown exception occured - <b>" + exception,
					MediaEntityBuilder.createScreenCaptureFromBase64String(returnBase64ScreenCapture()).build());
			throw exception;
		}
	}

	// Soft assert 2 String values
	public void softAssertEquals(String actualResult, String expectedResult, String assertDescription) {
		try {
			softAssert.assertEquals(actualResult, expectedResult, assertDescription);
			ExtentTestManager.getExtentTest().pass(
					"<b>Assertion Point - </b>" + assertDescription + " - Passed  <br><b> Expected : </b>"
							+ expectedResult + "<br><b> Actual : </b>" + actualResult + "<br>",
					MediaEntityBuilder.createScreenCaptureFromBase64String(returnBase64ScreenCapture()).build());
		} catch (AssertionError assertionError) {
			ExtentTestManager.getExtentTest().fail(
					"<b>Assertion Point - </b>" + assertDescription + " - Failed  <br><b> Expected : </b>"
							+ expectedResult + "<br><b> Actual : </b>" + actualResult + "<br>",
					MediaEntityBuilder.createScreenCaptureFromBase64String(returnBase64ScreenCapture()).build());
		} catch (Exception exception) {
			ExtentTestManager.getExtentTest().fail(" An unknown exception occured - <b>" + exception,
					MediaEntityBuilder.createScreenCaptureFromBase64String(returnBase64ScreenCapture()).build());
			throw exception;
		}
	}

	public void softAsertAll() {
		softAssert.assertAll();
	}

	// Get screenshot as base64
	public String returnBase64ScreenCapture() {
		return ((TakesScreenshot) webDriver).getScreenshotAs(OutputType.BASE64);
	}
}
