package com.epam.streamdp.ten.yandex.product.disk;

import com.epam.streamdp.ten.framework.model.User;
import com.epam.streamdp.ten.framework.service.UserFactory;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginTest extends CommonConditions {

    @Test(description = "Check the user error message when userName incorrect.")
    public void shouldBeReturnedMessageAboutWrongUsername() {
        String incorrectLogin = "lskkdej";
        Assert.assertTrue(yandexDiskInitialPage
                .openPage()
                .goTologinPage()
                .sendLogin(incorrectLogin)
                .isErrorMessageWasDisplayed(), "Error message was not be displayed.");
    }

    @Test(description = "Check the user error message when userName incorrect.",
            dependsOnMethods = {"shouldBeReturnedMessageAboutWrongUsername"})
    public void shouldBeReturnedMessageAboutWrongPassword() {
        User userWithIncorrectPassword = UserFactory.withSelectedCredentials("iv4nov1vanewan", "AxmTYklsjo");
        Assert.assertTrue(yandexDiskInitialPage
                .openPage()
                .goTologinPage()
                .sendLogin(userWithIncorrectPassword.getLogin())
                .sendPassword(userWithIncorrectPassword.getPassword())
                .isErrorMessageWasDisplayed(), "Error message was not be displayed.");
    }
}