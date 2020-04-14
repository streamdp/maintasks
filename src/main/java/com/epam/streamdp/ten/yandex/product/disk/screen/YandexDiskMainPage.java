package com.epam.streamdp.ten.yandex.product.disk.screen;

import com.epam.streamdp.ten.framework.screen.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.List;
import java.util.stream.Collectors;

public class YandexDiskMainPage extends BasePage {
    public static final By NAME_ITEM = By.cssSelector("div.listing-item__title > span.clamped-text");
    private By headingTitle = By.tagName("h1");
    public static final By RECENT_LINK = By.cssSelector("div.navigation__items_standard > div:nth-child(1) > a");
    public static final By DISK_LINK = By.id("/disk");
    public static final By PHOTO_LINK = By.cssSelector("div.navigation__items_standard > div:nth-child(3) > a");
    public static final By ALBUM_LINK = By.cssSelector("div.navigation__items_standard > div:nth-child(4) > a");
    public static final By SHARED_LINK = By.cssSelector("div.navigation__items_standard > div:nth-child(5) > a");
    public static final By JOURNAL_LINK = By.cssSelector("div.navigation__items_standard > div:nth-child(6) > a");
    public static final By ATTACH_LINK = By.cssSelector("div.navigation__items_standard > div:nth-child(7) > a");
    public static final By TRASH_LINK = By.id("/trash");

    public YandexDiskMainPage() {
        super();
    }

    public YandexDiskFilesPage goToFilesMenuItem() {
        waitingPresenceOfElementLocated(DISK_LINK).click();
        waitingPresenceOfElementLocated(headingTitle);
        return new YandexDiskFilesPage();
    }

    public YandexDiskTrashPage goToTrash() {
        waitingPresenceOfElementLocated(TRASH_LINK).click();
        webDriverWait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(NAME_ITEM));
        return new YandexDiskTrashPage();
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
        webDriverWait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(NAME_ITEM));
        return getListItems().stream().anyMatch(item -> item.equals(name));
    }

    public List<String> getListItems() {
        return driver.findElements(NAME_ITEM).stream().map(WebElement::getText).collect(Collectors.toList());
    }
}