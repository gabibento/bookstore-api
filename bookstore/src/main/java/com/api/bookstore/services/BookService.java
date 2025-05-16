package com.api.bookstore.services;

import com.api.bookstore.dtos.book.BookCreationDTO;
import com.api.bookstore.entities.Book;
import com.api.bookstore.repositories.BookRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.Optional;

@Service
public class BookService {

    private final BookRepository bookRepository;
    private final BookIntegrationService bookIntegrationService;

    public BookService(BookRepository bookRepository, BookIntegrationService bookIntegrationService) {
        this.bookRepository = bookRepository;
        this.bookIntegrationService = bookIntegrationService;
    }

    public ResponseEntity<Book> createBook(String title, String author, BookCreationDTO bookCreationDTO) {
        Book book = fetchBook(title, author);
        if(book == null) return ResponseEntity.notFound().build();

        book.setPrice(bookCreationDTO.getPrice());
        book.setStockQuantity(bookCreationDTO.getStockQuantity());
        bookRepository.save(book);
        return ResponseEntity.ok(book);
    }
    public Book fetchBook(String title, String author){
        return bookIntegrationService.getBook(title, author).orElse(null);
    }

    public List<Book> findAll() {
        return bookRepository.findAll();
    }


}
