package com.epam.streamdp.five;

import java.nio.file.Path;

public class ListFiles {
    private String directoryName;
    private Path path;
    private String name;

    public ListFiles(String directoryName, Path path, String name) {
        this.directoryName = directoryName;
        this.path = path;
        this.name = name;
    }

    public String getDirectoryName() {
        return directoryName;
    }

    public Path getPath() {
        return path;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
