package com.pattern.factory;

public class PdfFactory extends DocumentFactory {
    @Override
    public Document createDocument() { return new PdfDocument(); }
}
