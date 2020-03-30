package com.epam.streamdp.ten.framework.service;

import java.util.ResourceBundle;

public class TestDataReader {
    private static ResourceBundle resourceBundle;

    public TestDataReader(String model) {
        resourceBundle = ResourceBundle.getBundle(System.getProperty("environment") + "-" + model);
    }

    public String getTestData(String key) {
        return resourceBundle.getString(key);
    }
}
