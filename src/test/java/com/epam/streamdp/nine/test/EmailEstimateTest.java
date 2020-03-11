package com.epam.streamdp.nine.test;

import com.epam.streamdp.nine.model.Configuration;
import com.epam.streamdp.nine.model.User;
import com.epam.streamdp.nine.page.GoogleCloudMainPage;
import com.epam.streamdp.nine.service.ConfigurationCreator;
import com.epam.streamdp.nine.service.UserCreator;
import org.testng.Assert;
import org.testng.annotations.Test;

public class EmailEstimateTest extends CommonConditions {
    @Test(description = "Create a new compute engine and email this. Testing receive correct email content.")
    public void createNewComputeEngineAndEmailEstimate() {
        String testEmailString = "Estimated Monthly Cost: USD 3,829.29";
        String searchTerm = "Google Cloud Platform Pricing Calculator";
        Configuration configuration = ConfigurationCreator.withParametersFromProperty();
        User user = UserCreator.withCredentialsFromProperty();
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