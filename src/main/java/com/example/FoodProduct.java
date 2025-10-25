package com.example;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.UUID;

/**
 * Represents a food product with expiration date and weight.
 * Implements behavior for perishability and shipping cost calculation.
 */

public class FoodProduct extends Product implements Perishable, Shippable {
    private final LocalDate expirationDate;
    private final BigDecimal weight;

    /**
     * Creates a new FoodProduct with required validation.
     *
     * @param id                Unique product ID
     * @param name              Name of the product
     * @param category          Category the product belongs to
     * @param price             Price of the product (must not be negative)
     * @param expirationDate    Expiration Date
     * @param weight            Weight in kilograms (must not be negative)
     */
    public FoodProduct(UUID id, String name, Category category, BigDecimal price, LocalDate expirationDate, BigDecimal weight) {
        super(id, name, category, price);

        if (price.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Price cannot be negative.");
        }

        if (weight.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("Weight cannot be negative.");
        }

        this.expirationDate = expirationDate;
        this.weight = weight;
    }

    /**
     * Returns the expiration date of the product.
     */
    @Override
    public LocalDate expirationDate() {
        return expirationDate;
    }

    /**
     * Returns the weight of the product in double format.
     */
    @Override
    public double weight() {
        return weight.doubleValue();
    }

    /**
     * Calculates the shipping cost based on weight
     * Rule: Cost = weight * 50
     */
    @Override
    public BigDecimal calculateShippingCost() {
        return weight.multiply(BigDecimal.valueOf(50));
    }

    /**
     * Returns a description of the product.
     * Example: "Food: Milk, Expires: 2025-12-24"
     */
    @Override
    public String productDetails() {
        return "Food: " + name() + ", Expires: " + expirationDate;
    }
}
