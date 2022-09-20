# JPMC assignment for SDET role.

## Website under test –
  https://www.theguardian.com/tone/news

## Problem statement –
  For the first news article on https://www.theguardian.com/tone/news your task is to search google/other resources for similar information to confirm that the article is valid.

### Solution – 
   1. First, I fetched the first news published on https://www.theguardian.com/tone/news
   2. Then I used Google’s query URL + first news fetched to directly deeplink to search results. I did not perform actions on google’s website to search for the news as deeplinking is faster and saves time.
   3. Then I fetched the text of all the search results for iteration.
   4. Then I compared first news with each search result text to find out the similar words.
   5. A counter increases if (number of similar words > (number of words in first news/2)).
   6. After the final iteration, we get number of search results that have 50%+ similar words as first news.
   7. At last, I asserted that this counter should be greater than 1 which means there are at least 2 search results that have 50%+ similar words as first news.


## Scenarios Covered –
   NewsValidator fetches the first news from The Guardian site and compares it with few other sources.       

## Tools and frameworks used –
- Selenium 4.0.0-beta-1
- Cucumber Java
- Apache Maven
- Extent Reports
- TestNG
- WebDriverManager (Library to automate the management of drivers)
- Junit
- cucumber-picocontainer– Dependency Injection framework

## Prerequisites –
- Maven command line tool if running from terminal – brew install maven
- Java 8+
- Chrome and Firefox browsers – This project runs on these browsers.

## Steps to configure and execute through terminal –
  1. Download zip and extract or clone the GIT repository locally.
  2. Change directory to NewsValidator(base directory of project) in Terminal.
  4. Run mvn clean
  5. Run mvn install – to install pom.xml dependencies and run test.
  6. Second time onwards, run mvn test to execute tests.


## Steps to configure and execute in Eclipse– 
  1. Download Zip and extract or clone the GIT repository locally.
  2. Import in Eclipse as a maven project.
  3. This project runs on Chome and Firefox browsers by default and are configured in testNG.xml. Make sure the latest version of that browser is installed on the machine.
  4. Once imported, right click project -> select Run As -> Maven clean. 
  5. Right click project -> select Run As -> Maven install.
     This will install all the dependencies added in pom.xml and it will also run the tests as I have added testNG.xml in pom.xml.
  6. To execute second time onwards, Run As -> Maven test.
  7. Once execution finishes, refresh the project in eclipse to load the reports and then refer 'LatestExecutionReport.html' from ExecutionReports folder to view extent report with screenshots. 

## To view report, 
  Right click on Report -> Open with -> Web Browser. 
  Screenshot can be viewed by clicking `base64 img` icon on the report - 
