package com.epam.streamdp.ten.framework.screen;

import com.epam.streamdp.ten.framework.driver.DriverSingleton;
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

    public BasePage clickToItem(By locator) {
        waitingPresenceOfElementLocated(locator).click();
        return this;
    }

    public BasePage openContextMenuOnElement(By locator) {
        builder.contextClick(waitingPresenceOfElementLocated(locator)).build().perform();
        return this;
    }

    public BasePage jsClick(By locator) {
        builder.moveToElement(driver.findElement(locator)).build().perform();
        jsDriver.executeScript("arguments[0].click();", driver.findElement(locator));
        return this;
    }

    public By buildLocatorFor(String locatorTemplate, String name) {
        return By.xpath(String.format(locatorTemplate, name));
    }

    public WebElement waitingPresenceOfElementLocated(By locator) {
        return webDriverWait.until(ExpectedConditions.presenceOfElementLocated(locator));
    }
}
