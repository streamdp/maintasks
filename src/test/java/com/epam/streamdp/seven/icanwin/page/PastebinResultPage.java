package com.epam.streamdp.seven.icanwin.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class PastebinResultPage extends PastebinMainPage {
    public PastebinResultPage(WebDriver driver) {
        super(driver);
    }

    public void waitingForContent() {
        webDriverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("footer")));
    }

    public String getPasteName() {
        waitingForContent();
        return driver.findElement(By.tagName("h1")).getText();
    }
}
