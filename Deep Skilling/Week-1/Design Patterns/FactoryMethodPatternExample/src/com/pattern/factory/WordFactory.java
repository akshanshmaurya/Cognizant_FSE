package com.pattern.factory;

public class WordFactory extends DocumentFactory {
    @Override
    public Document createDocument() { return new WordDocument(); }
}
