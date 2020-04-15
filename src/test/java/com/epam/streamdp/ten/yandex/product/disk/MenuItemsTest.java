package com.epam.streamdp.ten.yandex.product.disk;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static com.epam.streamdp.ten.yandex.product.disk.screen.YandexDiskMainPage.*;

public class MenuItemsTest extends CommonConditions {
    @DataProvider(name = "Provider of Links")
    public static Object[][] provideLinks() {
        return new Object[][]{
                {RECENT_LINK},
                {DISK_LINK},
                {PHOTO_LINK},
                {ALBUM_LINK},
                {SHARED_LINK},
                {JOURNAL_LINK},
                {ATTACH_LINK},
                {TRASH_LINK}
        };
    }

    @Test(description = "Check that all main menu items works correctly and lead to correct page: Recent, Files," +
            " Photo, Published, Journal, Attach, Trash.", priority = 3, dataProvider = "Provider of Links")
    public void allMainMenuItemsShouldBeWorksCorrectlyAndLeadToCorrectPage(By linkLocator) {
        yandexAccountService.signIn(correctCredentials);
        Assert.assertTrue(yandexActionService.checkLink(linkLocator), linkLocator + "is broken");
    }
}
