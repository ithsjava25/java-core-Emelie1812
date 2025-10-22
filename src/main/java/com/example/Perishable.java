package com.example;

import java.time.LocalDate;

/**
 * Interface for products that have an expiration date.
 */

public interface Perishable {

    /**
     * Returns the expiration date of the product.
     */
    LocalDate expirationDate();

    /**
     * Checks if the product is expired on today's date.
     */
    default boolean isExpired() {
        return expirationDate().isBefore(LocalDate.now());
    }
}
