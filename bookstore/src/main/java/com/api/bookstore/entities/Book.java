package com.api.bookstore.entities;

import jakarta.persistence.*;


@Entity
@Table(name = "tb_book")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(length = 255)
    private String title;
    @Column(length = 255)
    private String author;
    @Lob
    @Column(columnDefinition = "TEXT")
    private String description;
    private String publisher;
    private String isbn;
    private Integer pages;
    private String category;

    public Book() {
    }

    public Book(Long id, String title, String author, String publisher, String description, String isbn, Integer pages, String category) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.publisher = publisher;
        this.description = description;
        this.isbn = isbn;
        this.pages = pages;
        this.category = category;
    }
    public Book(String title, String author, String publisher, String description, String isbn, Integer pages, String category) {
        this.title = title;
        this.author = author;
        this.publisher = publisher;
        this.description = description;
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

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
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
