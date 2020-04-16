package com.epam.streamdp.ten.framework.service;

import com.epam.streamdp.ten.framework.logger.Log;
import com.epam.streamdp.ten.framework.model.User;
import com.epam.streamdp.ten.framework.screen.BasePage;
import com.epam.streamdp.ten.yandex.product.disk.screen.YandexDiskInitialPage;

public class AccountService extends BasePage {

    public AccountService() {
        super();
    }

    public void signIn(User user) {
        new YandexDiskInitialPage()
                .openPage()
                .goTologinPage()
                .sendCredentials(user);
        Log.info("Sing in with selected credentials: " + user.getLogin() + " " + user.getPassword());
    }
}
