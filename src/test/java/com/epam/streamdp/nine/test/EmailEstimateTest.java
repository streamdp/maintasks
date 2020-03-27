package com.epam.streamdp.nine.test;

import com.epam.streamdp.nine.page.GoogleCloudMainPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class EmailEstimateTest extends CommonConditions {

    @Test(description = "Create a new compute engine and email this. Testing receive correct email content.")
    public void createNewComputeEngineAndEmailEstimate() {
        String emailedEstimatedMonthlyCost = new GoogleCloudMainPage(driver)
                .openPage()
                .fillSearchInputFieldAndGo(searchTerm)
                .followTheFirstLink(searchTerm)
                .fillingFieldsAccordingToTheTest(configuration)
                .clickEmailEstimateButton()
                .fillUserInformation(user)
                .receiveEmail();
        Assert.assertEquals(emailedEstimatedMonthlyCost, testEmailString, "We get wrong email content.");
    }
}