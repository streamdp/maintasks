package com.epam.streamdp.ten.yandex.product.disk;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class DocumentCreationsTest extends CommonConditions {

    @Test(description = "Create new Word document inside that new folder, give it name, past some text inside document " +
            "and save it. Check that you can see document inside appropriate folder and you can reopen it for editing " +
            "and you see all you information saved correctly.")
    public void documentCanBeCreateAndOpening() {
        SoftAssert softAssertion = new SoftAssert();
        yandexAccountService.signIn(correctCredentials);
        yandexActionService.createFolder(folder.getFolderName());
        yandexActionService.createTextDocument(folder.getFolderName(), document.getDocumentName(), document.getStringWithText());
        softAssertion.assertTrue(yandexDiskMainPage.isItemPresent(document.getDocumentName() + ".docx"), "Error creating file, file not found.");
        yandexDiskFilesPage.openTextDocument(document.getDocumentName());
        softAssertion.assertTrue(yandexDiskTextDocumentPage.isDocumentOpened(document.getDocumentName()), "Error opening document.");
        softAssertion.assertEquals(yandexDiskTextDocumentPage.getContent(), document.getStringWithText(), "Invalid text received from document.");
        softAssertion.assertAll();
    }
}