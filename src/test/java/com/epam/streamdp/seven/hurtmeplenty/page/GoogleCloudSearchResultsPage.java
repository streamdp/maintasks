package com.epam.streamdp.seven.hurtmeplenty.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class GoogleCloudSearchResultsPage extends GoogleCloudMainPage {
    public GoogleCloudSearchResultsPage(WebDriver driver) {
        super(driver);

    }

    public GoogleCloudSearchResultsPage waitingForContent() {
        webDriverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//div/a[@class='gs-title']")));
        return this;
    }

    public GoogleCloudPlatformPricingCalculatorPage followTheFirstLink(String searchTerm) {
        driver.findElement(By.xpath(String.format("//div/a[@class='gs-title']/b[text()='%s']", searchTerm))).click();
        return new GoogleCloudPlatformPricingCalculatorPage(driver);
    }
}
