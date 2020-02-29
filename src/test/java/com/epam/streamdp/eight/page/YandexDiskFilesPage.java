package com.epam.streamdp.eight.page;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import static com.epam.streamdp.eight.page.YandexDiskInitialPage.WAIT_TIMEOUT_SECONDS;

public class YandexDiskFilesPage extends YandexDiskMainPage {
    public static final String URL_MATCH = "disk";
    public static final String YANDEXDISK_FILES_URL = "https://disk.yandex.ru/client/disk";

    protected WebDriver driver;
    protected WebDriverWait webDriverWait;
    protected Actions builder;
    private String stringTitle = "//h1[@title='%s']";
    private String itemName = "//div[@class='listing-item__info']/div/span[text()='%s']";
    private By contextMenuItemCreateNewFolder = By.xpath("//*[@class='control menu__item context-menu-create-popup__item context-menu-create-popup__item_new-folder']");
    private By contexMenuItemRemove = By.xpath("//div[@class='control menu__item resources-actions-popup__action resources-actions-popup__action_type_delete']");
    private By contextMenuItemCreateNewTextDocument = By.xpath("//*[@class='control menu__item context-menu-create-popup__item context-menu-create-popup__item_word']");
    private By nameFolderInputField = By.xpath("//input[@class='textinput__control' and @value='Новая папка' ]");
    private By saveButton = By.xpath("//button[@class='control button2 button2_view_default button2_tone_default button2_size_n button2_theme_raised button2_action_yes confirmation-dialog__button confirmation-dialog__button_submit ']");

    public YandexDiskFilesPage(WebDriver driver) {
        super(driver);
        if (!driver.getCurrentUrl().contains(URL_MATCH)) {
            throw new IllegalStateException("This is not the page you are expected");
        }
        this.driver = driver;
        this.builder = new Actions(driver);
        this.webDriverWait = new WebDriverWait(driver, WAIT_TIMEOUT_SECONDS);
    }

    public YandexDiskFilesPage waitingForContent() {
        webDriverWait.until((ExpectedCondition<Boolean>) wd ->
                ((JavascriptExecutor) wd).executeScript("return document.readyState").equals("complete"));
        return this;
    }

    public YandexDiskFilesPage createFolder(String folderName) {
        if (!driver.getCurrentUrl().equals(YANDEXDISK_FILES_URL)) {
            driver.get(YANDEXDISK_FILES_URL);
        }
        waitingForContent();
        openContextMenuOnElement(buildLocatorForTitle("Файлы"));
        clickToItem(contextMenuItemCreateNewFolder);
        fillingInputField(nameFolderInputField, folderName);
        clickToItem(saveButton);
        webDriverWait.until(ExpectedConditions.presenceOfElementLocated(buildLocatorForItem(folderName)));
        return this;
    }

    public By buildLocatorForTitle(String title) {
        return By.xpath(String.format(stringTitle, title));
    }

    public By buildLocatorForItem(String name) {
        return By.xpath(String.format(itemName, name));
    }

    public YandexDiskFilesPage removeItem(String itemName) {
        waitingForContent();
        openContextMenuOnElement(buildLocatorForItem(itemName));
        clickToItem(contexMenuItemRemove);
        builder.pause(350).build().perform();
        return this;
    }

    public void fillingInputField(By locator, String string) {
        waitingForContent();
        webDriverWait.until(ExpectedConditions.presenceOfElementLocated(locator));
        builder.moveToElement(driver.findElement(locator))
                .sendKeys(Keys.BACK_SPACE).sendKeys(string).build().perform();
    }

    public void clickToItem(By locator) {
        waitingForContent();
        webDriverWait.until(ExpectedConditions.presenceOfElementLocated(locator));
        driver.findElement(locator).click();
    }

    public YandexDiskTextDocumentPage createTextDocument(String folderName) {
        waitingForContent();
        goToFolder(folderName);
        openContextMenuOnElement(buildLocatorForTitle(folderName));
        clickToItem(contextMenuItemCreateNewTextDocument);
        switchToTab(1);
        return new YandexDiskTextDocumentPage(driver);
    }

    public void openContextMenuOnElement(By locator) {
        waitingForContent();
        webDriverWait.until(ExpectedConditions.presenceOfElementLocated(locator));
        builder.contextClick(driver.findElement(locator)).build().perform();
    }

    public YandexDiskFilesPage goToFolder(String folderName) {
        waitingForContent();
        webDriverWait.until(ExpectedConditions.presenceOfElementLocated(buildLocatorForItem(folderName)));
        builder.doubleClick(driver.findElement(buildLocatorForItem(folderName))).build().perform();
        return this;
    }

    public YandexDiskTextDocumentPage openTextDocument(String fileName) {
        waitingForContent();
        fileName = fileName + ".docx";
        webDriverWait.until(ExpectedConditions.presenceOfElementLocated(buildLocatorForItem(fileName)));
        builder.pause(250).doubleClick(driver.findElement(buildLocatorForItem(fileName))).pause(250).build().perform();
        switchToTab(1);
        return new YandexDiskTextDocumentPage(driver);
    }

    public void switchToTab(int tab) {
        driver.switchTo().window(driver.getWindowHandles().toArray()[tab].toString());
    }

    public boolean isFolderAvailibleForVisit(String folderName) {
        waitingForContent();
        goToFolder(folderName);
        By folderPageTitle = By.xpath(String.format(stringTitle, folderName));
        webDriverWait.until(ExpectedConditions.presenceOfElementLocated(folderPageTitle));
        WebElement folder = driver.findElement(folderPageTitle);
        if (!driver.getCurrentUrl().contains(folderName)) {
            return false;
        }
        return folder.isDisplayed() || folder.getText().isEmpty();
    }
}