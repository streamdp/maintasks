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

public class TempMailMain {
    public static final int WAIT_TIMEOUT_SECONDS = 30;
    private static final String HOMEPAGE_URL = "https://tempail.com/";
    protected WebDriver driver;
    @FindBy(xpath = "//*[starts-with(@id,'mail_')]")
    private WebElement newMessage;
    @FindBy(xpath = "//*[@id='mobilepadding']/td/h2")
    private WebElement estimateMonthlyCost;
    @FindBy(xpath = "//*[@id='eposta_adres']")
    private WebElement fieldWithEmail;

    public TempMailMain(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public TempMailMain openPage() {
        driver.get(HOMEPAGE_URL);
        new WebDriverWait(driver, WAIT_TIMEOUT_SECONDS).until((ExpectedCondition<Boolean>) wd ->
                ((JavascriptExecutor) wd).executeScript("return document.readyState").equals("complete"));
        return this;
    }

    public String getTempEmail() {
        return fieldWithEmail.getAttribute("value");
    }

    public String getEmailContent() {
        new WebDriverWait(driver, 60)
                .until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[starts-with(@id,'mail_')]")));
        newMessage.click();
        new WebDriverWait(driver, WAIT_TIMEOUT_SECONDS)
                .until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.xpath("//*[@id='iframe']")));
        return estimateMonthlyCost.getText();
    }
}
