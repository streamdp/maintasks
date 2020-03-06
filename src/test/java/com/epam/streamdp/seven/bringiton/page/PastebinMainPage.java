package com.epam.streamdp.seven.bringiton.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class PastebinMainPage {
    public static final int WAIT_TIMEOUT_SECONDS = 10;
    private static final String HOMEPAGE_URL = "https://pastebin.com/";
    protected WebDriver driver;
    protected WebDriverWait webDriverWait;
    @FindBy(id = "paste_code")
    private WebElement fieldForNewPasteInput;
    @FindBy(xpath = "//span[starts-with(@id, 'select2-paste_format')]")
    private WebElement highlightingSyntaxSelect;
    @FindBy(xpath = "//span[starts-with(@id,'select2-paste_expire_date')]")
    private WebElement expirationTimeSelect;
    @FindBy(xpath = "//li[text()='10 Minutes']")
    private WebElement setExpirationTimeIn10M;
    @FindBy(name = "paste_name")
    private WebElement fieldForTitleInput;
    @FindBy(name = "submit")
    private WebElement submitButton;

    public PastebinMainPage(WebDriver driver) {
        this.driver = driver;
        webDriverWait = new WebDriverWait(driver, WAIT_TIMEOUT_SECONDS);
        PageFactory.initElements(driver, this);
    }

    public PastebinMainPage openPage() {
        driver.get(HOMEPAGE_URL);
        webDriverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("footer")));
        return this;
    }

    public PastebinMainPage fillInputFieldForNewPaste(List<String> content) {
        content.forEach(string -> fieldForNewPasteInput.sendKeys(string + '\n'));
        return this;
    }

    public PastebinMainPage fillInputFieldForTitle(String message) {
        fieldForTitleInput.sendKeys(message);
        return this;
    }

    public PastebinMainPage selectHighlightingSyntax(String syntax) {
        highlightingSyntaxSelect.click();
        driver.findElement(By.xpath(String.format("//li[text()='%s']", syntax))).click();
        return this;
    }

    public PastebinMainPage selectPasteExpirationTime() {
        expirationTimeSelect.click();
        setExpirationTimeIn10M.click();
        return this;
    }

    public PastebinResultPage createNewPaste() {
        submitButton.click();
        return new PastebinResultPage(driver);
    }
}
