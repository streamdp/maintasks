package com.epam.streamdp.ten.yandex.product.disk;

import com.epam.streamdp.ten.framework.service.AccountService;
import com.epam.streamdp.ten.framework.service.ActionService;
import com.epam.streamdp.ten.yandex.product.disk.screen.YandexDiskFilesPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class FolderCreationsTest extends CommonConditions {

    @Test(description = "Check creation of new folder inside Files (use unique name for each new folder)," +
            " check you can visit that folder.", priority = 4)
    public void folderCanBeCreatedAndVisited() {
        String folderName = "folderName" + 4 + random.nextInt(1000);
        new AccountService().signIn(correctCredentials);
        new ActionService().createFolder(folderName);
        Assert.assertTrue(new YandexDiskFilesPage().isFolderAvailableForVisit(folderName),
                String.format("Folder %s creation error.", folderName));
    }
}
