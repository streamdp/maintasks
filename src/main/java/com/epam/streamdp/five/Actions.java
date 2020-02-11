package com.epam.streamdp.five;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.logging.Level;
import java.util.stream.Collectors;

public class Actions {
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(Actions.class.getName());

    public static String makeTabs(int tabs) {
        StringBuilder tabsString = new StringBuilder("|");
        for (int i = 0; i < tabs; i++) {
            tabsString.append('\t');
        }
        return tabsString.toString();
    }

    public static String makeDirseparators(int count) {
        StringBuilder dirSeparators = new StringBuilder();
        for (int i = 0; i < count; i++) {
            dirSeparators.append("--\t");
        }
        return dirSeparators.toString();
    }

    public List<ListFiles> getSortedListFilesFromPath(Path path) {
        try {
            CustomFileVisitor fileVisitor = new CustomFileVisitor();
            Files.walkFileTree(path, fileVisitor);
            List<ListFiles> listFiles = fileVisitor.getListFilesWithPath();
            Collections.sort(listFiles, Comparator.comparing(ListFiles::getPath));
            return listFiles;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    public void writeDirectoryListInFile(Path path) {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter("data" + File.separator + "fileTree.txt"))) {
            List<ListFiles> listFiles = getSortedListFilesFromPath(path);
            if (listFiles.isEmpty()) {
                throw new NoSuchElementException("There are no items in the set.");
            }
            Path initialDir = listFiles.get(0).getPath();
            String directoryName = initialDir.getName(initialDir.getNameCount() - 1).toString();

            bufferedWriter.write(directoryName + 'n');
            bufferedWriter.write("|\n");
            for (ListFiles file : listFiles.stream().filter(listFile -> !listFile.getPath().equals(initialDir)).collect(Collectors.toList())) {
                if (!file.getDirectoryName().equals(directoryName)) {
                    bufferedWriter.write("|" + makeDirseparators(file.getPath().getNameCount() - initialDir.getNameCount()) + file.getDirectoryName() + '\n');
                    directoryName = file.getDirectoryName();
                }
                bufferedWriter.write(makeTabs(file.getPath().getNameCount() - initialDir.getNameCount()) + file.getName() + '\n');
            }
            bufferedWriter.write("|\n");
            for (ListFiles file : listFiles.stream().filter(listFile -> listFile.getPath().equals(initialDir)).collect(Collectors.toList())) {
                bufferedWriter.write(file.getName() + '\n');
            }
        } catch (IOException | NoSuchElementException ex) {
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
        return (countFilesInInitialDitictory + countFilesInOtherDirectory) / countDirictories;
    }

}
