package com.api.bookstore.dtos;

import com.api.bookstore.entities.Book;

public class BookDTO {
    private Long id;
    private String title;
    private String author;
    private String description;
    private String publisher;
    private String isbn;
    private Integer pages;
    private String category;

    public BookDTO(Book book) {
        id = book.getId();
        title = book.getTitle();
        author = book.getAuthor();
        description = book.getDescription();
        publisher = book.getPublisher();
        isbn = book.getIsbn();
        pages = book.getPages();
        category = book.getCategory();
    }

    public BookDTO(Long id, String title, String author, String description, String publisher, String isbn, Integer pages, String category) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.description = description;
        this.publisher = publisher;
        this.isbn = isbn;
        this.pages = pages;
        this.category = category;
    }

    public BookDTO(String title, String author, String description, String publisher, String isbn, Integer pages, String category) {
        this.title = title;
        this.author = author;
        this.description = description;
        this.publisher = publisher;
        this.isbn = isbn;
        this.pages = pages;
        this.category = category;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public Integer getPages() {
        return pages;
    }

    public void setPages(Integer pages) {
        this.pages = pages;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
