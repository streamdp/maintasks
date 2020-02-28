package com.epam.streamdp.seven.icanwin.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PastebinMainPage {
    public static final int WAIT_TIMEOUT_SECONDS = 10;
    private static final String HOMEPAGE_URL = "https://pastebin.com/";
    protected WebDriver driver;

    @FindBy(xpath = "//*[@id='paste_code']")
    private WebElement fieldForNewPasteInput;
    @FindBy(xpath = "//span[starts-with(@id,'select2-paste_expire_date')]")
    private WebElement pasteExpirationSelect;
    @FindBy(xpath = "//li[text()='10 Minutes']")
    private WebElement setExpirationTimeIn10M;
    @FindBy(xpath = "//*[@name='paste_name']")
    private WebElement fieldForTitleInput;
    @FindBy(xpath = "//*[@name='submit']")
    private WebElement submitButton;

    public PastebinMainPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public PastebinMainPage openPage() {
        driver.get(HOMEPAGE_URL);
        new WebDriverWait(driver, WAIT_TIMEOUT_SECONDS)
                .until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='footer']")));
        return this;
    }

    public PastebinMainPage fillInputFieldForNewPaste(String message) {
        fieldForNewPasteInput.sendKeys(message);
        return this;
    }

    public PastebinMainPage fillInputFieldForTitle(String message) {
        fieldForTitleInput.sendKeys(message);
        return this;
    }

    public PastebinMainPage selectPasteExpirationTime() {
        pasteExpirationSelect.click();
        setExpirationTimeIn10M.click();
        return this;
    }

    public PastebinResultPage createNewPaste() {
        submitButton.click();
        return new PastebinResultPage(driver);
    }
}
