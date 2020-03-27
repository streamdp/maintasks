package com.epam.streamdp.nine.test;

import com.epam.streamdp.nine.driver.DriverSingleton;
import com.epam.streamdp.nine.model.Configuration;
import com.epam.streamdp.nine.model.User;
import com.epam.streamdp.nine.service.ConfigurationCreator;
import com.epam.streamdp.nine.service.UserCreator;
import com.epam.streamdp.nine.util.TestListener;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;

import java.util.Arrays;
import java.util.List;

@Listeners({TestListener.class})
public class CommonConditions {
    protected WebDriver driver;
    public final String FIELD_ERROR = "%s field is incorrectly filled.";
    public Configuration configuration;
    public List<String> computeEngineListOptions;
    public List<String> listOptionForTest;
    public String searchTerm;
    public String testEmailString;
    public User user;

    @BeforeMethod()
    public void setUp() {
        driver = DriverSingleton.getDriver();
        configuration = ConfigurationCreator.withParametersFromProperty();
        listOptionForTest = Arrays.asList(
                "VM class: regular",
                "Instance type: n1-standard-8",
                "Region: Taiwan",
                "Total available local SSD space 1x375 GB",
                "Commitment term: 1 Year",
                "Estimated Component Cost: USD 3,829.29 per 1 month");
        searchTerm = "Google Cloud Platform Pricing Calculator";
        testEmailString = "Estimated Monthly Cost: USD 3,829.29";
        user = UserCreator.withCredentialsFromProperty();
    }

    @AfterMethod(alwaysRun = true)
    public void stopBrowser() {
        DriverSingleton.closeDriver();
    }
}
