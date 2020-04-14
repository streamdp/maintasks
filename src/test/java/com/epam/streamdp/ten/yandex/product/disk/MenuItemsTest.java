package com.epam.streamdp.ten.yandex.product.disk;

import com.epam.streamdp.ten.framework.service.AccountService;
import com.epam.streamdp.ten.framework.service.ActionService;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import static com.epam.streamdp.ten.yandex.product.disk.screen.YandexDiskMainPage.*;

public class MenuItemsTest extends CommonConditions {

    @Test(description = "Check that all main menu items works correctly and lead to correct page: Recent, Files," +
            " Photo, Published, Journal, Attach, Trash.", priority = 3)
    public void allMainMenuItemsShouldBeWorksCorrectlyAndLeadToCorrectPage() {
        SoftAssert softAssertion = new SoftAssert();
        new AccountService().signIn(correctCredentials);
        softAssertion.assertTrue(new ActionService().checkLink(RECENT_LINK), "recentLink is broken.");
        softAssertion.assertTrue(new ActionService().checkLink(DISK_LINK), "diskLink is broken.");
        softAssertion.assertTrue(new ActionService().checkLink(PHOTO_LINK), "photoLink is broken.");
        softAssertion.assertTrue(new ActionService().checkLink(ALBUM_LINK), "albumsLink is broken.");
        softAssertion.assertTrue(new ActionService().checkLink(SHARED_LINK), "sharedLink is broken.");
        softAssertion.assertTrue(new ActionService().checkLink(JOURNAL_LINK), "journalLink is broken.");
        softAssertion.assertTrue(new ActionService().checkLink(ATTACH_LINK), "attachLink is broken.");
        softAssertion.assertTrue(new ActionService().checkLink(TRASH_LINK), "trashLink is broken.");
        softAssertion.assertAll();
    }
}
