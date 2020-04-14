package com.epam.streamdp.ten.yandex.product.disk;

import com.epam.streamdp.ten.framework.service.AccountService;
import com.epam.streamdp.ten.framework.service.ActionService;
import com.epam.streamdp.ten.yandex.product.disk.screen.YandexDiskFilesPage;
import com.epam.streamdp.ten.yandex.product.disk.screen.YandexDiskMainPage;
import com.epam.streamdp.ten.yandex.product.disk.screen.YandexDiskTextDocumentPage;
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
        new AccountService().signIn(correctCredentials);
        new ActionService().createFolder(folderName);
        new ActionService().createTextDocument(folderName, documentName, stringWithTextForTest);
        softAssertion.assertTrue(new YandexDiskMainPage().isItemPresent(documentName + ".docx"), "Error creating file, file not found.");
        new YandexDiskFilesPage().openTextDocument(documentName);
        softAssertion.assertTrue(new YandexDiskTextDocumentPage().isDocumentOpened(documentName), "Error opening document.");
        softAssertion.assertEquals(new YandexDiskTextDocumentPage().getContent(), stringWithTextForTest, "Invalid text received from document.");
        softAssertion.assertAll();
    }
}
