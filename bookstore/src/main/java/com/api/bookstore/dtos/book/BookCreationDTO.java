package com.api.bookstore.dtos.book;

import java.math.BigDecimal;

public class BookCreationDTO {
    private BigDecimal price;
    private Integer stockQuantity;

    public BookCreationDTO(BigDecimal price, Integer stockQuantity) {
        this.price = price;
        this.stockQuantity = stockQuantity;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public Integer getStockQuantity() {
        return stockQuantity;
    }

    public void setStockQuantity(Integer stockQuantity) {
        this.stockQuantity = stockQuantity;
    }
}
