package com.epam.streamdp.ten.yandex.product.disk.screen;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class YandexDiskTrashPage extends YandexDiskMainPage {
    private By emptyTrashButton = By.cssSelector("button.client-listing__clean-trash-button");
    private By confirmationButton = By.xpath("//*[@id='nb-1']/body/div[4]/div/div/div/div/div/div[2]/button[2]");
    private By headingTitle = By.tagName("h1");

    public YandexDiskTrashPage(WebDriver driver) {
        super(driver);
    }

    public YandexDiskTrashPage clickEmptyTrashButton() {
        waitingPresenceOfElementLocated(headingTitle);
        waitingPresenceOfElementLocated(emptyTrashButton).click();
        webDriverWait.until(ExpectedConditions.elementToBeClickable(confirmationButton)).click();
        webDriverWait.until(ExpectedConditions.invisibilityOfAllElements(driver.findElements(nameItem)));
        return this;
    }
}