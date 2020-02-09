package com.epam.streamdp.three.actions;

import com.epam.streamdp.three.entity.Minerals;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

public class SaveReadItemsFromJson {
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(SaveReadItemsFromJson.class.getName());

    private SaveReadItemsFromJson() {
        throw new IllegalStateException("SaveReadItemsFromJson");
    }

    public static void saveItemsToFile(Minerals minerals, String filename){
        Gson gson = new Gson();
        String json = gson.toJson(minerals);
        try (FileWriter writer = new FileWriter("data" + File.separator + filename)) {
            writer.write(json);
            logger.log(Level.INFO, "File wried successfully to data/{0}", filename);
        } catch (IOException ex) {
            logger.log(Level.SEVERE, "Exception: ", ex);
        }
    }

    public static List<Minerals> loadItemsFromFile(String filename){
        Gson gson = new Gson();
        List<Minerals> mineralsList = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader("data" + File.separator + filename))) {
            mineralsList = gson.fromJson(reader, new TypeToken<List<Minerals>>() {
            }.getType());
        } catch (IOException ex) {
            logger.log(Level.SEVERE, "Exception: ", ex);
        }
        return mineralsList;
    }

}
