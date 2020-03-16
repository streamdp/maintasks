package com.epam.streamdp.eight.page;

import com.epam.streamdp.eight.model.User;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class YandexPassportPage extends BasePage {
    private By loginField = By.id("passp-field-login");
    private By passwordField = By.id("passp-field-passwd");
    private By submitButton = By.xpath("//button[@type='submit']");
    private By errorMessage = By.cssSelector("div.passp-form-field__error");

    public YandexPassportPage(WebDriver driver) {
        super(driver);
    }

    public YandexDiskMainPage sentCredentials(User user) {
        sentLogin(user.getLogin());
        sentPassword(user.getPassword());
        return new YandexDiskMainPage(driver);
    }

    public YandexPassportPage sentLogin(String login) {
        webDriverWait.until(ExpectedConditions.presenceOfElementLocated(loginField)).sendKeys(login);
        driver.findElement(submitButton).click();
        return this;
    }

    public YandexPassportPage sentPassword(String password) {
        webDriverWait.until(ExpectedConditions.presenceOfElementLocated(passwordField)).sendKeys(password);
        driver.findElement(submitButton).click();
        return this;
    }

    public boolean isErrorMessageWasDisplayed() {
        WebElement errorMessage = webDriverWait.until(ExpectedConditions.presenceOfElementLocated(this.errorMessage));
        return errorMessage.isDisplayed() && !errorMessage.getText().isEmpty();
    }
}