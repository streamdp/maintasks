package com.epam.streamdp.nine.page;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class GoogleCloudMainPage extends CommonPage {
    private static final String HOMEPAGE_URL = "https://cloud.google.com/";
    @FindBy(name = "q")
    private WebElement searchBox;

    public GoogleCloudMainPage(WebDriver driver) {
        this.driver = driver;
        jsDriver = (JavascriptExecutor) driver;
        webDriverWait = new WebDriverWait(driver, WAIT_TIMEOUT_SECONDS);
        PageFactory.initElements(driver, this);
    }

    public GoogleCloudMainPage openPage() {
        driver.get(HOMEPAGE_URL);
        waitReadyStateIsComplete();
        return this;
    }

    public GoogleCloudSearchResultsPage fillSearchInputFieldAndGo(String searchTerm) {
        webDriverWait.until(ExpectedConditions.elementToBeClickable(searchBox)).click();
        searchBox.sendKeys(searchTerm);
        searchBox.submit();
        return new GoogleCloudSearchResultsPage(driver);
    }
}