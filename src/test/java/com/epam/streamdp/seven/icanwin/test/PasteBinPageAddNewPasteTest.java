package com.epam.streamdp.seven.icanwin.test;

import com.epam.streamdp.seven.BaseTest;
import com.epam.streamdp.seven.icanwin.page.PastebinMain;
import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class PasteBinPageAddNewPasteTest extends BaseTest {

    @BeforeMethod(alwaysRun = true)
    public void browserSetup() {
        setUp();
    }

    @Test(description = "Just first test, JIRA binding can be here")
    public void createNewPaste() {
        new PastebinMain(driver).openPage()
                .fillInputFieldForNewPaste("Hello from WebDriver")
                .selectPasteExpirationTime()
                .fillInputFieldForTitle("helloweb")
                .createNewPaste()
                .waitingForContent();
        Assert.assertEquals(driver.findElement(By.xpath("//h1")).getText(), "helloweb",
                "Invalid page loaded.");
    }

    @AfterMethod(alwaysRun = true)
    public void browserTearDown() {
        tearDown();
    }
}
