package com.epam.streamdp.seven.hardcore.page;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

public class GoogleCloudMainPage {
    public static final int WAIT_TIMEOUT_SECONDS = 30;
    private static final String HOMEPAGE_URL = "https://cloud.google.com/";
    protected WebDriver driver;
    protected WebDriverWait webDriverWait;
    protected JavascriptExecutor jsDriver;

    @FindBy(xpath = "//input[starts-with(@class, 'devsite-search-field')]")
    private WebElement searchBox;

    public GoogleCloudMainPage(WebDriver driver) {
        this.driver = driver;
        jsDriver = (JavascriptExecutor) driver;
        webDriverWait = new WebDriverWait(driver, WAIT_TIMEOUT_SECONDS);
        PageFactory.initElements(driver, this);
    }

    public GoogleCloudMainPage openPage() {
        driver.get(HOMEPAGE_URL);
        webDriverWait.until((ExpectedCondition<Boolean>) wd ->
                ((JavascriptExecutor) wd).executeScript("return document.readyState").equals("complete"));
        return this;
    }

    public GoogleCloudSearchResultsPage fillSearchInputFieldAndGo(String searchTerm) {
        searchBox.click();
        searchBox.sendKeys(searchTerm);
        searchBox.submit();
        return new GoogleCloudSearchResultsPage(driver);
    }
}