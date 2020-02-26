package com.epam.streamdp.seven.bringiton.page;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Arrays;
import java.util.List;

public class PastebinResult extends Pastebin {
    private static final String splitRegexp = "\\n";
    @FindBy(xpath = "//span[@class='h_640']/a")
    private WebElement typeOfHighlightingSyntax;
    @FindBy(xpath = "//textarea[@id='paste_code']")
    private WebElement contentInputField;

    public PastebinResult(WebDriver driver) {
        super(driver);
        new WebDriverWait(driver, WAIT_TIMEOUT_SECONDS)
                .until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id='footer']")));
    }

    public boolean isTitleOfPasteCorrect(String syntaxHighlighting, String titleName) {
        return String.format("[%s] %s - Pastebin.com", syntaxHighlighting, titleName).equalsIgnoreCase(driver.getTitle());
    }

    public String getHighlightingSyntaxWasSelect() {
        return typeOfHighlightingSyntax.getText();
    }

    public List<String> getContendPaste() {
        return Arrays.asList(contentInputField.getText().split(splitRegexp));
    }
}
