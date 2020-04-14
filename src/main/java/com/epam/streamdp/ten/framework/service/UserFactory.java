package com.epam.streamdp.ten.framework.service;


import com.epam.streamdp.ten.framework.model.User;

public class UserFactory {
    public static final String USER_LOGIN = "user.login";
    public static final String USER_PASSWORD = "user.password";

    private UserFactory() {
        throw new IllegalStateException("Service class");
    }

    public static User withCredentialsFromProperty() {
        TestDataReader reader = new TestDataReader("user");
        return new User(reader.getTestData(USER_LOGIN),
                reader.getTestData(USER_PASSWORD));
    }

    public static User withSelectedCredentials(String user, String password) {
        return new User(user, password);
    }
}