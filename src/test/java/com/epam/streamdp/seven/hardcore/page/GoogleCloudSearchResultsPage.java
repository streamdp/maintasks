package com.epam.streamdp.seven.hardcore.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class GoogleCloudSearchResultsPage extends GoogleCloudMainPage {
    public GoogleCloudSearchResultsPage(WebDriver driver) {
        super(driver);
    }

    public void waitingForContent() {
        webDriverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div/a[@class='gs-title']")));
    }

    public GoogleCloudPlatformPricingCalculatorPage followTheFirstLink(String searchTerm) {
        waitingForContent();
        driver.findElement(By.xpath(String.format("//div/a[@class='gs-title']/b[text()='%s']", searchTerm))).click();
        return new GoogleCloudPlatformPricingCalculatorPage(driver);
    }
}
