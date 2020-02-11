package com.epam.streamdp.five;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;

class CustomFileVisitor extends SimpleFileVisitor<Path> {
    private int tabs = -1;
    private int dirSeparate = -1;
    private List<String> listOfFileNamesFromInitialDirectory = new ArrayList<>();
    private List<String> listOfOtherDirectoriesWithFiles = new ArrayList<>();
    private Path initialPath = null;
    private int initialDirCout = -1;

    public List<String> getListOfFileNamesFromInitialDirectory() {
        return listOfFileNamesFromInitialDirectory;
    }

    public List<String> getListOfOtherDirectoriesWithFiles() {
        return listOfOtherDirectoriesWithFiles;
    }

    public String makeTabs(int tabs) {
        String tabsString = "|";
        for (int i = 0; i < tabs; i++) {
            tabsString = tabsString + "\t";
        }
        return tabsString;
    }

    @Override
    public FileVisitResult visitFile(Path path, BasicFileAttributes fileAttributes) {
        if (initialPath.equals(path.getParent())) {
            listOfFileNamesFromInitialDirectory.add(path.getFileName().toString());
        } else {
            listOfOtherDirectoriesWithFiles.add(makeTabs(tabs) + path.getFileName());
        }
        return FileVisitResult.CONTINUE;
    }

    @Override
    public FileVisitResult postVisitDirectory(Path dir, IOException exc)
            throws IOException {
        tabs--;
        if (exc != null)
            throw exc;
        return FileVisitResult.CONTINUE;
    }

    @Override
    public FileVisitResult preVisitDirectory(Path path, BasicFileAttributes fileAttributes) {
        String dirSepatator = "";
        if (initialPath == null) {
            initialDirCout = path.getNameCount();
            initialPath = path;
            tabs++;
            return FileVisitResult.CONTINUE;
        }
        listOfOtherDirectoriesWithFiles.add("|");
        for (int i = 0; i < path.getNameCount() - initialDirCout; i++) {
            dirSepatator = dirSepatator + "--\t";
        }
        listOfOtherDirectoriesWithFiles.add("|" + dirSepatator + path.getName(path.getNameCount() - 1));
        tabs++;
        return FileVisitResult.CONTINUE;
    }
}
