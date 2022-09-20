package step_definitions;

import page_objetcs.GoogleSearchResultsPage;
import page_objetcs.TheGuardianNewsPage;
import utilities.CustomAssert;
import utilities.ExtentTestManager;
import utilities.ReadPropertiesFile;

public class World {
	TheGuardianNewsPage theGuardianNewsPage;
	GoogleSearchResultsPage googleSearchResultsPage;
	ReadPropertiesFile file;
	CustomAssert customAssert;
	ExtentTestManager extentTestManager;
}
