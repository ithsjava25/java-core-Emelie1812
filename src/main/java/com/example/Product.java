package com.example;

import java.math.BigDecimal;
import java.util.UUID;

public abstract class Product {
    private final UUID id;
    private final String name;
    private final Category category;
    private BigDecimal price;

    public Product(UUID id, String name, Category category, BigDecimal price) {
        if (id == null) throw new IllegalArgumentException("UUID can't be null");
        if (name == null || name.isBlank()) throw new IllegalArgumentException("Name can't be null or blank");
        if (category == null) throw new IllegalArgumentException("Category can't be null");
        if (price == null || price.compareTo(BigDecimal.ZERO) < 0) throw new IllegalArgumentException("Price can't be null or negative");

        this.id = id;
        this.name = name;
        this.category = category;
        this.price = price;
    }

    public String name() {
        return name;
    }

    public Category category() {
        return category;
    }

    public BigDecimal price() {
        return price;
    }

    public void price(BigDecimal price) {
        if (price == null || price.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Price can't be null or negative");
        }
        this.price = price;
    }

    // Abstract method that subclasses must implement
    public abstract String productDetails();
}
