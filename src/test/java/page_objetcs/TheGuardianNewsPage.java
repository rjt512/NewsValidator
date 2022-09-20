package page_objetcs;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.PageActions;

public class TheGuardianNewsPage extends PageActions {

	WebDriver driver;

	public TheGuardianNewsPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//div[@data-test-id='facia-card']//span[@class='js-headline-text']")
	WebElement firstNews;

	public String fetchFirstNews() {
		return getElementText(firstNews, "First News on the site - The Guardian");
	}

}
