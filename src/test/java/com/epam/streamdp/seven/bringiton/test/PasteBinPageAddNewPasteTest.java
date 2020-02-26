package com.epam.streamdp.seven.bringiton.test;

import com.epam.streamdp.seven.bringiton.page.Pastebin;
import com.epam.streamdp.seven.bringiton.page.PastebinResult;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.Arrays;
import java.util.List;

public class PasteBinPageAddNewPasteTest {
    private List<String> content = Arrays.asList(
            "git config --global user.name  \"New Sheriff in Town\"",
            "git reset $(git commit-tree HEAD^{tree} -m \"Legacy code\")",
            "git push origin master --force");
    private String titleName = "how to gain dominance among developers";
    private String syntax = "Bash";
    private WebDriver driver;


    @BeforeMethod(alwaysRun = true)
    public void browserSetup() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @Test(description = "Create a new paste and check the correctness of the filling in the fields.")
    public void createNewPaste() {
        SoftAssert softAssertion = new SoftAssert();
        PastebinResult pastebinResultPage = new Pastebin(driver).openPage()
                .fillInputFieldForNewPaste(content)
                .selectHighlightingSyntax(syntax)
                .selectPasteExpirationTime()
                .fillInputFieldForTitle(titleName)
                .createNewPaste();
        softAssertion.assertTrue(pastebinResultPage.isTitleOfPasteCorrect(syntax, titleName),
                "Wrong title for the new paste.");
        softAssertion.assertEquals(pastebinResultPage.getHighlightingSyntaxWasSelect(), syntax,
                "Wrong syntax highlight type selected.");
        softAssertion.assertEquals(pastebinResultPage.getContendPaste(), content,
                "We received the wrong content from the page.");
        softAssertion.assertAll();
    }

    @AfterMethod(alwaysRun = true)
    public void browserTearDown() {
        driver.quit();
        driver = null;
    }
}
