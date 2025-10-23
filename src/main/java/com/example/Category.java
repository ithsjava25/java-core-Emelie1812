package com.example;

import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;

public final class Category {
    private static final Map<String, Category> cache = new ConcurrentHashMap<>();

    private final String name;

    private Category(String name) {
        this.name = name;
    }

    public String getName(){
        return name;
    }

    public static Category of(String name) {
        if (name == null) {
            throw new IllegalArgumentException("Category name can't be null");
        }

        String trimmed = name.trim();
        if (trimmed.isEmpty()) {
            throw new IllegalArgumentException("Category name can't be blank");
        }

        //Normalize: first letter uppercase, rest lowercase
        String normalized = trimmed.substring(0, 1).toUpperCase() + trimmed.substring(1).toLowerCase();

        // Flyweight: return some instance for same normalized name
        return cache.computeIfAbsent(normalized, Category::new);
    }

    public String name(){
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Category)) return false;
        Category category = (Category) o;
        return Objects.equals(name, category.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public String toString() {
        return "Category[" + name + "]";
    }
}

