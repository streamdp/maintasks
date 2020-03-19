package com.epam.streamdp.eight.page;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasePage {
    public static final int WAIT_TIMEOUT_SECONDS = 30;
    protected WebDriver driver;
    protected WebDriverWait webDriverWait;
    protected Actions builder;
    protected JavascriptExecutor jsDriver;


    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.builder = new Actions(driver);
        this.webDriverWait = new WebDriverWait(driver, WAIT_TIMEOUT_SECONDS);
        jsDriver = (JavascriptExecutor) driver;
    }

    public void switchToTab(int tab) {
        driver.switchTo().window(driver.getWindowHandles().toArray()[tab].toString());
    }

    public By buildLocatorForTitle(String title) {
        return By.xpath(String.format("//h1[@title='%s']", title));
    }

    public By buildLocatorForItem(String name) {
        return By.xpath(String.format("//div[@class='listing-item__info']/div/span[text()='%s']", name));
    }
}
