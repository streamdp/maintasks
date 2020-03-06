package com.epam.streamdp.seven.hurtmeplenty.page;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Objects;

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
        webDriverWait = new WebDriverWait(driver, WAIT_TIMEOUT_SECONDS);
        jsDriver = (JavascriptExecutor) driver;
        PageFactory.initElements(driver, this);
    }

    public GoogleCloudMainPage openPage() {
        driver.get(HOMEPAGE_URL);
        webDriverWait.until((ExpectedCondition<Boolean>) wd ->
                ((JavascriptExecutor) Objects.requireNonNull(wd)).executeScript("return document.readyState").equals("complete"));
        return this;
    }

    public GoogleCloudSearchResultsPage fillSearchInputFieldAndGo(String searchTerm) {
        searchBox.click();
        searchBox.sendKeys(searchTerm);
        searchBox.submit();
        return new GoogleCloudSearchResultsPage(driver);
    }
}