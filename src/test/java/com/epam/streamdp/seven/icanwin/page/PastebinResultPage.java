package com.epam.streamdp.seven.icanwin.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PastebinResultPage extends PastebinMainPage {
    public PastebinResultPage(WebDriver driver) {
        super(driver);
    }

    public PastebinResultPage waitingForContent() {
        new WebDriverWait(driver, WAIT_TIMEOUT_SECONDS)
                .until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='footer']")));
        return this;
    }

    public String getPasteName() {
        return driver.findElement(By.xpath("//h1")).getText();
    }
}
