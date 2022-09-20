package utilities;

import java.util.HashMap;
import java.util.Map;
import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentTestManager {

	static ExtentReports extentReports = new ExtentReports();
	static Map<Integer, ExtentTest> extentTestMap = new HashMap<>();

	// To generate extent report
	public static ExtentReports generateExtentReport() {
		ExtentSparkReporter extentSparkReporter = new ExtentSparkReporter(
				"./ExecutionReports/LatestExecutionReport.html");
		extentSparkReporter.config().setReportName("News Validator");
		extentReports.attachReporter(extentSparkReporter);
		return extentReports;
	}

	// Get thread safe instance of extent report
	public static ExtentTest getExtentTest() {
		return extentTestMap.get((int) (long) (Thread.currentThread().getId()));
	}

	// Start extent test per scenario
	public ExtentTest startExtentTest(String testName) {
		ExtentTest extentTest = extentReports.createTest("<b><big>" + testName + "</big></b>");
		extentTestMap.put((int) (long) (Thread.currentThread().getId()), extentTest);
		return extentTest;
	}

	// Flush extent report instance
	public static void flushExtentReports() {
		extentReports.flush();
	}

}
