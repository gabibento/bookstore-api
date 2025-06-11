package com.api.bookstore.services;

import com.api.bookstore.entities.Book;
import com.api.bookstore.repositories.BookRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {

    private final BookRepository bookRepository;
    private final BookIntegrationService bookIntegrationService;

    public BookService(BookRepository bookRepository, BookIntegrationService bookIntegrationService) {
        this.bookRepository = bookRepository;
        this.bookIntegrationService = bookIntegrationService;
    }

    public ResponseEntity<Book> createBook(String isbn) {
        Book book = bookIntegrationService.getBookByIsbn(isbn);
        if(book == null) return ResponseEntity.notFound().build();

        bookRepository.save(book);
        return ResponseEntity.ok(book);
    }
    public List<Book> searchBooks(String title, String author){
        return bookIntegrationService.searchBooks(title, author);
    }

    public List<Book> findAll() {
        return bookRepository.findAll();
    }


}
