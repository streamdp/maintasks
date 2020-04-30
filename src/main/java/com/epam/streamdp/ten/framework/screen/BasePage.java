package com.epam.streamdp.ten.framework.screen;

import com.epam.streamdp.ten.framework.driver.DriverSingleton;
import com.epam.streamdp.ten.framework.listener.TestListener;
import com.epam.streamdp.ten.framework.logger.Log;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;


public class BasePage {
    public static final String TITLE_LOCATOR_TEMPLATE = "//h1[@title='%s']";
    public static final String ITEM_LOCATOR_TEMPLATE = "//div[@class='listing-item__info']/div/span[text()='%s']";
    public static final int WAIT_TIMEOUT_SECONDS = 30;
    protected WebDriver driver;
    protected WebDriverWait webDriverWait;
    protected Actions builder;
    protected JavascriptExecutor jsDriver;

    public BasePage() {
        this.driver = DriverSingleton.getDriver();
        this.builder = new Actions(driver);
        this.webDriverWait = new WebDriverWait(driver, WAIT_TIMEOUT_SECONDS);
        jsDriver = (JavascriptExecutor) driver;
    }

    public void switchToTab(int tab) {
        driver.switchTo().window(driver.getWindowHandles().toArray()[tab].toString());
    }

    public void highlightElement(By locator) {
        WebElement element = waitingPresenceOfElementLocated(locator);
        String backgroundColor = element.getCssValue("backgroundColor");
        String border = element.getCssValue("border");
        jsDriver.executeScript("arguments[0].style.backgroundColor= '" + "red" + "'", element);
        jsDriver.executeScript("arguments[0].style.border = '2px solid green'", element);
        TestListener.saveScreenshot();
        jsDriver.executeScript("arguments[0].style.backgroundColor= '" + backgroundColor + "'", element);
        jsDriver.executeScript("arguments[0].style.border = '" + border + "'", element);

    }

    public BasePage clickToItem(By locator) {
        highlightElement(locator);
        waitingPresenceOfElementLocated(locator).click();
        Log.info("Click to item: " + locator.toString());
        return this;
    }

    public BasePage openContextMenuOnElement(By locator) {
        highlightElement(locator);
        builder.contextClick(waitingPresenceOfElementLocated(locator)).build().perform();
        Log.info("Open context menu on element: " + locator.toString());
        return this;
    }

    public BasePage jsClick(By locator) {
        highlightElement(locator);
        builder.moveToElement(driver.findElement(locator)).build().perform();
        jsDriver.executeScript("arguments[0].click();", driver.findElement(locator));
        Log.info("Click to item: " + locator.toString());
        return this;
    }

    public By buildLocatorFor(String locatorTemplate, String name) {
        return By.xpath(String.format(locatorTemplate, name));
    }

    public WebElement waitingPresenceOfElementLocated(By locator) {
        Log.info("Waiting presence of element located by: " + locator.toString());
        return webDriverWait.until(ExpectedConditions.presenceOfElementLocated(locator));
    }
}