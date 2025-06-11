package com.api.bookstore.controllers;

import com.api.bookstore.entities.Book;
import com.api.bookstore.services.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/books")
public class BookController {
    @Autowired
    BookService bookService;

    @PostMapping
    public ResponseEntity<Book> createBook(@RequestParam String isbn){
        return bookService.createBook(isbn);
    }

    @GetMapping("/search")
    public List<Book> searchBooks(@RequestParam String title, @RequestParam String author){
        return bookService.searchBooks(title, author);
    }
    @GetMapping
    public List<Book> findAll(){
        return bookService.findAll();
    }
}
