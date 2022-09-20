package utilities;

import java.util.Collections;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Reporter;
import io.github.bonigarcia.wdm.WebDriverManager;

//This class is created to control WebDriver instance
public class DriverClass {

	private WebDriver webDriver;

	public void initiateBrowser() {

		String browserName;

		browserName = Reporter.getCurrentTestResult().getTestContext().getCurrentXmlTest().getParameter("browser");

		// WebDriverManager is to automate the management of browser drivers

		if (browserName.equalsIgnoreCase("chrome")) {
			WebDriverManager.chromedriver().setup();
			ChromeOptions options = new ChromeOptions();
			// Option to remove automation info bar from top of browser
			options.setExperimentalOption("useAutomationExtension", false);
			options.setExperimentalOption("excludeSwitches", Collections.singletonList("enable-automation"));

			webDriver = new ChromeDriver(options);
		} else if (browserName.equalsIgnoreCase("firefox")) {
			WebDriverManager.firefoxdriver().setup();
			webDriver = new FirefoxDriver();
		} else if (browserName.equalsIgnoreCase("edge")) {
			WebDriverManager.edgedriver().setup();
			webDriver = new EdgeDriver();
		} else {
			System.out.println("Error Message ----> browser name not mentioned properly");
			System.exit(0);
		}
		webDriver.manage().window().maximize();
		ExtentTestManager.getExtentTest().info("Successfully initiated " + browserName + " browser");
	}

	// Method to navigate to specific url
	public void navigateToURL(String url) {
		webDriver.get(url);
		ExtentTestManager.getExtentTest().info("Navigated to URL - <b>" + url + "</b>");
	}

	// Method to quit browser
	public void quitDriver() {
		webDriver.quit();
		ExtentTestManager.getExtentTest().info("Browser closed");
	}

	// Method to get browser/driver instance
	public WebDriver getDriver() {
		// get browser/driver instance
		return webDriver;
	}
}
