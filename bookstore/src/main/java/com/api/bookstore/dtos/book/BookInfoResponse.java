package com.api.bookstore.dtos.book;

import java.util.List;

public class BookInfoResponse {
    private List<BookItem> items;

    public List<BookItem> getItems() {
        return items;
    }

    public void setItems(List<BookItem> items) {
        this.items = items;
    }
}

