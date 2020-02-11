package com.epam.streamdp.five;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.logging.Level;
import java.util.stream.Collectors;

public class Actions {
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(Actions.class.getName());
    private static final String EXCEPTION_MESSAGE = "Exception: ";

    public String makeTabs(int tabs) {
        StringBuilder tabsString = new StringBuilder("|");
        for (int i = 0; i < tabs; i++) {
            tabsString.append('\t');
        }
        return tabsString.toString();
    }

    public String makeDirSeparators(int count) {
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
        } catch (IOException ex) {
            logger.log(Level.SEVERE, EXCEPTION_MESSAGE, ex);
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
            bufferedWriter.write(directoryName + '\n');
            for (ListFiles file : listFiles.stream().filter(listFile -> !listFile.getPath().equals(initialDir)).collect(Collectors.toList())) {
                if (!file.getDirectoryName().equals(directoryName)) {
                    bufferedWriter.write("|\n");
                    bufferedWriter.write("|" + makeDirSeparators(file.getPath().getNameCount() - initialDir.getNameCount()) + file.getDirectoryName() + '\n');
                    directoryName = file.getDirectoryName();
                }
                bufferedWriter.write(makeTabs(file.getPath().getNameCount() - initialDir.getNameCount()) + file.getName() + '\n');
            }
            bufferedWriter.write("|\n");
            for (ListFiles file : listFiles.stream().filter(listFile -> listFile.getPath().equals(initialDir)).collect(Collectors.toList())) {
                bufferedWriter.write(file.getName() + '\n');
            }
        } catch (IOException | NoSuchElementException ex) {
            logger.log(Level.SEVERE, EXCEPTION_MESSAGE, ex);
        }
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
        return !string.equals("|") && string.length() > 0 && string.toCharArray()[0] == '|' && string.toCharArray()[1] == '-';
    }

    public boolean isStringContainedFiles(String string) {
        return (!string.equals("|") && string.length() > 0 && string.toCharArray()[0] == '|' &&
                string.toCharArray()[1] == '\t') || (string.length() > 0 && string.toCharArray()[0] != '|');
    }

    public List<String> getAnswersForPartTwoMainTask(Path path) {
        List<String> stringList = new ArrayList<>();
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(path.toString()))) {
            int countDirictories = 0;
            int countFiles = 0;
            int sumOfFileNameLength = 0;
            bufferedReader.readLine();
            countDirictories++;
            while (bufferedReader.ready()) {
                String fileName = bufferedReader.readLine();
                if (!fileName.equals("|") && isStringContainedFiles(fileName)) {
                    countFiles++;
                    sumOfFileNameLength += getFileNameFromString(fileName).toCharArray().length;
                } else if (!fileName.equals("|") && isStringContainedDirectory(fileName)) {
                    countDirictories++;
                }
            }
            stringList.add("Number of directories: " + countDirictories);
            stringList.add("Number of files: " + countFiles);
            stringList.add("Average number files in directories: " + countFiles / countDirictories);
            stringList.add("Average length of file names " + sumOfFileNameLength / countFiles);
        } catch (IOException ex) {
            logger.log(Level.SEVERE, EXCEPTION_MESSAGE, ex);
        }
        return stringList;
    }
}
