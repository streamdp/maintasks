package com.epam.streamdp.seven.hardcore.test;

import com.epam.streamdp.seven.BaseTest;
import com.epam.streamdp.seven.hardcore.page.GoogleCloudMain;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class GoogleCloudPlatformPricingCalculatorTest extends BaseTest {
    private String testEmailString = "Estimated Monthly Cost: USD 3,829.29";

    @BeforeMethod(alwaysRun = true)
    public void browserSetup() {
        setUp();
    }

    @Test(description = "Create a new compute engine and email this. Testing receive correct email content.")
    public void createNewComputeEngine() {
        String searchTerm = "Google Cloud Platform Pricing Calculator";
        String emailedEstimatedMonthlyCost = new GoogleCloudMain(driver)
                .openPage()
                .fillSearchInputFieldAndGo(searchTerm)
                .waitingForContent()
                .followTheFirstLink(searchTerm)
                .waitingForContent()
                .fillingFieldsAccordingToTheTestScenario()
                .emailEstimate()
                .waitingForContent()
                .fillingFirstNameLastNameEmailPhoneFieldsAndEmailEstimate()
                .receiveEmail();
        Assert.assertEquals(emailedEstimatedMonthlyCost, testEmailString, "We get wrong email content.");
    }

    @AfterMethod(alwaysRun = true)
    public void browserTearDown() {
        tearDown();
    }
}