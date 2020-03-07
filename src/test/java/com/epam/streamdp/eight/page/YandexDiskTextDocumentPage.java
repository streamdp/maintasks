package com.epam.streamdp.eight.page;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Objects;

import static com.epam.streamdp.eight.page.YandexDiskFilesPage.URL_MATCH;
import static com.epam.streamdp.eight.page.YandexDiskInitialPage.WAIT_TIMEOUT_SECONDS;

public class YandexDiskTextDocumentPage {
    protected WebDriver driver;
    protected WebDriverWait webDriverWait;
    protected Actions builder;
    protected JavascriptExecutor jsDriver;

    private By frameLocator = By.xpath("//*[@id='nb-1']/body/iframe");
    private By textFieldLocator = By.id("WACViewPanel_EditingElement");
    private By fileMenuItem = By.xpath("//*[@data-automation-id='FileMenu']");
    private By renameMenuItem = By.id("jbtnRenameDialog-Menu48");
    private By txtDocumentNameField = By.id("txtDocumentName");
    private By okButton = By.id("WACDialogActionButton");
    private By breadcrumbTitle = By.id("BreadcrumbTitle");

    public YandexDiskTextDocumentPage(WebDriver driver) {
        if (!driver.getCurrentUrl().contains(URL_MATCH)) {
            throw new IllegalStateException("This is not the page you are expected");
        }
        this.driver = driver;
        jsDriver = (JavascriptExecutor) driver;
        builder = new Actions(driver);
        webDriverWait = new WebDriverWait(driver, WAIT_TIMEOUT_SECONDS);
    }

    public void waitingForContent() {
        webDriverWait.until((ExpectedCondition<Boolean>) wd ->
                ((JavascriptExecutor) Objects.requireNonNull(wd)).executeScript("return document.readyState").equals("complete"));
        webDriverWait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frameLocator));
    }

    public YandexDiskTextDocumentPage inputSomeText(String string) {
        waitingForContent();
        waitForElementPresent(textFieldLocator);
        builder.pause(1500).moveToElement(driver.findElement(textFieldLocator)).pause(1500)
                .sendKeys(string).pause(3000).build().perform();
        return this;
    }

    public String getContent() {
        waitForElementPresent(textFieldLocator);
        String result = driver.findElement(textFieldLocator).getText().trim();
        closeDocument();
        return result;
    }

    public YandexDiskTextDocumentPage renameDocument(String documentName) {
        waitForElementPresent(fileMenuItem);
        jsClick(fileMenuItem);
        webDriverWait.until(ExpectedConditions.elementToBeClickable(renameMenuItem));
        driver.findElement(renameMenuItem).click();
        waitForElementPresent(txtDocumentNameField);
        builder.moveToElement(driver.findElement(txtDocumentNameField)).sendKeys(documentName).pause(500)
                .moveToElement(driver.findElement(okButton)).build().perform();
        jsClick(okButton);
        builder.pause(2000).build().perform();
        return this;
    }

    public void jsClick(By locator) {
        builder.moveToElement(driver.findElement(locator)).build().perform();
        jsDriver.executeScript("arguments[0].click();", driver.findElement(locator));
    }

    public void waitForElementPresent(By locator) {
        webDriverWait.until(ExpectedConditions.presenceOfElementLocated(locator));
    }

    public boolean isDocumentOpened(String fileName) {
        waitingForContent();
        webDriverWait.until(ExpectedConditions.presenceOfElementLocated(breadcrumbTitle));
        return driver.findElement(breadcrumbTitle).getText().equals(fileName);
    }

    public YandexDiskFilesPage closeDocument() {
        driver.close();
        switchToTab(0);
        return new YandexDiskFilesPage(driver);
    }

    public void switchToTab(int tab) {
        driver.switchTo().window(driver.getWindowHandles().toArray()[tab].toString());
    }
}
