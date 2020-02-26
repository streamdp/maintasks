package com.epam.streamdp.seven.icanwin.test;

import com.epam.streamdp.seven.icanwin.page.Pastebin;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class PasteBinPageAddNewPasteTest {
    private WebDriver driver;

    @BeforeMethod(alwaysRun = true)
    public void browserSetup() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @Test(description = "Just first test, JIRA binding can be here")
    public void createNewPaste() {
        new Pastebin(driver).openPage()
                .fillInputFieldForNewPaste("Hello from WebDriver")
                .selectPasteExpirationTime()
                .fillInputFieldForTitle("helloweb")
                .createNewPaste();
    }

    @AfterMethod(alwaysRun = true)
    public void browserTearDown() {
        driver.quit();
        driver = null;
    }
}
