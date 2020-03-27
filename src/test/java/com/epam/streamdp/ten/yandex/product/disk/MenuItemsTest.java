package com.epam.streamdp.ten.yandex.product.disk;

import com.epam.streamdp.ten.yandex.product.disk.screen.YandexDiskInitialPage;
import com.epam.streamdp.ten.yandex.product.disk.screen.YandexDiskMainPage;
import org.openqa.selenium.By;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class MenuItemsTest extends CommonConditions {
    private By recentLink = By.cssSelector("div.navigation__items_standard > div:nth-child(1) > a");
    private By diskLink = By.id("/disk");
    private By photoLink = By.cssSelector("div.navigation__items_standard > div:nth-child(3) > a");
    private By albumsLink = By.cssSelector("div.navigation__items_standard > div:nth-child(4) > a");
    private By sharedLink = By.cssSelector("div.navigation__items_standard > div:nth-child(5) > a");
    private By journalLink = By.cssSelector("div.navigation__items_standard > div:nth-child(6) > a");
    private By attachLink = By.cssSelector("div.navigation__items_standard > div:nth-child(7) > a");
    private By trashLink = By.id("/trash");

    @Test(description = "Check that all main menu items works correctly and lead to correct page: Recent, Files," +
            " Photo, Published, Journal, Attach, Trash.", priority = 3)
    public void allMainMenuItemsShouldBeWorksCorrectlyAndLeadToCorrectPage() {
        SoftAssert softAssertion = new SoftAssert();
        YandexDiskMainPage yandexDiskMainPage = new YandexDiskInitialPage(driver)
                .openPage()
                .goTologinPage()
                .sentCredentials(correctCredentials);
        softAssertion.assertTrue(yandexDiskMainPage.isMenuItemLinkCorrect(recentLink), "recentLink is broken.");
        softAssertion.assertTrue(yandexDiskMainPage.isMenuItemLinkCorrect(diskLink), "diskLink is broken.");
        softAssertion.assertTrue(yandexDiskMainPage.isMenuItemLinkCorrect(photoLink), "photoLink is broken.");
        softAssertion.assertTrue(yandexDiskMainPage.isMenuItemLinkCorrect(albumsLink), "albumsLink is broken.");
        softAssertion.assertTrue(yandexDiskMainPage.isMenuItemLinkCorrect(sharedLink), "sharedLink is broken.");
        softAssertion.assertTrue(yandexDiskMainPage.isMenuItemLinkCorrect(journalLink), "journalLink is broken.");
        softAssertion.assertTrue(yandexDiskMainPage.isMenuItemLinkCorrect(attachLink), "attachLink is broken.");
        softAssertion.assertTrue(yandexDiskMainPage.isMenuItemLinkCorrect(trashLink), "trashLink is broken.");
        softAssertion.assertAll();
    }
}
