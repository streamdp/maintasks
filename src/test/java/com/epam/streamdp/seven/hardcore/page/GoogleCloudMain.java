package com.epam.streamdp.seven.hardcore.page;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

public class GoogleCloudMain {
    public static final int WAIT_TIMEOUT_SECONDS = 30;
    private static final String HOMEPAGE_URL = "https://cloud.google.com/";
    protected WebDriver driver;
    protected JavascriptExecutor jsDriver;

    @FindBy(xpath = "//input[starts-with(@class, 'devsite-search-field')]")
    private WebElement searchBox;

    public GoogleCloudMain(WebDriver driver) {
        this.driver = driver;
        jsDriver = (JavascriptExecutor) driver;
        PageFactory.initElements(driver, this);
    }

    public GoogleCloudMain openPage() {
        driver.get(HOMEPAGE_URL);
        new WebDriverWait(driver, WAIT_TIMEOUT_SECONDS).until((ExpectedCondition<Boolean>) wd ->
                ((JavascriptExecutor) wd).executeScript("return document.readyState").equals("complete"));
        return this;
    }

    public GoogleCloudSearchResults fillSearchInputFieldAndGo(String searchTerm) {
        searchBox.click();
        searchBox.sendKeys(searchTerm);
        searchBox.submit();
        return new GoogleCloudSearchResults(driver);
    }
}