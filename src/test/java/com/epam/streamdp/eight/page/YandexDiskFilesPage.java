package com.epam.streamdp.eight.page;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.Objects;

public class YandexDiskFilesPage extends YandexDiskMainPage {
    public static final String YANDEXDISK_FILES_URL = "https://disk.yandex.ru/client/disk";
    private By createNewFolderContextMenuItem = By.cssSelector("div.context-menu-create-popup__item_new-folder");
    private By removeContextMenuItem = By.cssSelector("div.resources-actions-popup__action_type_delete");
    private By createNewTextDocumentContextMenuItem = By.cssSelector("div.context-menu-create-popup__item_word");
    private By nameFolderInput = By.cssSelector("span.textinput_size_m > input.textinput__control");
    private By saveButton = By.cssSelector("button.confirmation-dialog__button_submit");
    private By progressBar = By.cssSelector("div.b-progressbar__fill");

    public YandexDiskFilesPage(WebDriver driver) {
        super(driver);
    }

    public YandexDiskFilesPage createFolder(String folderName) {
        if (!driver.getCurrentUrl().equals(YANDEXDISK_FILES_URL)) {
            driver.get(YANDEXDISK_FILES_URL);
        }
        openContextMenuOnElement(By.cssSelector("h1.listing-heading__title"));
        clickToItem(createNewFolderContextMenuItem);
        fillingInputField(nameFolderInput, folderName);
        clickToItem(saveButton);
        webDriverWait.until(ExpectedConditions.presenceOfElementLocated(buildLocatorForItem(folderName)));
        return this;
    }

    public YandexDiskFilesPage removeItem(String itemName) {
        By locator = buildLocatorForItem(itemName);
        openContextMenuOnElement(locator);
        clickToItem(removeContextMenuItem);
        webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(progressBar));
        webDriverWait.until(ExpectedConditions.invisibilityOfElementLocated(progressBar));
        return this;
    }

    public YandexDiskFilesPage fillingInputField(By locator, String string) {
        builder.moveToElement(webDriverWait.until(ExpectedConditions.presenceOfElementLocated(locator)))
                .sendKeys(Keys.BACK_SPACE).sendKeys(string).build().perform();
        return this;
    }

    public YandexDiskFilesPage clickToItem(By locator) {
        webDriverWait.until(ExpectedConditions.presenceOfElementLocated(locator)).click();
        return this;
    }

    public YandexDiskTextDocumentPage createTextDocument(String folderName) {
        goToFolder(folderName);
        openContextMenuOnElement(buildLocatorForTitle(folderName));
        clickToItem(createNewTextDocumentContextMenuItem);
        switchToTab(1);
        webDriverWait.until((ExpectedCondition<Boolean>) wd ->
                (Boolean) ((JavascriptExecutor) Objects.requireNonNull(wd)).executeScript("return jQuery.active == 0"));
        return new YandexDiskTextDocumentPage(driver);
    }

    public YandexDiskFilesPage openContextMenuOnElement(By locator) {
        builder.contextClick(webDriverWait.until(ExpectedConditions.presenceOfElementLocated(locator))).build().perform();
        return this;
    }

    public YandexDiskFilesPage goToFolder(String folderName) {
        builder.doubleClick(webDriverWait.until(ExpectedConditions.
                presenceOfElementLocated(buildLocatorForItem(folderName)))).build().perform();
        return this;
    }

    public YandexDiskTextDocumentPage openTextDocument(String fileName) {
        fileName = fileName + ".docx";
        WebElement fileItem = webDriverWait.until(ExpectedConditions.presenceOfElementLocated(buildLocatorForItem(fileName)));
        builder.pause(250).doubleClick(fileItem).pause(250).build().perform();
        switchToTab(1);
        return new YandexDiskTextDocumentPage(driver);
    }

    public boolean isFolderAvailableForVisit(String folderName) {
        goToFolder(folderName);
        By folderPageTitle = buildLocatorForTitle(folderName);
        WebElement folder = webDriverWait.until(ExpectedConditions.presenceOfElementLocated(folderPageTitle));
        if (!driver.getCurrentUrl().contains(folderName)) {
            return false;
        }
        return folder.isDisplayed() || folder.getText().isEmpty();
    }
}