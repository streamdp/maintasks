package com.epam.streamdp.lerning.collection;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Properties;

public class PropertyMain {
    public static void main(String[] args) {
        String filename = "data/messages.properties";
        PropertyLoader loader = new PropertyLoader();
        Properties properties = loader.loadFile(filename);

        properties.list(System.out);
        String label = properties.getProperty("label");
        String value = properties.getProperty("abc");
        String valueDefault = properties.getProperty("xyz", "Y-");
        try {
            properties.store(new FileWriter(filename), "Comment Prop");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
