package com.epam.streamdp.five;

import java.nio.file.Path;

public class ListDirectories {
    private String directoryName = null;
    private Path path = null;

    public ListDirectories(String directoryName, Path path) {
        this.directoryName = directoryName;
        this.path = path;
    }

    public ListDirectories() {

    }

    public String getDirectoryName() {
        return directoryName;
    }

    public Path getPath() {
        return path;
    }
}
