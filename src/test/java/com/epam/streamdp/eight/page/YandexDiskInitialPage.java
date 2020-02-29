package com.epam.streamdp.eight.page;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

public class YandexDiskInitialPage {
    public static final int WAIT_TIMEOUT_SECONDS = 30;
    private static final String HOMEPAGE_URL = "https://disk.yandex.ru/";
    protected WebDriver driver;
    protected WebDriverWait webDriverWait;
    private By buttonLogin = By.xpath("//*[@class='button button_login header__login-link']");

    public YandexDiskInitialPage(WebDriver driver) {
        this.driver = driver;
        webDriverWait = new WebDriverWait(driver, WAIT_TIMEOUT_SECONDS);
    }

    public YandexDiskInitialPage openPage() {
        driver.get(HOMEPAGE_URL);
        webDriverWait.until((ExpectedCondition<Boolean>) wd ->
                ((JavascriptExecutor) wd).executeScript("return document.readyState").equals("complete"));
        return this;
    }

    public YandexPassportPage goTologinPage() {
        driver.findElement(buttonLogin).click();
        return new YandexPassportPage(driver);
    }
}