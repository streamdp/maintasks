package com.epam.streamdp.ten.yandex.product.disk;


import com.epam.streamdp.ten.framework.driver.DriverSingleton;
import com.epam.streamdp.ten.framework.listener.TestListener;
import com.epam.streamdp.ten.framework.model.User;
import com.epam.streamdp.ten.framework.service.UserCreator;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;

import java.util.Random;

@Listeners({TestListener.class})
public class CommonConditions {
    protected Random random = new Random();
    protected WebDriver driver;
    protected User correctCredentials;

    @BeforeMethod()
    public void setUp() {
        driver = DriverSingleton.getDriver();
        correctCredentials = UserCreator.withCredentialsFromProperty();
    }

    @AfterMethod(alwaysRun = true)
    public void stopBrowser() {
        DriverSingleton.closeDriver();
    }
}
