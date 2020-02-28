package com.epam.streamdp.seven.hardcore.test;

import com.epam.streamdp.seven.BaseTest;
import com.epam.streamdp.seven.hardcore.page.GoogleCloudMainPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class GoogleCloudPlatformPricingCalculatorTest extends BaseTest {
    @Test(description = "Create a new compute engine and email this. Testing receive correct email content.")
    public void createNewComputeEngine() {
        String testEmailString = "Estimated Monthly Cost: USD 3,829.29";
        String searchTerm = "Google Cloud Platform Pricing Calculator";
        String emailedEstimatedMonthlyCost = new GoogleCloudMainPage(driver)
                .openPage()
                .fillSearchInputFieldAndGo(searchTerm)
                .followTheFirstLink(searchTerm)
                .fillingFieldsAccordingToTheTestScenario()
                .clickEmailEstimateButton()
                .fillUserInformation()
                .receiveEmail();
        Assert.assertEquals(emailedEstimatedMonthlyCost, testEmailString, "We get wrong email content.");
    }
}