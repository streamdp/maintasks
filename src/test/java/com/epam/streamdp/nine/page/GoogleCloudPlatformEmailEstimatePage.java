package com.epam.streamdp.nine.page;

import com.epam.streamdp.nine.model.User;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class GoogleCloudPlatformEmailEstimatePage extends GoogleCloudMainPage {
    private TempMailMainPage tempMail;
    private By footerDivLocator = By.xpath("/html/body/footer/div");
    private By cancelButtonLocator = By.xpath("//*[@ng-click='emailQuote.$mdDialog.cancel()']");
    @FindBy(xpath = "//*[@ng-model='emailQuote.user.firstname']")
    private WebElement firstNameField;
    @FindBy(xpath = "//*[@ng-model='emailQuote.user.lastname']")
    private WebElement lastNameField;
    @FindBy(xpath = "//*[@ng-model='emailQuote.user.email']")
    private WebElement emailField;
    @FindBy(xpath = "//*[@ng-model='emailQuote.user.phone']")
    private WebElement phoneField;
    @FindBy(xpath = "//button[@aria-label='Send Email']")
    private WebElement sendEmailButton;

    public GoogleCloudPlatformEmailEstimatePage(WebDriver driver) {
        super(driver);
    }

    public GoogleCloudPlatformEmailEstimatePage fillUserInformation(User user) {
        waitingForContent(cancelButtonLocator);
        firstNameField.sendKeys(user.getFirstName());
        lastNameField.sendKeys(user.getLastName());
        emailField.sendKeys(getEmail());
        phoneField.sendKeys(user.getPhone());
        jsDriver.executeScript("arguments[0].click();", sendEmailButton);
        return this;
    }

    public String getEmail() {
        jsDriver.executeScript("window.open()");
        switchToTab(1);
        tempMail = new TempMailMainPage(driver);
        String tempEmailString = new TempMailMainPage(driver)
                .openPage()
                .getTempEmail();
        switchToTab(0);
        webDriverWait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.xpath(CLOUD_FRAME_ONE)));
        webDriverWait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(CLOUD_FRAME_TWO));
        waitingForContent(cancelButtonLocator);
        return tempEmailString;
    }

    public String receiveEmail() {
        switchToTab(1);
        waitingForContent(footerDivLocator);
        return tempMail.getEmailContent();
    }
}
