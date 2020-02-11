package com.epam.streamdp.five;

import java.nio.file.Path;

public class ListFiles extends ListDirectories {
    private String name = null;

    public ListFiles(String name, Path path) {
        super();
        this.name = name;
    }

    public ListFiles(String nameDirectory, Path path, String name) {
        super(nameDirectory, path);
        this.name = name;
    }

    public ListFiles() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
