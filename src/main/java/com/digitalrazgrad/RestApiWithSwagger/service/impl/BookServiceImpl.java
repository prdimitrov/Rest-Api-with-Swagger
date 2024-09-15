package com.digitalrazgrad.RestApiWithSwagger.service.impl;

import com.digitalrazgrad.RestApiWithSwagger.model.entity.Book;
import com.digitalrazgrad.RestApiWithSwagger.repository.BookRepository;
import com.digitalrazgrad.RestApiWithSwagger.service.BookService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
public class BookServiceImpl implements BookService {

    private final ModelMapper modelMapper;
    private final BookRepository bookRepository;

    public BookServiceImpl(ModelMapper modelMapper, BookRepository bookRepository) {
        this.modelMapper = modelMapper;
        this.bookRepository = bookRepository;
    }

    @Override
    public Set<Book> getAllBooks() {
        return bookRepository.findAll()
                .stream()
                .map(book -> modelMapper.map(book, Book.class))
                .collect(Collectors.toSet());
    }

    @Override
    public Book addBook(Book book) {
        Book newBook = modelMapper.map(book, Book.class);
        bookRepository.saveAndFlush(book);
        return newBook;
    }

    @Override
    public Book updateBook(long bookId, Book book) {
        return bookRepository.findById(bookId).map(existingBook -> {
            book.setId(bookId);
            modelMapper.map(book, existingBook);
            return bookRepository.saveAndFlush(existingBook);
        }).orElseThrow(() -> new RuntimeException("Book with id \\'" + bookId + "\\' was not found!"));
    }

    @Override
    public void deleteBook(long bookId) {
        if (!bookRepository.existsById(bookId)) {
            throw new RuntimeException("Book with id \\'" + bookId + "\\' was not found!");
        }
        bookRepository.deleteById(bookId);
    }

}
