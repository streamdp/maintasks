package com.epam.streamdp.nine.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class GoogleCloudSearchResultsPage extends GoogleCloudMainPage {
    public String linksLocatorTemplate = "//div/a[@class='gs-title']/b[text()='%s']";
    public By googleSiteTitle = By.xpath("//div/a[@class='gs-title']");

    public GoogleCloudSearchResultsPage(WebDriver driver) {
        super(driver);
    }

    public GoogleCloudPlatformPricingCalculatorPage followTheFirstLink(String searchTerm) {
        waitingForContent(googleSiteTitle);
        driver.findElement(By.xpath(String.format(linksLocatorTemplate, searchTerm))).click();
        return new GoogleCloudPlatformPricingCalculatorPage(driver);
    }
}
