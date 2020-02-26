package com.epam.streamdp.seven.hurtmeplenty.test;

import com.epam.streamdp.seven.hurtmeplenty.page.GoogleCloud;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GoogleCloudPlatformPricingCalculatorTest {
    private static final String FIELD_ERROR = "%s field is incorrectly filled.";
    private WebDriver driver;
    private List<String> computeEngineListOptions = new ArrayList<>();
    private List<String> listOptionForTest = Arrays.asList(
            "VM class: regular",
            "Instance type: n1-standard-8",
            "Region: Taiwan",
            "Total available local SSD space 1x375 GB",
            "Commitment term: 1 Year",
            "Estimated Component Cost: USD 3,829.29 per 1 month");

    @BeforeMethod(alwaysRun = true)
    public void browserSetup() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @Test(description = "Create a new compute engine and get list options for estimate. Testing correct filling fields.")
    public void createNewComputeEngine() {
        String searchTerm = "Google Cloud Platform Pricing Calculator";
        SoftAssert softAssertion = new SoftAssert();
        computeEngineListOptions = new GoogleCloud(driver)
                .openPage()
                .fillSearchInputFieldAndGo(searchTerm)
                .followTheFirstLink(searchTerm)
                .fillingFields()
                .getComputeEngineListOptions();
        softAssertion.assertEquals(computeEngineListOptions.get(1), listOptionForTest.get(0), String.format(FIELD_ERROR, "VM class"));
        softAssertion.assertEquals(computeEngineListOptions.get(2), listOptionForTest.get(1), String.format(FIELD_ERROR, "Instance type"));
        softAssertion.assertEquals(computeEngineListOptions.get(3), listOptionForTest.get(2), String.format(FIELD_ERROR, "Region"));
        softAssertion.assertEquals(computeEngineListOptions.get(7), listOptionForTest.get(3), String.format(FIELD_ERROR, "Local SSD"));
        softAssertion.assertEquals(computeEngineListOptions.get(8), listOptionForTest.get(4), String.format(FIELD_ERROR, "Commitment term"));
        softAssertion.assertEquals(computeEngineListOptions.get(9), listOptionForTest.get(5), "Estimated component cost is wrong.");
        softAssertion.assertAll();
    }

    @AfterMethod(alwaysRun = true)
    public void browserTearDown() {
        driver.quit();
        driver = null;
    }
}
