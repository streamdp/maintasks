package com.epam.streamdp.ten.yandex.product.disk.screen;

import com.epam.streamdp.ten.framework.screen.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.regex.Pattern;

public class YandexDiskTextDocumentPage extends BasePage {
    private By frameLocator = By.cssSelector("iframe.editor-doc__iframe");
    private By textFieldLocator = By.cssSelector("div.EditingSurfaceBody");
    private By fileMenuItem = By.cssSelector("button.ms-Button.root-54");
    private By backMenuItem = By.id("jbtnBackArrow-Menu32");
    private By renameMenuItem = By.id("jbtnRenameDialog-Menu48");
    private By txtDocumentNameInput = By.id("txtDocumentName");
    private By okButton = By.id("WACDialogActionButton");
    private By breadcrumbTitle = By.id("BreadcrumbTitle");
    private By breadcrumbSaveStatus = By.id("BreadcrumbSaveStatus");

    public YandexDiskTextDocumentPage(WebDriver driver) {
        super(driver);
    }

    public YandexDiskTextDocumentPage inputSomeText(String string) {
        webDriverWait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frameLocator));
        waitingPresenceOfElementLocated(textFieldLocator).sendKeys(string);
        webDriverWait.until(ExpectedConditions.textMatches(breadcrumbSaveStatus, Pattern.compile("/*Yandex")));
        return this;
    }

    public String getContent() {
        String result = waitingPresenceOfElementLocated(textFieldLocator).getText().trim();
        closeDocument();
        return result;
    }

    public YandexDiskTextDocumentPage renameDocument(String documentName) {
        waitingPresenceOfElementLocated(fileMenuItem).click();
        waitingPresenceOfElementLocated(backMenuItem).click();
        waitingPresenceOfElementLocated(fileMenuItem).click();
        webDriverWait.until(ExpectedConditions.elementToBeClickable(renameMenuItem)).click();
        builder.moveToElement(waitingPresenceOfElementLocated(txtDocumentNameInput)).sendKeys(documentName)
                .moveToElement(driver.findElement(okButton)).build().perform();
        jsClick(okButton);
        webDriverWait.until(ExpectedConditions.textToBe(breadcrumbTitle, documentName));
        webDriverWait.until(ExpectedConditions.textMatches(breadcrumbSaveStatus, Pattern.compile("/*Yandex")));
        return this;
    }

    public YandexDiskTextDocumentPage jsClick(By locator) {
        builder.moveToElement(driver.findElement(locator)).build().perform();
        jsDriver.executeScript("arguments[0].click();", driver.findElement(locator));
        return this;
    }

    public boolean isDocumentOpened(String fileName) {
        webDriverWait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(frameLocator));
        return waitingPresenceOfElementLocated(breadcrumbTitle).getText().equals(fileName);
    }

    public YandexDiskFilesPage closeDocument() {
        driver.close();
        switchToTab(0);
        return new YandexDiskFilesPage(driver);
    }
}