package com.epam.streamdp.ten.framework.service;

import com.epam.streamdp.ten.framework.model.Folder;

import java.util.Random;

public class FolderFactory {
    private static final String FOLDER_BASE_NAME = "Folder";
    private static Random random = new Random();

    private FolderFactory() {
        throw new IllegalStateException("Service class");
    }

    public static Folder withRandomFolderName() {
        return new Folder(FOLDER_BASE_NAME + random.nextInt(1000));
    }
}