package utilities;

import java.time.Duration;
import java.util.StringTokenizer;
import org.openqa.selenium.By;
import org.openqa.selenium.NotFoundException;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

//This class is created to customise selenium webdriver methods to handle exceptions, element waits and log steps to HTML report
public class PageActions extends DriverClass {

	// Method to wait for an element to be present on web page
	public void waitForElement(WebElement webElement) {
		try {
			WebDriverWait wait = new WebDriverWait(getDriver(), Duration.ofSeconds(30));
			wait.until(ExpectedConditions.visibilityOf(webElement));
		} catch (TimeoutException e) {
			throw e;
		}
	}

	// Method to check if an element is displayed
	public boolean isDisplayed(By by, String elementName) {
		boolean isDisplayed;
		try {
			isDisplayed = getDriver().findElement(by).isDisplayed();
			ExtentTestManager.getExtentTest().info("Web element <b>" + elementName + "</b> is Displayed");
		} catch (NotFoundException e) {
			isDisplayed = false;
			ExtentTestManager.getExtentTest().fail("Web element <b>" + elementName + "</b> is not Displayed");
		}
		return isDisplayed;
	}

	// Method to to check if an element is enabled
	public boolean isEnabled(WebElement webElement, String elementName) {
		boolean isEnabled;
		try {
			isEnabled = webElement.isEnabled();
		} catch (NotFoundException e) {
			isEnabled = false;
			throw e;
		}
		return isEnabled;
	}

	// Method to click on an element
	public void click(WebElement webElement, String elementName) {
		try {
			if (isEnabled(webElement, elementName)) {
				webElement.click();
				ExtentTestManager.getExtentTest().info("Clicked web element <b>" + elementName + "</b>");
			}
		} catch (Exception e) {
			ExtentTestManager.getExtentTest().fail("Failed to click web element <b>" + elementName + "</b>");
			throw e;
		}
	}

	// Method to type in an element
	public void type(WebElement webElement, String textToType, String elementName) {
		try {
			if (isEnabled(webElement, elementName)) {
				webElement.sendKeys(textToType);
				ExtentTestManager.getExtentTest()
						.info("Typed " + textToType + " in text field <b>" + elementName + "</b>");
			}
		} catch (Exception e) {
			ExtentTestManager.getExtentTest()
					.fail("Failed to type " + textToType + " in text field <b>" + elementName + "</b>");
			throw e;
		}
	}

	// Method to get element text
	public String getElementText(WebElement webElement, String elementName) {
		String text = "";
		try {
			text = webElement.getText();
		} catch (Exception e) {
			throw e;
		}
		return text;
	}

	// Method to get page title
	public String getPageTitle() throws InterruptedException {
		Thread.sleep(2000);
		return getDriver().getTitle();
	}

	// Get word count in a String
	public int getWordCount(String string) {
		StringTokenizer stringTokenizer = new StringTokenizer(string);
		return stringTokenizer.countTokens();
	}

}
