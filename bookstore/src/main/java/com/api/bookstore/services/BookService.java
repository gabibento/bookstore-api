package com.api.bookstore.services;

import com.api.bookstore.entities.Book;
import com.api.bookstore.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class BookService {
    @Autowired
    BookRepository bookRepository;

    public ResponseEntity<Book> createBook(Book book){
        bookRepository.save(book);
        return ResponseEntity.ok(book);
    }
}
