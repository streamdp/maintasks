package com.epam.streamdp.five;

import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;

class CustomFileVisitor extends SimpleFileVisitor<Path> {
    private List<ListFiles> listFilesWithPath = new ArrayList<>();

    public List<ListFiles> getListFilesWithPath() {
        return listFilesWithPath;
    }

    @Override
    public FileVisitResult visitFile(Path path, BasicFileAttributes fileAttributes) {
        listFilesWithPath.add(new ListFiles(path.getName(path.getNameCount() - 2).toString(), path.getParent(), path.getFileName().toString()));
        return FileVisitResult.CONTINUE;
    }
}
