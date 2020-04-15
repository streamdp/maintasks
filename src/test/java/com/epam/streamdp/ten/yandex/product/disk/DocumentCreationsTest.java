package com.epam.streamdp.ten.yandex.product.disk;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class DocumentCreationsTest extends CommonConditions {

    @Test(description = "Create new Word document inside that new folder, give it name, past some text inside document " +
            "and save it. Check that you can see document inside appropriate folder and you can reopen it for editing " +
            "and you see all you information saved correctly.")
    public void documentCanBeCreateAndOpening() {
        String folderName = "Documents" + 5 + random.nextInt(1000);
        String documentName = "newDocument" + random.nextInt(1000);
        String stringWithTextForTest = "Hello world!";

        SoftAssert softAssertion = new SoftAssert();
        yandexAccountService.signIn(correctCredentials);
        yandexActionService.createFolder(folderName);
        yandexActionService.createTextDocument(folderName, documentName, stringWithTextForTest);
        softAssertion.assertTrue(yandexDiskMainPage.isItemPresent(documentName + ".docx"), "Error creating file, file not found.");
        yandexDiskFilesPage.openTextDocument(documentName);
        softAssertion.assertTrue(yandexDiskTextDocumentPage.isDocumentOpened(documentName), "Error opening document.");
        softAssertion.assertEquals(yandexDiskTextDocumentPage.getContent(), stringWithTextForTest, "Invalid text received from document.");
        softAssertion.assertAll();
    }
}
