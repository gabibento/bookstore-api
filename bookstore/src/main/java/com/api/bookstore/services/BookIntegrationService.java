package com.api.bookstore.services;

import com.api.bookstore.dtos.book.BookInfoResponse;
import com.api.bookstore.dtos.book.IndustryIdentifier;
import com.api.bookstore.dtos.book.VolumeInfo;
import com.api.bookstore.entities.Book;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.Optional;

@Service
public class BookIntegrationService {

    private final WebClient webClient;

    public BookIntegrationService(WebClient.Builder builder) {
        this.webClient = builder.baseUrl("https://www.googleapis.com/books/v1").build();
    }
    private VolumeInfo fetchBookInfo(String query) {
        return webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/volumes")
                        .queryParam("q", query)
                        .build())
                .retrieve()
                .bodyToMono(BookInfoResponse.class)
                .map(response -> {
                    if (response.getItems() != null && !response.getItems().isEmpty()) {
                        return response.getItems().get(0).getVolumeInfo();
                    }
                    return null;
                })
                .block();
    }


    public List<Book> searchBooks(String title, String author) {
        String query = "intitle:" + title + "+inauthor:" + author;

        BookInfoResponse response = webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .path("/volumes")
                        .queryParam("q", query)
                        .build())
                .retrieve()
                .bodyToMono(BookInfoResponse.class)
                .block();

        if (response == null || response.getItems() == null) {
            return List.of();
        }

        return response.getItems().stream()
                .map(bookItem -> {
                    VolumeInfo info = bookItem.getVolumeInfo();
                   return createBookObject(info, getIsbn13(info)).orElse(null);
                })
                .toList();
    }

    private String getIsbn13(VolumeInfo info) {
        return info.getIndustryIdentifiers().stream()
                .filter(industryIdentifier -> industryIdentifier.getType().equals("ISBN_13"))
                .map(IndustryIdentifier::getIdentifier)
                .findFirst()
                .orElse(null);
    }
    public Book getBookByIsbn(String isbn){
        String query = "isbn:" + isbn;

        VolumeInfo info = fetchBookInfo(query);

        if (info == null) return null;

        return createBookObject(info, isbn).orElse(null);
    }
    private Optional<Book> createBookObject(VolumeInfo info, String isbn){
        Book book = new Book();
        book.setTitle(info.getTitle());
        book.setAuthor(info.getAuthors() != null ? String.join(", ", info.getAuthors()) : "Unknown");
        book.setPublisher(info.getPublisher());
        book.setDescription(info.getDescription());
        book.setPages(info.getPageCount());
        book.setIsbn(isbn);
        List<String> categories = info.getCategories();
        book.setCategory(categories != null && !categories.isEmpty() ? categories.get(0) : "Default");

        return Optional.of(book);
    }
}