package com.example;

import java.math.BigDecimal;

/**
 * Interface for products that can be shipped.
 */

public interface Shippable {

    /**
     * Returns the weight of the product in kilograms.
     */
    double weight();

    /**
     * Calculates the shipping cost of the product.
     */
    BigDecimal calculateShippingCost();
}
