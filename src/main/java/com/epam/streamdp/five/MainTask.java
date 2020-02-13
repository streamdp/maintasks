package com.epam.streamdp.five;

import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Scanner;
import java.util.logging.Level;

import static com.epam.streamdp.five.Actions.*;
import static com.epam.streamdp.three.actions.LoggingConfig.loadAndApplyLoggingConfig;

public class MainTask {
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(MainTask.class.getName());

    public static void main(String[] args) {
        loadAndApplyLoggingConfig();
        logger.log(Level.INFO, "Please enter the path to the directory:");
        Path path = args.length > 0 ? Paths.get(Arrays.toString(args)) : Paths.get(new Scanner(System.in).next());
        logger.log(Level.INFO, "Path: {0}", path);
        if (path.toFile().exists()) {
            if (path.toFile().isDirectory()) {
                logger.log(Level.INFO, "A list of directories and files will be saved to a file data/fileTree.txt");
                new Actions().writeDirectoryListInFile(path);
                logger.log(Level.INFO, "File saves successfully!");
            } else if (path.toFile().isFile()) {
                int[] arrayWithAnswers = new Actions().getAnswersForPartTwoMainTask(path);
                logger.log(Level.INFO, "Number of directories: {0}", arrayWithAnswers[NUMBER_OF_DIRECTORIES]);
                logger.log(Level.INFO, "Number of files: {0}", arrayWithAnswers[NUMBER_OF_FILES]);
                logger.log(Level.INFO, "Average number files in directories: {0}", arrayWithAnswers[AVERAGE_NUMBER_FILES]);
                logger.log(Level.INFO, "Average length of file names {0}", arrayWithAnswers[AVERAGE_LENGTH_FILE_NAMES]);
            }
        }
    }
}