package com.epam.streamdp.nine.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class TempMailMainPage extends CommonPage {
    private static final String HOMEPAGE_URL = "https://tempail.com/";
    protected WebDriver driver;
    protected WebDriverWait webDriverWait;
    private By newMessage = By.xpath("//*[starts-with(@id,'mail_')]");
    private By mailFromGoogleFrame = By.id("iframe");
    @FindBy(xpath = "//*[@id='mobilepadding']/td/h2")
    private WebElement estimateMonthlyCost;
    @FindBy(id = "eposta_adres")
    private WebElement fieldWithEmail;

    public TempMailMainPage(WebDriver driver) {
        this.driver = driver;
        webDriverWait = new WebDriverWait(driver, WAIT_TIMEOUT_SECONDS);
        PageFactory.initElements(driver, this);
    }

    public TempMailMainPage openPage() {
        driver.get(HOMEPAGE_URL);
        waitReadyStateIsComplete();
        webDriverWait.until((ExpectedConditions.visibilityOfAllElements(fieldWithEmail)));
        return this;
    }

    public String getTempEmail() {
        return fieldWithEmail.getAttribute("value");
    }

    public String getEmailContent() {
        webDriverWait.until(ExpectedConditions.presenceOfElementLocated(newMessage)).click();
        webDriverWait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(mailFromGoogleFrame));
        return estimateMonthlyCost.getText();
    }
}