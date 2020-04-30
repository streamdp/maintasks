package com.epam.streamdp.ten.yandex.product.disk.screen;

import com.epam.streamdp.ten.framework.listener.TestListener;
import com.epam.streamdp.ten.framework.logger.Log;
import com.epam.streamdp.ten.framework.model.User;
import com.epam.streamdp.ten.framework.screen.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class YandexPassportPage extends BasePage {
    private By loginField = By.id("passp-field-login");
    private By passwordField = By.id("passp-field-passwd");
    private By submitButton = By.xpath("//button[@type='submit']");
    private By errorMessage = By.cssSelector("div.passp-form-field__error");

    public YandexPassportPage() {
        super();
    }

    public YandexDiskMainPage sendCredentials(User user) {
        sendLogin(user.getLogin());
        sendPassword(user.getPassword());
        return new YandexDiskMainPage();
    }

    public YandexPassportPage sendLogin(String login) {
        highlightElement(loginField);
        waitingPresenceOfElementLocated(loginField).sendKeys(login);
        driver.findElement(submitButton).click();
        Log.info("Send login: " + login);
        return this;
    }

    public YandexPassportPage sendPassword(String password) {
        highlightElement(passwordField);
        waitingPresenceOfElementLocated(passwordField).sendKeys(password);
        driver.findElement(submitButton).click();
        Log.info("Send password: " + password);
        return this;
    }

    public boolean isErrorMessageWasDisplayed() {
        WebElement message = waitingPresenceOfElementLocated(this.errorMessage);
        TestListener.saveScreenshot();
        return message.isDisplayed() && !message.getText().isEmpty();
    }
}