package com.epam.streamdp.ten.yandex.product.disk;


import org.testng.Assert;
import org.testng.annotations.Test;

public class FolderCreationsTest extends CommonConditions {

    @Test(description = "Check creation of new folder inside Files (use unique name for each new folder)," +
            " check you can visit that folder.")
    public void folderCanBeCreatedAndVisited() {
        yandexAccountService.signIn(correctCredentials);
        yandexActionService.createFolder(folder.getFolderName());
        Assert.assertTrue(yandexDiskFilesPage.isFolderAvailableForVisit(folder.getFolderName()),
                String.format("Folder %s creation error.", folder.getFolderName()));
    }
}