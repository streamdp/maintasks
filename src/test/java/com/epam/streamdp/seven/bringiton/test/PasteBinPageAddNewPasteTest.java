package com.epam.streamdp.seven.bringiton.test;

import com.epam.streamdp.seven.BaseTest;
import com.epam.streamdp.seven.bringiton.page.PastebinMainPage;
import com.epam.streamdp.seven.bringiton.page.PastebinResultPage;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.Arrays;
import java.util.List;

public class PasteBinPageAddNewPasteTest extends BaseTest {
    private List<String> content = Arrays.asList(
            "git config --global user.name  \"New Sheriff in Town\"",
            "git reset $(git commit-tree HEAD^{tree} -m \"Legacy code\")",
            "git push origin master --force");
    private String titleName = "how to gain dominance among developers";
    private String syntax = "Bash";

    @Test(description = "Create a new paste and check the correctness of the filling in the fields.")
    public void createNewPaste() {
        SoftAssert softAssertion = new SoftAssert();
        PastebinResultPage pastebinResultPage = new PastebinMainPage(driver).openPage()
                .fillInputFieldForNewPaste(content)
                .selectHighlightingSyntax(syntax)
                .selectPasteExpirationTime()
                .fillInputFieldForTitle(titleName)
                .createNewPaste();
        softAssertion.assertEquals(pastebinResultPage.getTitleStringOfPaste(syntax, titleName), driver.getTitle(),
                "Wrong title for the new paste.");
        softAssertion.assertEquals(pastebinResultPage.getHighlightingSyntaxWasSelect(), syntax,
                "Wrong syntax highlight type selected.");
        softAssertion.assertEquals(pastebinResultPage.getContendPaste(), content,
                "We received the wrong content from the page.");
        softAssertion.assertAll();
    }
}
