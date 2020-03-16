package com.epam.streamdp.eight.test;

import com.epam.streamdp.eight.model.User;
import com.epam.streamdp.eight.page.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.Random;

public class YandexDiskTest {
    Random random = new Random();
    private WebDriver driver;
    private User correctCredentials = new User("iv4nov1vanewan", "AxmTYklsjo190QW");
    private By recentLink = By.cssSelector("div.navigation__items_standard > div:nth-child(1) > a");
    private By diskLink = By.id("/disk");
    private By photoLink = By.cssSelector("div.navigation__items_standard > div:nth-child(3) > a");
    private By albumsLink = By.cssSelector("div.navigation__items_standard > div:nth-child(4) > a");
    private By sharedLink = By.cssSelector("div.navigation__items_standard > div:nth-child(5) > a");
    private By journalLink = By.cssSelector("div.navigation__items_standard > div:nth-child(6) > a");
    private By attachLink = By.cssSelector("div.navigation__items_standard > div:nth-child(7) > a");
    private By trashLink = By.id("/trash");

    @BeforeMethod(alwaysRun = true)
    public void browserSetup() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @Test(description = "Check the user error message when userName incorrect.", priority = 1)
    public void shouldBeReturnedMessageAboutWrongUsername() {
        String incorrectLogin = "lskkdej";
        Assert.assertTrue(new YandexDiskInitialPage(driver)
                .openPage()
                .goTologinPage()
                .sentLogin(incorrectLogin)
                .isErrorMessageWasDisplayed(), "Error message was not be displayed.");
    }

    @Test(description = "Check the user error message when userName incorrect.", priority = 2)
    public void shouldBeReturnedMessageAboutWrongPassword() {
        User userWithIncorrectPassword = new User("iv4nov1vanewan", "AxmTYklsjo");
        Assert.assertTrue(new YandexDiskInitialPage(driver)
                .openPage()
                .goTologinPage()
                .sentLogin(userWithIncorrectPassword.getLogin())
                .sentPassword(userWithIncorrectPassword.getPassword())
                .isErrorMessageWasDisplayed(), "Error message was not be displayed.");
    }

    @Test(description = "Check that all main menu items works correctly and lead to correct page: Recent, Files," +
            " Photo, Published, Journal, Attach, Trash.", priority = 3)
    public void allMainMenuItemsShouldBeWorksCorrectlyAndLeadToCorrectPage() {
        SoftAssert softAssertion = new SoftAssert();
        YandexDiskMainPage yandexDiskMainPage = new YandexDiskInitialPage(driver)
                .openPage()
                .goTologinPage()
                .sentCredentials(correctCredentials);
        softAssertion.assertTrue(yandexDiskMainPage.isMenuItemLinkCorrect(recentLink), "recentLink is broken.");
        softAssertion.assertTrue(yandexDiskMainPage.isMenuItemLinkCorrect(diskLink), "diskLink is broken.");
        softAssertion.assertTrue(yandexDiskMainPage.isMenuItemLinkCorrect(photoLink), "photoLink is broken.");
        softAssertion.assertTrue(yandexDiskMainPage.isMenuItemLinkCorrect(albumsLink), "albumsLink is broken.");
        softAssertion.assertTrue(yandexDiskMainPage.isMenuItemLinkCorrect(sharedLink), "sharedLink is broken.");
        softAssertion.assertTrue(yandexDiskMainPage.isMenuItemLinkCorrect(journalLink), "journalLink is broken.");
        softAssertion.assertTrue(yandexDiskMainPage.isMenuItemLinkCorrect(attachLink), "attachLink is broken.");
        softAssertion.assertTrue(yandexDiskMainPage.isMenuItemLinkCorrect(trashLink), "trashLink is broken.");
        softAssertion.assertAll();
    }

    @Test(description = "Check creation of new folder inside Files (use unique name for each new folder)," +
            " check you can visit that folder.", priority = 4)
    public void folderCanBeCreatedAndVisited() {
        String folderName = "folderName" + 4 + random.nextInt(1000);
        YandexDiskFilesPage yandexDiskMain = new YandexDiskInitialPage(driver)
                .openPage()
                .goTologinPage()
                .sentCredentials(correctCredentials)
                .goToFilesMenuItem()
                .createFolder(folderName);
        Assert.assertTrue(yandexDiskMain.isFolderAvailableForVisit(folderName),
                String.format("Folder %s creation error.", folderName));
    }

    @Test(description = "Create new Word document inside that new folder, give it name, past some text inside document " +
            "and save it. Check that you can see document inside appropriate folder and you can reopen it for editing " +
            "and you see all you information saved correctly.", priority = 5)
    public void documentCanBeCreateAndOpening() {
        SoftAssert softAssertion = new SoftAssert();
        String folderName = "Documents" + 5 + random.nextInt(1000);
        String documentName = "newDocument" + random.nextInt(1000);
        String stringWithTextForTest = "Hello world!";
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

    @Test(description = "Test you can move document to trash. Check that you see document in trash but not in origin " +
            "folder.", priority = 6)
    public void moveToTrashItemShouldBeAvailable() {
        SoftAssert softAssertion = new SoftAssert();
        String folderName = "Documents" + 6 + random.nextInt(1000);
        YandexDiskFilesPage yandexDiskFilesPage = new YandexDiskInitialPage(driver)
                .openPage()
                .goTologinPage()
                .sentCredentials(correctCredentials)
                .goToFilesMenuItem();
        softAssertion.assertTrue(yandexDiskFilesPage.createFolder(folderName).isItemPresent(folderName),
                String.format("Folder %s creation error.", folderName));
        softAssertion.assertFalse(yandexDiskFilesPage.removeItem(folderName).isItemPresent(folderName),
                String.format("Folder %s removing error.", folderName));
        softAssertion.assertTrue(yandexDiskFilesPage.goToTrash().isItemPresent(folderName),
                String.format("Folder %s not found in Trash folder.", folderName));
        softAssertion.assertAll();
    }

    @Test(description = "Test you can empty trash. Check that document removed completely.",
            priority = 7)
    public void trashFolderShouldBeEmpty() {
        YandexDiskTrashPage yandexDiskTrashPage = new YandexDiskInitialPage(driver)
                .openPage()
                .goTologinPage()
                .sentCredentials(correctCredentials)
                .goToTrash()
                .clickEmptyTrashButton();
        Assert.assertTrue(yandexDiskTrashPage.getListItems().isEmpty(),
                "Error emptying trash. Folder contains items.");
    }

    @AfterMethod(alwaysRun = true)
    public void browserTearDown() {
        driver.quit();
        driver = null;
    }
}