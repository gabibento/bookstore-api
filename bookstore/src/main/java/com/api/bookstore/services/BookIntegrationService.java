package com.api.bookstore.services;

import com.api.bookstore.dtos.BookInfoResponse;
import com.api.bookstore.dtos.IndustryIdentifier;
import com.api.bookstore.dtos.VolumeInfo;
import com.api.bookstore.entities.Book;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;
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
                .map(bookInfoResponse -> bookInfoResponse.getItems().get(0).getVolumeInfo())
                .blockOptional()
                .orElse(null);
    }
    private String getIsbn13(String query) {
        return fetchBookInfo(query).getIndustryIdentifiers().stream()
                .filter(industryIdentifier -> industryIdentifier.getType().equals("ISBN_13"))
                .map(IndustryIdentifier::getIdentifier)
                .findFirst()
                .orElse(null);
    }
    public Optional<Book> getBook(String query){
        VolumeInfo info = fetchBookInfo(query);

        if (info == null ) {
            return Optional.empty();
        }
        Book book = new Book();
        book.setTitle(info.getTitle());
        book.setAuthor(info.getAuthors() != null ? String.join(", ", info.getAuthors()) : "Unknown author");
        book.setPublisher(info.getPublisher());
        book.setDescription(info.getDescription());
        book.setPages(info.getPageCount());
        book.setIsbn(getIsbn13(query));
        List<String> categories = info.getCategories();
        book.setCategory(categories != null && !categories.isEmpty() ? categories.get(0) : "Default");

        return Optional.of(book);
    }
}
