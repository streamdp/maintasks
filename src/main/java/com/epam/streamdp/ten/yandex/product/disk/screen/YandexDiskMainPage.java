package com.epam.streamdp.ten.yandex.product.disk.screen;

import com.epam.streamdp.ten.framework.screen.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;
import java.util.stream.Collectors;

public class YandexDiskMainPage extends BasePage {
    public By nameItem = By.cssSelector("div.listing-item__title > span.clamped-text");
    private By headingTitle = By.tagName("h1");
    private By diskLink = By.id("/disk");
    private By trashLink = By.id("/trash");

    public YandexDiskMainPage(WebDriver driver) {
        super(driver);
    }

    public YandexDiskFilesPage goToFilesMenuItem() {
        waitingPresenceOfElementLocated(diskLink).click();
        waitingPresenceOfElementLocated(headingTitle);
        return new YandexDiskFilesPage(driver);
    }

    public YandexDiskTrashPage goToTrash() {
        waitingPresenceOfElementLocated(trashLink).click();
        webDriverWait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(nameItem));
        return new YandexDiskTrashPage(driver);
    }

    public boolean isMenuItemLinkCorrect(By locator) {
        WebElement element = waitingPresenceOfElementLocated(locator);
        if (!element.isDisplayed() && !element.getText().isEmpty()) {
            return false;
        }
        element.click();
        waitingPresenceOfElementLocated(headingTitle);
        builder.pause(50).build().perform();
        WebElement title = driver.findElement(this.headingTitle);
        return title.isDisplayed() || title.getText().isEmpty();
    }

    public boolean isItemPresent(String name) {
        webDriverWait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(nameItem));
        return getListItems().stream().anyMatch(item -> item.equals(name));
    }

    public List<String> getListItems() {
        return driver.findElements(nameItem).stream().map(WebElement::getText).collect(Collectors.toList());
    }
}