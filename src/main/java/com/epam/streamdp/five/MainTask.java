package com.epam.streamdp.five;


import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.logging.Level;

import static com.epam.streamdp.three.actions.LoggingConfig.loadAndApplyLoggingConfig;


public class MainTask {
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(MainTask.class.getName());

    public static void main(String[] args) {
        loadAndApplyLoggingConfig();
        Path path = args.length > 0 ? Paths.get(Arrays.toString(args)) : Paths.get(new Scanner(System.in).next());
        if (path.toFile().exists()) {
            if (path.toFile().isDirectory()) {
                logger.log(Level.INFO, "A list of directories and files will be saved to a file data/fileTree.txt");
                new Actions().writeDirectoryListInFile(path);
                logger.log(Level.INFO, "File saveв successfully!");
            } else if (path.toFile().isFile()) {
//                Количество папок
//                Количество файлов
//                Среднее количество файлов в папке
//                Среднюю длинну названия файла

                List<String> filesAndDirectories = new ArrayList<>();


//                for (File file : path.getParent().toFile().listFiles()) {
//
//                }
            }
        }
        Path pathSource = Paths.get("/home/streamdp/workspace/maintask/data/");

    }
}