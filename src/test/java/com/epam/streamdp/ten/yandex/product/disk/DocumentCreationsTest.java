package com.epam.streamdp.ten.yandex.product.disk;

import com.epam.streamdp.ten.yandex.product.disk.screen.YandexDiskFilesPage;
import com.epam.streamdp.ten.yandex.product.disk.screen.YandexDiskInitialPage;
import com.epam.streamdp.ten.yandex.product.disk.screen.YandexDiskTextDocumentPage;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class DocumentCreationsTest extends CommonConditions {

    @Test(description = "Create new Word document inside that new folder, give it name, past some text inside document " +
            "and save it. Check that you can see document inside appropriate folder and you can reopen it for editing " +
            "and you see all you information saved correctly.", priority = 5)
    public void documentCanBeCreateAndOpening() {
        String folderName = "Documents" + 5 + random.nextInt(1000);
        String documentName = "newDocument" + random.nextInt(1000);
        String stringWithTextForTest = "Hello world!";

        SoftAssert softAssertion = new SoftAssert();
        YandexDiskFilesPage yandexDiskFilesPage = new YandexDiskInitialPage(driver)
                .openPage()
                .goTologinPage()
                .sentCredentials(correctCredentials)
                .goToFilesMenuItem()
                .createFolder(folderName)
                .createTextDocument(folderName)
                .inputSomeText(stringWithTextForTest)
                .renameDocument(documentName)
                .closeDocument();
        softAssertion.assertTrue(yandexDiskFilesPage.isItemPresent(documentName + ".docx"), "Error creating file, file not found.");
        YandexDiskTextDocumentPage textDocument = yandexDiskFilesPage.openTextDocument(documentName);
        softAssertion.assertTrue(textDocument.isDocumentOpened(documentName), "Error opening document.");
        softAssertion.assertEquals(textDocument.getContent(), stringWithTextForTest, "Invalid text received from document.");
        softAssertion.assertAll();
    }
}
