package com.epam.streamdp.ten.yandex.product.disk;

import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class TrashFolderTest extends CommonConditions {

    @Test(description = "Test you can move document to trash. Check that you see document in trash but not in origin " +
            "folder.")
    public void moveToTrashItemShouldBeAvailable() {
        SoftAssert softAssertion = new SoftAssert();
        String folderName = "Documents" + 6 + random.nextInt(1000);
        yandexAccountService.signIn(correctCredentials);
        yandexDiskMainPage.goToFilesMenuItem();
        softAssertion.assertTrue(yandexDiskFilesPage.createFolder(folderName).isItemPresent(folderName),
                String.format("Folder %s creation error.", folderName));
        softAssertion.assertFalse(yandexDiskFilesPage.removeItem(folderName).isItemPresent(folderName),
                String.format("Folder %s removing error.", folderName));
        softAssertion.assertTrue(yandexDiskFilesPage.goToTrash().isItemPresent(folderName),
                String.format("Folder %s not found in Trash folder.", folderName));
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
