package com.library.service;

import com.library.repository.BookRepository;

public class BookService {
    private BookRepository bookRepository;

    // Setter Injection
    public void setBookRepository(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    public void printBooks() {
        System.out.println("Library Book Collection:");
        bookRepository.getBooks().forEach(b -> System.out.println(" - " + b));
    }
}
