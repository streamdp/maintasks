package com.epam.streamdp.five;

public class DirectoryOrFile {
    private boolean isDirectory = false;
    private String name = null;

    public DirectoryOrFile(boolean isDirectory, String name) {
        this.isDirectory = isDirectory;
        this.name = name;
    }

    public DirectoryOrFile() {
    }

    public boolean isDirectory() {
        return isDirectory;
    }

    public void setDirectory(boolean directory) {
        isDirectory = directory;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
