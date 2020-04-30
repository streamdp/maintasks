package com.epam.streamdp.ten.framework.service;

import com.epam.streamdp.ten.framework.model.Document;

import java.util.Random;

public class DocumentFactory {
    private static final String DOCUMENT_BASE_NAME = "Document";
    private static final String DOCUMENT_BASE_TEXT = "Hello world!";
    private static Random random = new Random();

    private DocumentFactory() {
        throw new IllegalStateException("Service class");
    }

    public static Document withRandomDocumentName() {
        return new Document(DOCUMENT_BASE_NAME + random.nextInt(1000), DOCUMENT_BASE_TEXT);
    }
}