package com.epam.streamdp.seven.hardcore.test;

import com.epam.streamdp.seven.hardcore.page.GoogleCloud;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class GoogleCloudPlatformPricingCalculatorTest {
    private WebDriver driver;
    private String testEmailString = "Estimated Monthly Cost: USD 3,829.29";

    @BeforeMethod(alwaysRun = true)
    public void browserSetup() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @Test(description = "Create a new compute engine and email this. Testing receive correct email content.")
    public void createNewComputeEngine() {
        String searchTerm = "Google Cloud Platform Pricing Calculator";
        String emailedEstimatedMonthlyCost = new GoogleCloud(driver)
                .openPage()
                .fillSearchInputFieldAndGo(searchTerm)
                .followTheFirstLink(searchTerm)
                .fillingFields()
                .emailEstimate()
                .fillingFieldsAndEmailEstimate()
                .receiveEmail();
        Assert.assertEquals(emailedEstimatedMonthlyCost, testEmailString, "We get wrong email content.");
    }

    @AfterMethod(alwaysRun = true)
    public void browserTearDown() {
        driver.quit();
        driver = null;
    }
}
