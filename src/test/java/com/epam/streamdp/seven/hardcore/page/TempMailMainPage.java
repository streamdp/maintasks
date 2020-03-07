package com.epam.streamdp.seven.hardcore.page;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Objects;

public class TempMailMainPage {
    public static final int WAIT_TIMEOUT_SECONDS = 30;
    private static final String HOMEPAGE_URL = "https://tempail.com/";
    protected WebDriver driver;
    protected WebDriverWait webDriverWait;
    @FindBy(xpath = "//*[starts-with(@id,'mail_')]")
    private WebElement newMessage;
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
        webDriverWait.until((ExpectedCondition<Boolean>) wd ->
                ((JavascriptExecutor) Objects.requireNonNull(wd)).executeScript("return document.readyState").equals("complete"));
        webDriverWait.until((ExpectedConditions.visibilityOfAllElements(fieldWithEmail)));
        return this;
    }

    public String getTempEmail() {
        return fieldWithEmail.getAttribute("value");
    }

    public String getEmailContent() {
        webDriverWait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[starts-with(@id,'mail_')]")));
        newMessage.click();
        webDriverWait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.id("iframe")));
        return estimateMonthlyCost.getText();
    }
}