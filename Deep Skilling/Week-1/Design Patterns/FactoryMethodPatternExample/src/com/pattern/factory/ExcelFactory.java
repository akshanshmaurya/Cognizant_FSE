package com.pattern.factory;

public class ExcelFactory extends DocumentFactory {
    @Override
    public Document createDocument() { return new ExcelDocument(); }
}
