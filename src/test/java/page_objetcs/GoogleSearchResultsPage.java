package page_objetcs;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.PageActions;

public class GoogleSearchResultsPage extends PageActions {

	WebDriver driver;

	public GoogleSearchResultsPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//div[h1[text()='Search Results']]//a/h3")
	List<WebElement> newsOnGoogleLink;

	@FindBy(xpath = "//div[text()='Accept all']")
	WebElement acceptAllButton;

	public void acceptAllCookies() {
		click(acceptAllButton, "Accept All Cookies Button"); // Accept cookies popup on google after deeplinking
	}

	public int validateNews(String firstNews) {
		int similarityCounter = 0;

		int wordsInFirstNews = getWordCount(firstNews); // To get number of words in the First news from The Guardian site
		String newsArray[] = firstNews.trim().split("\\W"); // Covert firstNews string to Array

		for (WebElement webElement : newsOnGoogleLink) { // Loop for each search result found on google

			if (getSimilarNewsWordCount(newsArray, webElement) >= (wordsInFirstNews / 2)) { // check if other source(google search) result has 50%+ similar words as firstNews
				similarityCounter++;
			}

		}

		return similarityCounter; // Return number of other source(google search) result having 50%+ similar words as firstNews
	}

	// To get number of similar words between 'First news from The Guardian site' and other source(google search)
	public int getSimilarNewsWordCount(String array[], WebElement webElement) {

		List<String> firstNewsList = new ArrayList<String>(Arrays.asList(array));
		firstNewsList.removeAll(Arrays.asList(" ", "  "));

		String searchResultArray[] = webElement.getText().trim().split("\\W");
		List<String> searchResultList = Arrays.asList(searchResultArray);
		searchResultList.removeAll(Arrays.asList(" ", "  "));

		firstNewsList.retainAll(searchResultList);

		return firstNewsList.size();
	}

}
