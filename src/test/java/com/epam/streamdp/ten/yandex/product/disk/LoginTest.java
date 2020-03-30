package com.epam.streamdp.ten.yandex.product.disk;

import com.epam.streamdp.ten.framework.model.User;
import com.epam.streamdp.ten.framework.service.UserCreator;
import com.epam.streamdp.ten.yandex.product.disk.screen.YandexDiskInitialPage;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginTest extends CommonConditions {

    @Test(description = "Check the user error message when userName incorrect.", priority = 1)
    public void shouldBeReturnedMessageAboutWrongUsername() {
        String incorrectLogin = "lskkdej";
        Assert.assertTrue(new YandexDiskInitialPage(driver)
                .openPage()
                .goTologinPage()
                .sentLogin(incorrectLogin)
                .isErrorMessageWasDisplayed(), "Error message was not be displayed.");
    }

    @Test(description = "Check the user error message when userName incorrect.", priority = 2)
    public void shouldBeReturnedMessageAboutWrongPassword() {
        User userWithIncorrectPassword = UserCreator.withSelectedCredentials("iv4nov1vanewan", "AxmTYklsjo");
        Assert.assertTrue(new YandexDiskInitialPage(driver)
                .openPage()
                .goTologinPage()
                .sentLogin(userWithIncorrectPassword.getLogin())
                .sentPassword(userWithIncorrectPassword.getPassword())
                .isErrorMessageWasDisplayed(), "Error message was not be displayed.");
    }
}