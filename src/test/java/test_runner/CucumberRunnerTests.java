package test_runner;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.attribute.BasicFileAttributes;
import java.time.ZoneId;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import utilities.ExtentTestManager;

@CucumberOptions(features = { "src/test/resources/features/" }, glue = { "step_definitions", "utilities" }, plugin = {
		"pretty" }, monochrome = true)
public class CucumberRunnerTests extends AbstractTestNGCucumberTests {

	@Override
	@DataProvider(parallel = false)
	public Object[][] scenarios() {
		return super.scenarios();
	}

	@BeforeSuite
	public static void beforeAll() throws Exception {
		backupReport();
		ExtentTestManager.generateExtentReport();
	}

	@AfterSuite
	public static void afterAll() {
		ExtentTestManager.flushExtentReports();
	}

	// This method takes a backup of last generated html extent report by renaming it, if there is any, so that it is not lost.
	// ExecutionReports folder is created automatically if not present
	public static void backupReport() throws Exception {
		try {
			File oldReport = new File("./ExecutionReports/LatestExecutionReport.html");
			if (oldReport.exists()) {
				// Use BasicFileAttributes to get creation date of last generated html extent
				// report to use it in backup name so that its unique
				BasicFileAttributes attr = Files.readAttributes(oldReport.toPath(), BasicFileAttributes.class);
				File newFile = new File("./ExecutionReports/Report_"
						+ attr.creationTime().toInstant().atZone(ZoneId.systemDefault()).toLocalDateTime() + ".html");
				// rename the last generated html extent report if any
				oldReport.renameTo(newFile);
			}
		} catch (Exception exception) {
			System.out.println(exception.getMessage());
			throw exception;
		}
	}

}