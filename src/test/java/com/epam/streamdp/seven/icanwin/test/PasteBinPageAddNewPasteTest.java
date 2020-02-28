package com.epam.streamdp.seven.icanwin.test;

import com.epam.streamdp.seven.BaseTest;
import com.epam.streamdp.seven.icanwin.page.PastebinMainPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class PasteBinPageAddNewPasteTest extends BaseTest {
    @Test(description = "Just first test, JIRA binding can be here")
    public void createNewPaste() {
        Assert.assertEquals(new PastebinMainPage(driver).openPage()
                        .fillInputFieldForNewPaste("Hello from WebDriver")
                        .selectPasteExpirationTime()
                        .fillInputFieldForTitle("helloweb")
                        .createNewPaste()
                        .waitingForContent()
                        .getPasteName(), "helloweb",
                "Invalid page loaded.");
    }
}
