package com.epam.streamdp.seven.icanwin.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class PastebinResult extends Pastebin {
    public PastebinResult(WebDriver driver) {
        super(driver);
        new WebDriverWait(driver, WAIT_TIMEOUT_SECONDS)
                .until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='footer']")));
    }

}
