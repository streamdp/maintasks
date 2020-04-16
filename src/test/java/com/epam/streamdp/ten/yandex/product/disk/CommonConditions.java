package com.epam.streamdp.ten.yandex.product.disk;

import com.epam.streamdp.ten.framework.driver.DriverSingleton;
import com.epam.streamdp.ten.framework.listener.TestListener;
import com.epam.streamdp.ten.framework.model.Document;
import com.epam.streamdp.ten.framework.model.Folder;
import com.epam.streamdp.ten.framework.model.User;
import com.epam.streamdp.ten.framework.service.*;
import com.epam.streamdp.ten.yandex.product.disk.screen.*;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Listeners;

@Listeners({TestListener.class})
public class CommonConditions {
    protected WebDriver driver;
    protected User correctCredentials;
    protected ActionService yandexActionService = new ActionService();
    protected AccountService yandexAccountService = new AccountService();
    protected YandexDiskMainPage yandexDiskMainPage = new YandexDiskMainPage();
    protected YandexDiskFilesPage yandexDiskFilesPage = new YandexDiskFilesPage();
    protected YandexDiskTextDocumentPage yandexDiskTextDocumentPage = new YandexDiskTextDocumentPage();
    protected YandexDiskInitialPage yandexDiskInitialPage = new YandexDiskInitialPage();
    protected YandexDiskTrashPage yandexDiskTrashPage = new YandexDiskTrashPage();
    protected Folder folder;
    protected Document document;

    @BeforeClass()
    public void setUp() {
        driver = DriverSingleton.getDriver();
        correctCredentials = UserFactory.withCredentialsFromProperty();
        folder = FolderFactory.withRandomFolderName();
        document = DocumentFactory.withRandomDocumentName();
    }

    @AfterClass(alwaysRun = true)
    public void stopBrowser() {
        DriverSingleton.closeDriver();
    }
}