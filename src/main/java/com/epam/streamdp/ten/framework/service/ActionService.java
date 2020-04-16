package com.epam.streamdp.ten.framework.service;

import com.epam.streamdp.ten.framework.logger.Log;
import com.epam.streamdp.ten.yandex.product.disk.screen.YandexDiskFilesPage;
import com.epam.streamdp.ten.yandex.product.disk.screen.YandexDiskMainPage;
import org.openqa.selenium.By;

public class ActionService {

    public void createFolder(String folderName) {
        new YandexDiskMainPage()
                .goToFilesMenuItem()
                .createFolder(folderName);
        Log.info("Folder created: " + folderName);
    }

    public void createTextDocument(String folderName, String documentName, String stringWithTextForTest) {
        new YandexDiskFilesPage()
                .createTextDocument(folderName)
                .inputSomeText(stringWithTextForTest)
                .renameDocument(documentName)
                .closeDocument();
        Log.info("Text document created: " + documentName);
    }

    public boolean checkLink(By locator) {
        Log.info("Checking link: " + locator.toString());
        return new YandexDiskMainPage().isMenuItemLinkCorrect(locator);
    }
}
