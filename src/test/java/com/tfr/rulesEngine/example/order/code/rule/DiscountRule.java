package com.tfr.rulesEngine.example.order.code.rule;

import com.tfr.rulesEngine.example.order.code.model.Order;
import com.tfr.rulesEngine.rule.simple.SimpleRule;

import java.util.function.Function;
import java.util.function.Predicate;

/**
 *
 * Created by Erik on 7/3/2017.
 */
public class DiscountRule extends SimpleRule<Order, Order> {

    public DiscountRule(String name, Predicate<Order> predicate, Function<Order, Order> function) {
        super(name, predicate, function);
    }

    public DiscountRule(String name, int priority, Predicate<Order> predicate, Function<Order, Order> function) {
        super(name, priority, predicate, function);
    }

    public DiscountRule(String name, int priority, Predicate<Order> predicate, Function<Order, Order> function, String next) {
        super(name, priority, predicate, function, next);
    }
}
