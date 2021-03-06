package com.epam.streamdp.three.maintask;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;

import static com.epam.streamdp.three.actions.LoggingConfig.loadAndApplyLoggingConfig;

public class OptionalTaskOne {
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(OptionalTaskOne.class.getName());

    public static void main(String[] args) {
        loadAndApplyLoggingConfig();

        List<String> stringList = new ArrayList<>();
        String filename = "data" + File.separator + "optionaltask";
        readFile(stringList, filename);
        Collections.reverse(stringList);
        writeFile(stringList, filename);
        logger.log(Level.INFO, "Revers done! Please, check file.");
    }

    private static void readFile(List<String> stringList, String filename) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            while (reader.ready()) {
                stringList.add(reader.readLine());
            }
        } catch (IOException ex) {
            logger.log(Level.SEVERE, "Exception: ", ex);
        }
    }

    private static void writeFile(List<String> stringList, String filename) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filename))) {
            for (String string : stringList) {
                writer.write(string + '\n');
            }
            writer.flush();
        } catch (IOException ex) {
            logger.log(Level.SEVERE, "Exception: ", ex);
        }
    }
}

