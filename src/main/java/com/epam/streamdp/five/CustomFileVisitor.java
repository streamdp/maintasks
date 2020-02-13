package com.epam.streamdp.five;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Path;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

class CustomFileVisitor extends SimpleFileVisitor<Path> {
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(CustomFileVisitor.class.getName());

    private List<ListFiles> listFilesWithPath = new ArrayList<>();

    public List<ListFiles> getListFilesWithPath() {
        return listFilesWithPath;
    }

    @Override
    public FileVisitResult visitFileFailed(Path file, IOException e) {
        logger.log(Level.INFO, "Visiting failed for {0}", file);
        return FileVisitResult.SKIP_SUBTREE;
    }

    @Override
    public FileVisitResult visitFile(Path path, BasicFileAttributes fileAttributes) {
        listFilesWithPath.add(new ListFiles(path.getName(path.getNameCount() - 2).toString(), path.getParent(), path.getFileName().toString()));
        return FileVisitResult.CONTINUE;
    }
}
