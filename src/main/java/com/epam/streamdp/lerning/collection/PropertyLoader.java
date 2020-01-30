package com.epam.streamdp.lerning.collection;

import java.io.*;
import java.util.Properties;

public class PropertyLoader {
    public Properties loadFile(String filename) {
        Properties properties = new Properties();
        try {
           // properties.load(getClass().getClassLoader().getResourceAsStream(filename));
            properties.load(new FileReader(filename));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return properties;
    }
}
