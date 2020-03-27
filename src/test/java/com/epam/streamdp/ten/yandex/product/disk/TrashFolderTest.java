package com.epam.streamdp.ten.yandex.product.disk;

import com.epam.streamdp.ten.yandex.product.disk.screen.YandexDiskFilesPage;
import com.epam.streamdp.ten.yandex.product.disk.screen.YandexDiskInitialPage;
import com.epam.streamdp.ten.yandex.product.disk.screen.YandexDiskTrashPage;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class TrashFolderTest extends CommonConditions {

    @Test(description = "Test you can move document to trash. Check that you see document in trash but not in origin " +
            "folder.", priority = 6)
    public void moveToTrashItemShouldBeAvailable() {
        SoftAssert softAssertion = new SoftAssert();
        String folderName = "Documents" + 6 + random.nextInt(1000);
        YandexDiskFilesPage yandexDiskFilesPage = new YandexDiskInitialPage(driver)
                .openPage()
                .goTologinPage()
                .sentCredentials(correctCredentials)
                .goToFilesMenuItem();
        softAssertion.assertTrue(yandexDiskFilesPage.createFolder(folderName).isItemPresent(folderName),
                String.format("Folder %s creation error.", folderName));
        softAssertion.assertFalse(yandexDiskFilesPage.removeItem(folderName).isItemPresent(folderName),
                String.format("Folder %s removing error.", folderName));
        softAssertion.assertTrue(yandexDiskFilesPage.goToTrash().isItemPresent(folderName),
                String.format("Folder %s not found in Trash folder.", folderName));
        softAssertion.assertAll();
    }

    @Test(description = "Test you can empty trash. Check that document removed completely.",
            priority = 7)
    public void trashFolderShouldBeEmpty() {
        YandexDiskTrashPage yandexDiskTrashPage = new YandexDiskInitialPage(driver)
                .openPage()
                .goTologinPage()
                .sentCredentials(correctCredentials)
                .goToTrash()
                .clickEmptyTrashButton();
        Assert.assertTrue(yandexDiskTrashPage.getListItems().isEmpty(),
                "Error emptying trash. Folder contains items.");
    }
}
