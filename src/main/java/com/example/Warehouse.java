package com.example;

import java.math.BigDecimal;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

/**
 * Warehouse singleton class to manage products
 * Singleton instances are unique per warehouse name
 */

public class Warehouse {
    // Map to hold singleton instances per warehouse name
    private static final Map<String, Warehouse> INSTANCES = new ConcurrentHashMap<>();

    public List<Perishable> perishables() {
        return products.values().stream().filter(p -> p instanceof Perishable).map(p -> (Perishable) p)
                .collect(Collectors.toList());
    }

    // Name of the warehouse instance
    private final String name;

    // Internal storage for products by UUID
    private final Map<UUID, Product> products = new HashMap<>();

    // Set to track products with updated prices
    private final Set<Product> changedProducts = new HashSet<>();

    // Private constructor for singleton pattern
    private Warehouse(String name) {
        this.name = name;
    }

    /**
     * Returns singleton Warehouse instance for the given name.
     */
    public static Warehouse getInstance(String name) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Warehouse name cannot be null or blank.");
        }
        return INSTANCES.computeIfAbsent(name.trim(), Warehouse::new);
    }

    /**
     * Adds a product to the warehouse.
     * Throws IllegalArgumentException if product is null
     */
    public void addProduct(Product product) {
        if (product == null) {
            throw new IllegalArgumentException("Product cannot be null.");
        }
        products.put(product.uuid(), product);
    }

    public List<Product> getProducts() {
        return Collections.unmodifiableList(new ArrayList<>(products.values()));
    }

    /**
     * Returns all products that are perishable.
     */
    public List<Perishable> getPerishableProducts() {
        return products.values().stream().filter(p -> p instanceof Perishable)
                .map(p -> (Perishable) p).collect(Collectors.toList());
    }

    /**
     * Returns an optional of the product matching the given UUID
     */
    public Optional<Product> getProductById(UUID id){
        return Optional.ofNullable(products.get(id));
    }
    /**
     * Updates the price of a product identified by UUID
     * Throws NoSuchElementException if product not found.
     * Tracks the product as changed
     */
    public void updateProductPrice(UUID id, BigDecimal newPrice) {
        Product product = products.get(id);
        if (product == null) {
            throw new NoSuchElementException("Product not found with id: " + id);
        }
        product.price(newPrice);
        changedProducts.add(product);
    }

    /**
     * Returns a list of products whose prices have been changed
     */
    public List<Product> getChangedProducts() {
        return Collections.unmodifiableList(new ArrayList<>(changedProducts));
    }

    /**
     * Returns a list of expired products.
     * Only products implementing Perishable are considered
     */
    public List<Perishable> expiredProducts() {
        return products.values().stream().filter(p -> p instanceof Perishable)
                .map(p -> (Perishable) p).filter(Perishable::isExpired).collect(Collectors.toList());
    }

    /**
     * Returns a list of shippable products
     * Only products implementing Shippable are considered
     */
    public List<Shippable> shippableProducts() {
        return products.values().stream().filter(p -> p instanceof Shippable).map(p -> (Shippable) p).collect(Collectors.toList());
    }

    /**
     * Removes a product by UUID if it exists
     */
    public void remove(UUID id) {
        products.remove(id);
    }

    /**
     * Returns the name of the warehouse
     */
    public String getName() {
        return name;
    }

    /**
     * Removes all products from the warehouse.
     * Also clears the list of changed products.
     */
    public void clearProducts() {
        products.clear();
        changedProducts.clear();
    }

    public boolean isEmpty() {
        return products.isEmpty();
    }

    public Map<Category, List<Product>> getProductsGroupedByCategories() {
        return products.values().stream().collect(Collectors.groupingBy(Product::category));
    }

}
