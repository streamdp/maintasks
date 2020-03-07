package com.epam.streamdp.eight.page;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.Objects;

import static com.epam.streamdp.eight.page.YandexDiskInitialPage.WAIT_TIMEOUT_SECONDS;

public class YandexDiskTrashPage extends YandexDiskMainPage {
    public static final String URL_MATCH = "trash";
    protected WebDriver driver;
    protected WebDriverWait webDriverWait;
    protected Actions builder;

    private By emptyTrashButton = By.xpath("//button[@class='control button2 button2_view_default button2_tone_default button2_size_m button2_theme_raised client-listing__clean-trash-button']");
    private By confirmationButton = By.xpath("//*[@id='nb-1']/body/div[4]/div/div/div/div/div/div[2]/button[2]");
    private By headingTitle = By.tagName("h1");

    public YandexDiskTrashPage(WebDriver driver) {
        super(driver);
        if (!driver.getCurrentUrl().contains(URL_MATCH)) {
            throw new IllegalStateException("This is not the page you are expected");
        }
        this.driver = driver;
        this.builder = new Actions(driver);
        this.webDriverWait = new WebDriverWait(driver, WAIT_TIMEOUT_SECONDS);
    }

    public void waitingForContent() {
        webDriverWait.until((ExpectedCondition<Boolean>) wd ->
                ((JavascriptExecutor) Objects.requireNonNull(wd)).executeScript("return document.readyState").equals("complete"));
        webDriverWait.until(ExpectedConditions.presenceOfElementLocated(headingTitle));
    }

    public YandexDiskTrashPage clickEmptyTrashButton() {
        waitingForContent();
        webDriverWait.until(ExpectedConditions.presenceOfElementLocated(emptyTrashButton));
        driver.findElement(emptyTrashButton).click();
        webDriverWait.until(ExpectedConditions.presenceOfElementLocated(confirmationButton));
        builder.pause(200).click(driver.findElement(confirmationButton)).pause(200).build().perform();
        refreshPage();
        builder.pause(500);
        return this;
    }
}
