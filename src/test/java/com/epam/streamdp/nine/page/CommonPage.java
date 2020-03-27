package com.epam.streamdp.nine.page;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Objects;

public abstract class CommonPage {
    public static final String CLOUD_FRAME_ONE = "//*[@id='cloud-site']/devsite-iframe/iframe";
    public static final String CLOUD_FRAME_TWO = "myFrame";
    public static final int WAIT_TIMEOUT_SECONDS = 30;
    protected WebDriver driver;
    protected WebDriverWait webDriverWait;
    protected JavascriptExecutor jsDriver;

    public void waitingForContent(By locatorOfElement) {
        webDriverWait.until(ExpectedConditions.presenceOfElementLocated(locatorOfElement));
    }

    public void waitingForContent() {
        webDriverWait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(By.xpath(CLOUD_FRAME_ONE)));
        webDriverWait.until(ExpectedConditions.frameToBeAvailableAndSwitchToIt(CLOUD_FRAME_TWO));
    }

    public void waitReadyStateIsComplete() {
        webDriverWait.until((ExpectedCondition<Boolean>) wd ->
                ((JavascriptExecutor) Objects.requireNonNull(wd)).executeScript("return document.readyState").equals("complete"));
    }

    public void switchToTab(int tab) {
        driver.switchTo().window(driver.getWindowHandles().toArray()[tab].toString());
    }
}
