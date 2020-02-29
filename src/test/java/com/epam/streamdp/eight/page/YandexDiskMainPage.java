package com.epam.streamdp.eight.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.stream.Collectors;

import static com.epam.streamdp.eight.page.YandexDiskInitialPage.WAIT_TIMEOUT_SECONDS;

public class YandexDiskMainPage {
    private static final String URL_MATCH = "disk.yandex";
    protected WebDriver driver;
    protected WebDriverWait webDriverWait;
    protected Actions builder;
    private By headingTitle = By.xpath("//*/h1");
    private By diskLink = By.xpath("//*[@id='/disk']");
    private By trashLink = By.xpath("//*[@id='/trash']");
    private By allItemsName = By.xpath("//div[@class='listing-item__info']/div/span");

    public YandexDiskMainPage(WebDriver driver) {
        if (!driver.getCurrentUrl().contains(URL_MATCH)) {
            throw new IllegalStateException("This is not the page you are expected");
        }
        this.driver = driver;
        webDriverWait = new WebDriverWait(driver, WAIT_TIMEOUT_SECONDS);
        builder = new Actions(driver);
    }

    public YandexDiskFilesPage goToFilesMenuItem() {
        webDriverWait.until(ExpectedConditions.presenceOfElementLocated(diskLink));
        driver.findElement(diskLink).click();
        webDriverWait.until(ExpectedConditions.presenceOfElementLocated(headingTitle));
        return new YandexDiskFilesPage(driver);
    }

    public YandexDiskTrashPage goToTrash() {
        webDriverWait.until(ExpectedConditions.presenceOfElementLocated(trashLink));
        driver.findElement(trashLink).click();
        webDriverWait.until(ExpectedConditions.presenceOfElementLocated(headingTitle));
        return new YandexDiskTrashPage(driver);
    }

    public void refreshPage() {
        driver.get(driver.getCurrentUrl());
        webDriverWait.until(ExpectedConditions.presenceOfElementLocated(headingTitle));
    }

    public boolean isMenuItemLinkCorrect(By locator) {
        webDriverWait.until(ExpectedConditions.presenceOfElementLocated(locator));
        WebElement element = driver.findElement(locator);
        if (!element.isDisplayed() && !element.getText().isEmpty()) {
            return false;
        }
        element.click();
        webDriverWait.until(ExpectedConditions.presenceOfElementLocated(headingTitle));
        builder.pause(50).build().perform();
        WebElement headingTitle = driver.findElement(this.headingTitle);
        return headingTitle.isDisplayed() || headingTitle.getText().isEmpty();
    }

    public boolean isItemPresent(String itemName) {
        webDriverWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(allItemsName));
        return getListItems().stream().anyMatch(item -> item.equals(itemName));
    }

    public List<String> getListItems() {
        return driver.findElements(allItemsName).stream().map(WebElement::getText).collect(Collectors.toList());
    }
}