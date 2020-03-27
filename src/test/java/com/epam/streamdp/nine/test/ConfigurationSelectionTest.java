package com.epam.streamdp.nine.test;

import com.epam.streamdp.nine.page.GoogleCloudMainPage;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

public class ConfigurationSelectionTest extends CommonConditions {

    @Test(description = "Create a new compute engine and get list options for estimate. Testing correct filling fields.")
    public void createNewComputeEngine() {
        SoftAssert softAssertion = new SoftAssert();
        computeEngineListOptions = new GoogleCloudMainPage(driver)
                .openPage()
                .fillSearchInputFieldAndGo(searchTerm)
                .followTheFirstLink(searchTerm)
                .fillingFieldsAccordingToTheTest(configuration)
                .getComputeEngineListOptions();
        softAssertion.assertEquals(computeEngineListOptions.get(1), listOptionForTest.get(0), String.format(FIELD_ERROR, "VM class"));
        softAssertion.assertEquals(computeEngineListOptions.get(2), listOptionForTest.get(1), String.format(FIELD_ERROR, "Instance type"));
        softAssertion.assertEquals(computeEngineListOptions.get(3), listOptionForTest.get(2), String.format(FIELD_ERROR, "Region"));
        softAssertion.assertEquals(computeEngineListOptions.get(7), listOptionForTest.get(3), String.format(FIELD_ERROR, "Local SSD"));
        softAssertion.assertEquals(computeEngineListOptions.get(8), listOptionForTest.get(4), String.format(FIELD_ERROR, "Commitment term"));
        softAssertion.assertEquals(computeEngineListOptions.get(9), listOptionForTest.get(5), "Estimated component cost is wrong.");
        softAssertion.assertAll();
    }
}