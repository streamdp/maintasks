package com.epam.streamdp.seven.hardcore.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static com.epam.streamdp.seven.hardcore.page.GoogleCloudPlatformPricingCalculatorPage.CLOUD_FRAME_ONE;
import static com.epam.streamdp.seven.hardcore.page.GoogleCloudPlatformPricingCalculatorPage.CLOUD_FRAME_TWO;

public class GoogleCloudPlatformEmailEstimatePage extends GoogleCloudMainPage {
    private TempMailMainPage tempMail;
    private String cancelButtonLocator = "//*[@ng-click='emailQuote.$mdDialog.cancel()']";
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

    public GoogleCloudPlatformEmailEstimatePage waitingForContent() {
        new WebDriverWait(driver, WAIT_TIMEOUT_SECONDS)
                .until(ExpectedConditions.presenceOfElementLocated(By.xpath(cancelButtonLocator)));
        return this;
    }

    public GoogleCloudPlatformEmailEstimatePage fillUserInformation() {
        firstNameField.sendKeys("Ivan");
        lastNameField.sendKeys("Ivanov");
        emailField.sendKeys(getEmail());
        phoneField.sendKeys("234-56-71");
        jsDriver.executeScript("arguments[0].click();", sendEmailButton);
        return this;
    }

    public String getEmail() {
        jsDriver.executeScript("window.open()");
        driver.switchTo().window(driver.getWindowHandles().toArray()[1].toString());
        tempMail = new TempMailMainPage(driver);
        String tempEmailString = new TempMailMainPage(driver)
                .openPage()
                .getTempEmail();
        driver.switchTo().window(driver.getWindowHandles().toArray()[0].toString());
        new WebDriverWait(driver, WAIT_TIMEOUT_SECONDS)
                .until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.xpath(CLOUD_FRAME_ONE)));
        new WebDriverWait(driver, WAIT_TIMEOUT_SECONDS)
                .until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(CLOUD_FRAME_TWO));
        new WebDriverWait(driver, WAIT_TIMEOUT_SECONDS)
                .until(ExpectedConditions.presenceOfElementLocated(By.xpath(cancelButtonLocator)));
        return tempEmailString;
    }

    public String receiveEmail() {
        driver.switchTo().window(driver.getWindowHandles().toArray()[1].toString());
        new WebDriverWait(driver, WAIT_TIMEOUT_SECONDS)
                .until(ExpectedConditions.presenceOfElementLocated(By.xpath("/html/body/footer/div")));
        return tempMail.getEmailContent();
    }
}
