package com.epam.streamdp.nine.service;

import com.epam.streamdp.nine.model.User;

public class UserCreator {
    public static final String USER_FIRST_NAME = "user.firstName";
    public static final String USER_LAST_NAME = "user.lastName";
    public static final String USER_PHONE = "user.phone";

    public static User withCredentialsFromProperty() {
        TestDataReader reader = new TestDataReader("user");
        return new User(reader.getTestData(USER_FIRST_NAME),
                reader.getTestData(USER_LAST_NAME),
                reader.getTestData(USER_PHONE));
    }
}