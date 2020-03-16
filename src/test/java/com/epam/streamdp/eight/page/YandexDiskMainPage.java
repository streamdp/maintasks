package com.epam.streamdp.eight.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;
import java.util.stream.Collectors;

public class YandexDiskMainPage extends BasePage {
    private By headingTitle = By.tagName("h1");
    private By diskLink = By.id("/disk");
    private By trashLink = By.id("/trash");
    public By nameItem = By.cssSelector("div.listing-item__title > span.clamped-text");

    public YandexDiskMainPage(WebDriver driver) {
        super(driver);
    }

    public YandexDiskFilesPage goToFilesMenuItem() {
        webDriverWait.until(ExpectedConditions.presenceOfElementLocated(diskLink)).click();
        webDriverWait.until(ExpectedConditions.presenceOfElementLocated(headingTitle));
        return new YandexDiskFilesPage(driver);
    }

    public YandexDiskTrashPage goToTrash() {
        webDriverWait.until(ExpectedConditions.presenceOfElementLocated(trashLink)).click();
        webDriverWait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(nameItem));
        return new YandexDiskTrashPage(driver);
    }

    public boolean isMenuItemLinkCorrect(By locator) {
        WebElement element = webDriverWait.until(ExpectedConditions.presenceOfElementLocated(locator));
        if (!element.isDisplayed() && !element.getText().isEmpty()) {
            return false;
        }
        element.click();
        webDriverWait.until(ExpectedConditions.presenceOfElementLocated(headingTitle));
        builder.pause(50).build().perform();
        WebElement headingTitle = driver.findElement(this.headingTitle);
        return headingTitle.isDisplayed() || headingTitle.getText().isEmpty();
    }

    public boolean isItemPresent(String name) {
        webDriverWait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(nameItem));
        return getListItems().stream().anyMatch(item -> item.equals(name));
    }

    public List<String> getListItems() {
        return driver.findElements(nameItem).stream().map(WebElement::getText).collect(Collectors.toList());
    }
}