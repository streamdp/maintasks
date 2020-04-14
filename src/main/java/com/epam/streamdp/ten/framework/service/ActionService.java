package com.epam.streamdp.ten.framework.service;

import com.epam.streamdp.ten.yandex.product.disk.screen.YandexDiskFilesPage;
import com.epam.streamdp.ten.yandex.product.disk.screen.YandexDiskMainPage;
import org.openqa.selenium.By;

public class ActionService {

    public void createFolder(String folderName) {
        new YandexDiskMainPage()
                .goToFilesMenuItem()
                .createFolder(folderName);
    }

    public void createTextDocument(String folderName, String documentName, String stringWithTextForTest) {
        new YandexDiskFilesPage()
                .createTextDocument(folderName)
                .inputSomeText(stringWithTextForTest)
                .renameDocument(documentName)
                .closeDocument();
    }

    public boolean checkLink(By locator) {
        return new YandexDiskMainPage().isMenuItemLinkCorrect(locator);
    }
}
