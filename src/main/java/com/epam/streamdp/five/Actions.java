package com.epam.streamdp.five;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.stream.Collectors;

public class Actions {
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(Actions.class.getName());

    public void writeDirectoryListInFile(Path path) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("data" + File.separator + "fileTree.txt"))) {
            CustomFileVisitor fileVisitor = new CustomFileVisitor();
            Files.walkFileTree(path, fileVisitor);
            bufferedWriter.write(String.valueOf(path.getName(path.getNameCount() - 1)) + '\n');
            bufferedWriter.write("|\n");
            for (String string : fileVisitor.getListOfOtherDirectoriesWithFiles()) {
                bufferedWriter.write(string + '\n');
            }
            bufferedWriter.write("|\n");
            for (String string : fileVisitor.getListOfFileNamesFromInitialDirectory()) {
                bufferedWriter.write(string + '\n');
            }
        } catch (IOException ex) {
            logger.log(Level.SEVERE, "Exception: ", ex);
        }
    }

    public String getDirectoryNameFromString(String string) {
        int endPositionSeparator = 0;
        for (int i = 0; i < string.toCharArray().length; i++) {
            if (string.toCharArray()[i] == '\t') {
                endPositionSeparator = i;
            }
        }
        return string.substring(endPositionSeparator + 1);
    }

    public String getFileNameFromString(String string) {
        int endPositionSeparator = 0;
        for (int i = 0; i < string.toCharArray().length; i++) {
            if (string.toCharArray()[i] == '\t') {
                endPositionSeparator = i;
            }
        }
        return string.substring(endPositionSeparator + 1);
    }

    public boolean isStringContainedDirectory(String string) {
        return string.toCharArray()[0] == '|' && string.toCharArray()[1] == '-';
    }

    public boolean isStringContainedFiles(String string) {
        return string.toCharArray()[0] != '|' || string.toCharArray()[1] == '\t';
    }

    public int getAverageCountFilesInDirectories(BufferedReader bufferedReader) {
        int countFilesInInitialDitictory = 0;
        int countFilesInOtherDirectory = 0;
        int countDirictories = 0;
        double averageFilesInDirectoryes = 0;
        for (String string : bufferedReader.lines().collect(Collectors.toList())) {
            if (string.toCharArray()[0] == '|' && string.toCharArray()[1] == '-') {
                countDirictories++;
            } else if (string.toCharArray()[0] != '|' || string.toCharArray()[1] == '\t') {
                countFilesInOtherDirectory++;
            }else{
                countFilesInInitialDitictory++;
            }
        }
    }

    public List<DirectoryOrFile> parseFileAndReturnlListOfStringWithDirectories(Path path) {
        List<DirectoryOrFile> listWithResult = new ArrayList<>();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader("data" + File.separator + "fileTree.txt"))) {
            bufferedReader.lines().forEach(string -> {
                if (isStringContainedDirectory(string)) {
                    listWithResult.add(new DirectoryOrFile(true, getDirectoryNameFromString(string)));
                } else if (isStringContainedFiles(string)) {
                    listWithResult.add(new DirectoryOrFile(false, getFileNameFromString(string)));
                }
            });
        } catch (IOException ex) {
            logger.log(Level.SEVERE, "Exception: ", ex);
        }
        return listWithResult;
    }
}
