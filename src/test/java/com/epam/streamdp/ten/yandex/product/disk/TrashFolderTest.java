package com.epam.streamdp.ten.yandex.product.disk;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class TrashFolderTest extends CommonConditions {

    @Test(description = "Test you can move document to trash. Check that you see document in trash but not in origin " +
            "folder.")
    public void moveToTrashItemShouldBeAvailable() {
        SoftAssert softAssertion = new SoftAssert();
        yandexAccountService.signIn(correctCredentials);
        yandexDiskMainPage.goToFilesMenuItem();
        softAssertion.assertTrue(yandexDiskFilesPage.createFolder(folder.getFolderName()).isItemPresent(folder.getFolderName()),
                String.format("Folder %s creation error.", folder.getFolderName()));
        softAssertion.assertFalse(yandexDiskFilesPage.removeItem(folder.getFolderName()).isItemPresent(folder.getFolderName()),
                String.format("Folder %s removing error.", folder.getFolderName()));
        softAssertion.assertTrue(yandexDiskFilesPage.goToTrash().isItemPresent(folder.getFolderName()),
                String.format("Folder %s not found in Trash folder.", folder.getFolderName()));
        softAssertion.assertAll();
    }

    @Test(description = "Test you can empty trash. Check that document removed completely.",
            dependsOnMethods = {"moveToTrashItemShouldBeAvailable"})
    public void trashFolderShouldBeEmpty() {
        yandexAccountService.signIn(correctCredentials);
        yandexDiskMainPage.goToTrash().clickEmptyTrashButton();
        Assert.assertTrue(yandexDiskTrashPage.getListItems().isEmpty(),
                "Error emptying trash. Folder contains items.");
    }
}