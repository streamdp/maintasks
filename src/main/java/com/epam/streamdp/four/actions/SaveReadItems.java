package com.epam.streamdp.four.actions;

import com.epam.streamdp.four.entity.StatementOfGrades;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.stream.Collectors;

public class SaveReadItems {
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(SaveReadItems.class.getName());
    private static final String EXCEPTION_SEVERE_LEVEL_MESSAGE = "Exception: ";
    private static final String INITIAL_DIRECTORY = "data" + File.separator;

    private SaveReadItems() {
        throw new IllegalStateException("SaveReadItemsFromJson");
    }

    public static List<StatementOfGrades> loadTestItemsFromFile(String filename) {
        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(new File(INITIAL_DIRECTORY + filename)))) {
            return (List<StatementOfGrades>) inputStream.readObject();
        } catch (IOException | ClassNotFoundException ex) {
            logger.log(Level.SEVERE, EXCEPTION_SEVERE_LEVEL_MESSAGE, ex);
        }
        return new ArrayList<>();
    }

    public static void saveItemsToFile(List<StatementOfGrades> statementOfGrades, String filename) {
        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(new File(INITIAL_DIRECTORY + filename)))) {
            outputStream.writeObject(statementOfGrades);
        } catch (IOException ex) {
            logger.log(Level.SEVERE, EXCEPTION_SEVERE_LEVEL_MESSAGE, ex);
        }
    }

    public static List<String> loadItemsFromFile(String filename) {
        List<String> valuesList = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(INITIAL_DIRECTORY + filename))) {
            valuesList = reader.lines().collect(Collectors.toList());
        } catch (IOException ex) {
            logger.log(Level.SEVERE, EXCEPTION_SEVERE_LEVEL_MESSAGE, ex);
        }
        return valuesList;
    }
}
