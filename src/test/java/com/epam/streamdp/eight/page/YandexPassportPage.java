package com.epam.streamdp.eight.page;

import com.epam.streamdp.eight.User;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Objects;

import static com.epam.streamdp.eight.page.YandexDiskInitialPage.WAIT_TIMEOUT_SECONDS;

public class YandexPassportPage {
    private static final String URL_MATCH = "passport.yandex";

    protected WebDriver driver;
    protected WebDriverWait webDriverWait;
    protected Actions builder;

    private By loginField = By.id("passp-field-login");
    private By passwordField = By.id("passp-field-passwd");
    private By submitButton = By.xpath("//button[@type='submit']");
    private By errorMessage = By.xpath("//div[@class='passp-form-field__error']");

    public YandexPassportPage(WebDriver driver) {
        if (!driver.getCurrentUrl().contains(URL_MATCH)) {
            throw new IllegalStateException("This is not the page you are expected");
        }
        this.driver = driver;
        webDriverWait = new WebDriverWait(driver, WAIT_TIMEOUT_SECONDS);
        builder = new Actions(driver);
    }

    public void waitingForContent() {
        webDriverWait.until((ExpectedCondition<Boolean>) wd ->
                ((JavascriptExecutor) Objects.requireNonNull(wd)).executeScript("return document.readyState").equals("complete"));
        builder.pause(100).build().perform();
    }

    public YandexDiskMainPage sentCredentials(User user) {
        sentLogin(user);
        sentPassword(user);
        return new YandexDiskMainPage(driver);
    }

    public YandexPassportPage sentLogin(User user) {
        waitingForContent();
        webDriverWait.until(ExpectedConditions.presenceOfElementLocated(loginField));
        driver.findElement(loginField).sendKeys(user.getLogin());
        driver.findElement(submitButton).click();
        return this;
    }

    public YandexPassportPage sentPassword(User user) {
        waitingForContent();
        webDriverWait.until(ExpectedConditions.presenceOfElementLocated(passwordField));
        driver.findElement(passwordField).sendKeys(user.getPassword());
        driver.findElement(submitButton).click();
        return this;
    }

    public boolean isErrorMessageWasDisplayed() {
        webDriverWait.until(ExpectedConditions.presenceOfElementLocated(errorMessage));
        return driver.findElement(errorMessage).isDisplayed() && !driver.findElement(errorMessage).getText().isEmpty();
    }
}
