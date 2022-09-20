Feature: Validating the first news published on The Guardian site
 
 Scenario: NewsValidator fetches the first news from The Guardian site and compares it with few other sources
   Given NewsValidator has fetched the first news from The Guardian site - "https://www.theguardian.com/tone/news"
   When NewsValidator searches for the news on google to compare it with few other sources
   Then NewsValidator returns the number of other sources publishing similar news