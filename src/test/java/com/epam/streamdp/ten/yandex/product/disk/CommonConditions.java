package com.epam.streamdp.ten.yandex.product.disk;

import com.epam.streamdp.ten.framework.driver.DriverSingleton;
import com.epam.streamdp.ten.framework.listener.TestListener;
import com.epam.streamdp.ten.framework.model.User;
import com.epam.streamdp.ten.framework.service.AccountService;
import com.epam.streamdp.ten.framework.service.ActionService;
import com.epam.streamdp.ten.framework.service.UserFactory;
import com.epam.streamdp.ten.yandex.product.disk.screen.*;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;

import java.util.Random;

@Listeners({TestListener.class})
public class CommonConditions {
    protected Random random = new Random();
    protected WebDriver driver;
    protected User correctCredentials;
    protected ActionService yandexActionService = new ActionService();
    protected AccountService yandexAccountService = new AccountService();
    protected YandexDiskMainPage yandexDiskMainPage = new YandexDiskMainPage();
    protected YandexDiskFilesPage yandexDiskFilesPage = new YandexDiskFilesPage();
    protected YandexDiskTextDocumentPage yandexDiskTextDocumentPage = new YandexDiskTextDocumentPage();
    protected YandexDiskInitialPage yandexDiskInitialPage = new YandexDiskInitialPage();
    protected YandexDiskTrashPage yandexDiskTrashPage = new YandexDiskTrashPage();

    @BeforeMethod()
    public void setUp() {
        driver = DriverSingleton.getDriver();
        correctCredentials = UserFactory.withCredentialsFromProperty();
    }

    @AfterMethod(alwaysRun = true)
    public void stopBrowser() {
        DriverSingleton.closeDriver();
    }
}