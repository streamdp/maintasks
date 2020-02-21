package com.epam.streamdp.lerning.waits;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class WebDriverSyncDemo {
    public static void main(String[] args) throws InterruptedException {
        WebDriver driver = new ChromeDriver();
//        driver.manage().timeouts().pageLoadTimeout(15, TimeUnit.SECONDS);
//        driver.manage().timeouts().setScriptTimeout(15, TimeUnit.SECONDS);
//        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);

        //     new WebDriverWait(driver, 10)
        //.until(ExpectedConditions.presenceOfElementLocated(By.id ("gsc-i-id1")));
        driver.get("http://radio-t.com");
        //new WebDriverWait(driver, 10).until(CustomConditions.jQueryAJAXCompleted());
        WebElement searchBtn = waitForElementLocatedBy(driver, By.xpath("//*[@id=\"navbar\"]/div/div[1]/label"));
        searchBtn.click();
        WebElement searchInput = driver.findElement(By.xpath("//*[@id=\"page-search-input\"]"));
        searchInput.sendKeys("при\n");
        new WebDriverWait(driver, 10).until(ExpectedConditions.presenceOfElementLocated(By.xpath("//*[@id=\"page-search\"]/div/div/div[*]/div/a[*]")));
        List<WebElement> searchResults = driver.findElements(By.xpath("//*[@id=\"page-search\"]/div/div/div[*]/div/a[*]"));
        searchResults.get(0).click();
        System.out.println(searchResults);
        try {
            Thread.sleep(20000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        driver.quit();
    }

    public static WebElement waitForElementLocatedBy(WebDriver driver, By by) {
        return new WebDriverWait(driver, 10).until(ExpectedConditions.presenceOfElementLocated(by));
    }
}
