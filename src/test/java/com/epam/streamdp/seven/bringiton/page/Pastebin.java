package com.epam.streamdp.seven.bringiton.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class Pastebin {
    public static final int WAIT_TIMEOUT_SECONDS = 10;
    private static final String HOMEPAGE_URL = "https://pastebin.com/";
    protected WebDriver driver;

    private String highlightingSyntaxXpath = "//li[text()='%s']";

    @FindBy(xpath = "//*[@id='paste_code']")
    private WebElement inputFieldForNewPaste;
    @FindBy(xpath = "//span[starts-with(@id, 'select2-paste_format')]")
    private WebElement highlightingSyntaxSelect;
    @FindBy(xpath = "//span[starts-with(@id,'select2-paste_expire_date')]")
    private WebElement expirationTimeSelect;
    @FindBy(xpath = "//li[text()='10 Minutes']")
    private WebElement setExpirationTimeIn10M;
    @FindBy(xpath = "//*[@name='paste_name']")
    private WebElement inputFieldForTitle;
    @FindBy(xpath = "//*[@name='submit']")
    private WebElement submitButton;

    public Pastebin(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public Pastebin openPage() {
        driver.get(HOMEPAGE_URL);
        new WebDriverWait(driver, WAIT_TIMEOUT_SECONDS)
                .until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='footer']")));
        return this;
    }

    public Pastebin fillInputFieldForNewPaste(List<String> content) {
        content.forEach(string -> inputFieldForNewPaste.sendKeys(string + '\n'));
        return this;
    }

    public Pastebin fillInputFieldForTitle(String message) {
        inputFieldForTitle.sendKeys(message);
        return this;
    }

    public Pastebin selectHighlightingSyntax(String syntax) {
        highlightingSyntaxSelect.click();
        driver.findElement(By.xpath(String.format(highlightingSyntaxXpath, syntax))).click();
        return this;
    }

    public Pastebin selectPasteExpirationTime() {
        expirationTimeSelect.click();
        setExpirationTimeIn10M.click();
        return this;
    }

    public PastebinResult createNewPaste() {
        submitButton.click();
        return new PastebinResult(driver);
    }
}
