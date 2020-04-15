package com.epam.streamdp.ten.yandex.product.disk;

import org.testng.Assert;
import org.testng.annotations.Test;

public class FolderCreationsTest extends CommonConditions {

    @Test(description = "Check creation of new folder inside Files (use unique name for each new folder)," +
            " check you can visit that folder.", priority = 4)
    public void folderCanBeCreatedAndVisited() {
        String folderName = "folderName" + 4 + random.nextInt(1000);
        yandexAccountService.signIn(correctCredentials);
        yandexActionService.createFolder(folderName);
        Assert.assertTrue(yandexDiskFilesPage.isFolderAvailableForVisit(folderName),
                String.format("Folder %s creation error.", folderName));
    }
}
