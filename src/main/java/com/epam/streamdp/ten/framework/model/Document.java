package com.epam.streamdp.ten.framework.model;

public class Document {
    private String documentName;
    private String stringWithText;

    public Document(String documentName, String stringWithText) {
        this.documentName = documentName;
        this.stringWithText = stringWithText;
    }

    public String getDocumentName() {
        return documentName;
    }

    public String getStringWithText() {
        return stringWithText;
    }
}
