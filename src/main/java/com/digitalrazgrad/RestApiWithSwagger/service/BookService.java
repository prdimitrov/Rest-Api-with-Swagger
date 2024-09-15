package com.digitalrazgrad.RestApiWithSwagger.service;

import com.digitalrazgrad.RestApiWithSwagger.model.entity.Book;

import java.util.Set;

public interface BookService {
    Set<Book> getAllBooks();

    Book addBook(Book book);

    Book updateBook(long bookId, Book book);

    void deleteBook(long bookId);
}
