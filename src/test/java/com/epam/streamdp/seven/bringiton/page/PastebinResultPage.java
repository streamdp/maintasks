package com.epam.streamdp.seven.bringiton.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

import java.util.Arrays;
import java.util.List;

public class PastebinResultPage extends PastebinMainPage {
    private static final String splitRegexp = "\\n";
    @FindBy(xpath = "//span[@class='h_640']/a")
    private WebElement typeOfHighlightingSyntax;
    @FindBy(id = "paste_code")
    private WebElement fieldForContentInput;

    public PastebinResultPage(WebDriver driver) {
        super(driver);
    }

    public void waitingForContent() {
        webDriverWait.until(ExpectedConditions.presenceOfElementLocated(By.id("footer")));
    }

    public String getTitleStringOfPaste(String syntaxHighlighting, String titleName) {
        waitingForContent();
        return String.format("[%s] %s - Pastebin.com", syntaxHighlighting, titleName);
    }

    public String getTypeOfHighlightingSyntaxText() {
        return typeOfHighlightingSyntax.getText();
    }

    public List<String> getContendPaste() {
        return Arrays.asList(fieldForContentInput.getText().split(splitRegexp));
    }
}
