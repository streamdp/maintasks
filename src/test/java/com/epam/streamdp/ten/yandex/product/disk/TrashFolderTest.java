package com.epam.streamdp.ten.yandex.product.disk;

import com.epam.streamdp.ten.framework.service.AccountService;
import com.epam.streamdp.ten.yandex.product.disk.screen.YandexDiskFilesPage;
import com.epam.streamdp.ten.yandex.product.disk.screen.YandexDiskMainPage;
import com.epam.streamdp.ten.yandex.product.disk.screen.YandexDiskTrashPage;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class TrashFolderTest extends CommonConditions {

    @Test(description = "Test you can move document to trash. Check that you see document in trash but not in origin " +
            "folder.")
    public void moveToTrashItemShouldBeAvailable() {
        SoftAssert softAssertion = new SoftAssert();
        String folderName = "Documents" + 6 + random.nextInt(1000);
        new AccountService().signIn(correctCredentials);
        new YandexDiskMainPage().goToFilesMenuItem();
        softAssertion.assertTrue(new YandexDiskFilesPage().createFolder(folderName).isItemPresent(folderName),
                String.format("Folder %s creation error.", folderName));
        softAssertion.assertFalse(new YandexDiskFilesPage().removeItem(folderName).isItemPresent(folderName),
                String.format("Folder %s removing error.", folderName));
        softAssertion.assertTrue(new YandexDiskFilesPage().goToTrash().isItemPresent(folderName),
                String.format("Folder %s not found in Trash folder.", folderName));
        softAssertion.assertAll();
    }

    @Test(description = "Test you can empty trash. Check that document removed completely.",
            dependsOnMethods = {"moveToTrashItemShouldBeAvailable"})
    public void trashFolderShouldBeEmpty() {
        new AccountService().signIn(correctCredentials);
        new YandexDiskMainPage().goToTrash().clickEmptyTrashButton();
        Assert.assertTrue(new YandexDiskTrashPage().getListItems().isEmpty(),
                "Error emptying trash. Folder contains items.");
    }
}
