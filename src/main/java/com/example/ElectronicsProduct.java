package com.example;

import java.math.BigDecimal;
import java.util.UUID;

/**
 * Represents an electronic product with warranty and shipping logic.
 * Implements shippable interface
 */

public class ElectronicsProduct extends Product implements Shippable {
    private final int warrantyMonths;
    private final BigDecimal weight;

    /**
     * Creates a new ElectronicsProduct
     *
     * @param id                Unique product ID
     * @param name              Name of the product
     * @param category          Product category
     * @param price             Product price
     * @param warrantyMonths    Warranty duration in months (must be non-negative)
     * @param weight            Product weight in kilograms
     */
    public ElectronicsProduct(UUID id, String name, Category category, BigDecimal price, int warrantyMonths, BigDecimal weight) {
        super(id, name, category, price);

        if (warrantyMonths < 0) {
            throw new IllegalArgumentException("Warranty months cannot be negative.");
        }

        this.warrantyMonths = warrantyMonths;
        this.weight = weight;
    }

    /**
     * Returns the warranty duration in months
     */
    public int warrantyMonths() {
        return warrantyMonths;
    }

    /**
     * Returns the weight of the product in double format
     */
    @Override
    public double weight() {
        return weight.doubleValue();
    }

    /**
     * Calculates the shipping cost
     * Rule: base cost is 79, add 49 if weight > 5.0 kg
     */
    @Override
    public BigDecimal calculateShippingCost() {
        BigDecimal base = BigDecimal.valueOf(79);
        if (weight.compareTo(BigDecimal.valueOf(5.0)) > 0) {
            return base.add(BigDecimal.valueOf(49));
        }
        return base;
    }

    /**
     * Returns a description of the product.
     * Example: "Electronics: Laptop, Warranty: 24 months"
     */
    @Override
    public String productDetails() {
        return "Electronics: " + name() + ", Warranty: " + warrantyMonths + " months";
    }
}
