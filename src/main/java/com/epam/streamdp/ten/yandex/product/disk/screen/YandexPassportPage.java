package com.epam.streamdp.ten.yandex.product.disk.screen;

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
        waitingPresenceOfElementLocated(loginField).sendKeys(login);
        driver.findElement(submitButton).click();
        return this;
    }

    public YandexPassportPage sendPassword(String password) {
        waitingPresenceOfElementLocated(passwordField).sendKeys(password);
        driver.findElement(submitButton).click();
        return this;
    }

    public boolean isErrorMessageWasDisplayed() {
        WebElement message = waitingPresenceOfElementLocated(this.errorMessage);
        return message.isDisplayed() && !message.getText().isEmpty();
    }
}