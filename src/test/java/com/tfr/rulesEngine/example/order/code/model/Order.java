package com.tfr.rulesEngine.example.order.code.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * Created by Erik on 7/3/2017.
 */
public class Order {

    private final LocalDate orderDate;
    private final Map<Item, Integer> items;
    private final List<Discount> discounts;
    private final Map<String, String> properties;

    public Order(LocalDate orderDate) {
        this.orderDate = orderDate;
        this.items = new HashMap<>();
        this.discounts = new ArrayList<>();
        this.properties = new HashMap<>();
    }

    public List<Discount> getDiscounts() {
        return discounts;
    }

    public void addDiscount(Discount discount) {
        this.discounts.add(discount);
    }

    public Map<Item, Integer> getItems() {
        return items;
    }

    public void addItem(Item item) {
        items.putIfAbsent(item, 0);
        items.put(item, items.get(item) + 1);
    }

    public String getProperty(String key) {
        return properties.get(key);
    }

    public void setProperty(String key, String value) {
        this.properties.put(key, value);
    }

    public Map<String, String> getProperties() {
        return properties;
    }

    public LocalDate getOrderDate() {
        return orderDate;
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderDate=" + orderDate +
                ", items=" + items +
                ", discounts=" + discounts +
                ", properties=" + properties +
                '}';
    }
}
