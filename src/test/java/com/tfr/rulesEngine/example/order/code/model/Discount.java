package com.tfr.rulesEngine.example.order.code.model;

/**
 *
 * Created by Erik on 7/3/2017.
 */
public class Discount {

    public enum Type {
        FLAT_AMOUNT,
        PERCENTAGE
    }

    private final String description;
    private final double discount;
    private final Type type;

    public Discount(String description, double discount, Type type) {
        this.description = description;
        this.discount = discount;
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public double getDiscount() {
        return discount;
    }

    public Type getType() {
        return type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Discount discount1 = (Discount) o;

        if (Double.compare(discount1.discount, discount) != 0) return false;
        if (!description.equals(discount1.description)) return false;
        return type == discount1.type;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = description.hashCode();
        temp = Double.doubleToLongBits(discount);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + type.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Discount{" +
                "description='" + description + '\'' +
                ", discount=" + discount +
                ", type=" + type +
                '}';
    }
}
