package com.epam.streamdp.four.actions;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

public class SaveReadItemsFromJson {
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(com.epam.streamdp.four.actions.SaveReadItemsFromJson.class.getName());

    private SaveReadItemsFromJson() {
        throw new IllegalStateException("SaveReadItemsFromJson");
    }

    public static void saveItemsToFile(List<NameFromJson> stringsList, String filename) {
        Gson gson = new Gson();
        String json = gson.toJson(stringsList);
        try (FileWriter writer = new FileWriter("data//" + filename)) {
            writer.write(json);
            logger.log(Level.INFO, "File wried successfully to data/{0}", filename);
        } catch (IOException ex) {
            logger.log(Level.SEVERE, "Exception: ", ex);
        }
    }

    public static List<NameFromJson> loadItemsFromFile(String filename) {
        Gson gson = new Gson();
        List<NameFromJson> valuesList = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader("data//" + filename))) {
            valuesList = gson.fromJson(reader, new TypeToken<List<NameFromJson>>() {
            }.getType());
        } catch (IOException ex) {
            logger.log(Level.SEVERE, "Exception: ", ex);
        }
        return valuesList;
    }
}
