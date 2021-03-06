package com.epam.streamdp.eight.page;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;

import java.util.Objects;

public class YandexDiskInitialPage extends BasePage {
    private static final String HOMEPAGE_URL = "https://disk.yandex.ru/";
    private By loginButton = By.cssSelector("a.button.button_login.header__login-link");

    public YandexDiskInitialPage(WebDriver driver) {
        super(driver);
    }

    public YandexDiskInitialPage openPage() {
        driver.get(HOMEPAGE_URL);
        webDriverWait.until((ExpectedCondition<Boolean>) wd ->
                ((JavascriptExecutor) Objects.requireNonNull(wd)).executeScript("return document.readyState").equals("complete"));
        return this;
    }

    public YandexPassportPage goTologinPage() {
        driver.findElement(loginButton).click();
        return new YandexPassportPage(driver);
    }
}