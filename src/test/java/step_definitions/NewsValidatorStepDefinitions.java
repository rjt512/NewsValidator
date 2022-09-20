package step_definitions;

import org.testng.Reporter;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import page_objetcs.GoogleSearchResultsPage;
import page_objetcs.TheGuardianNewsPage;
import utilities.CustomAssert;
import utilities.ExtentTestManager;
import utilities.PageActions;
import utilities.ReadPropertiesFile;

public class NewsValidatorStepDefinitions extends PageActions {

	private World world;
	String firstNews = "";

	public NewsValidatorStepDefinitions(World world) {
		this.world = world;
	}

	@Before
	public void beforeScenario(Scenario scenario) throws Exception {
		world.extentTestManager = new ExtentTestManager();
		String browserName = Reporter.getCurrentTestResult().getTestContext().getCurrentXmlTest().getName();
		world.extentTestManager.startExtentTest(browserName + " - " + scenario.getName()).assignAuthor("Rohit Tiwari");
		initiateBrowser();
		world.customAssert = new CustomAssert(getDriver());
	}

	@Given("NewsValidator has fetched the first news from The Guardian site - {string}")
	public void readFirstNews(String url) throws Exception {
		navigateToURL(url);
		world.theGuardianNewsPage = new TheGuardianNewsPage(getDriver());
		firstNews = world.theGuardianNewsPage.fetchFirstNews();
	}

	@When("NewsValidator searches for the news on google to compare it with few other sources")
	public void searchOnGoogle() throws Exception {
		// Deeplinking to google search that will query our first news
		world.file = new ReadPropertiesFile();
		navigateToURL(world.file.returnPropertyValue("deeplinkURL") + firstNews);
		world.googleSearchResultsPage = new GoogleSearchResultsPage(getDriver());
		world.googleSearchResultsPage.acceptAllCookies();
	}

	@Then("NewsValidator returns the number of other sources publishing similar news")
	public void returnNumberOfSources() {
		int similarNewsCount = world.googleSearchResultsPage.validateNews(firstNews);
		world.customAssert.assertEquals(similarNewsCount > 1, true,
				"Verifing that there are atleast 2 sources that have similar news as The Guardian.");
	}

	@After
	public void afterScenario(Scenario scenario) throws Exception {
		quitDriver();
	}
}
