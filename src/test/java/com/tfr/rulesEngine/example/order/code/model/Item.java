package com.tfr.rulesEngine.example.order.code.model;

/**
 *
 * Created by Erik on 7/3/2017.
 */
public class Item {

    public enum Type {
        CLOTHING,
        KITCHEN,
        FOOD,
        TOOLS,
        HOUSEWARES
    }

    private final String name;
    private final double price;
    private final Type type;
    private final String subType;

    public Item(String name, double price, Type type, String subType) {
        this.name = name;
        this.price = price;
        this.type = type;
        this.subType = subType;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public Type getType() {
        return type;
    }

    public String getSubType() {
        return subType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Item item = (Item) o;

        if (Double.compare(item.price, price) != 0) return false;
        if (!name.equals(item.name)) return false;
        if (type != item.type) return false;
        return subType == item.subType;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = name.hashCode();
        temp = Double.doubleToLongBits(price);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + type.hashCode();
        result = 31 * result + subType.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Item{" +
                "name='" + name + '\'' +
                ", price=" + price +
                ", type=" + type +
                ", subType=" + subType +
                '}';
    }
}
