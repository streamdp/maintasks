package com.epam.streamdp.ten.yandex.product.disk.screen;

import com.epam.streamdp.ten.framework.logger.Log;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class YandexDiskTrashPage extends YandexDiskMainPage {
    private By emptyTrashButton = By.cssSelector("button.client-listing__clean-trash-button");
    private By confirmationButton = By.cssSelector("button.confirmation-dialog__button_submit");
    private By headingTitle = By.tagName("h1");

    public YandexDiskTrashPage() {
        super();
    }

    public YandexDiskTrashPage clickEmptyTrashButton() {
        waitingPresenceOfElementLocated(headingTitle);
        waitingPresenceOfElementLocated(emptyTrashButton).click();
        webDriverWait.until(ExpectedConditions.elementToBeClickable(confirmationButton)).click();
        webDriverWait.until(ExpectedConditions.invisibilityOfAllElements(driver.findElements(NAME_ITEM)));
        Log.info("Click button:" + emptyTrashButton.toString());
        return this;
    }
}