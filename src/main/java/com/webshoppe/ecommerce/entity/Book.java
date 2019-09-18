package com.webshoppe.ecommerce.entity;

import java.math.BigDecimal;

public class Book {
    private String id;
    private String name;
    private String description;
    private BigDecimal price;
    private String author;

    public Book() {
    }

    public Book(String id, String name, String description, BigDecimal price, String author) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.author = author;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getAuthor() {
        return author;
    }
}
