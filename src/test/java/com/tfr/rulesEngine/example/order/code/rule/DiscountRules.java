package com.tfr.rulesEngine.example.order.code.rule;

import com.tfr.rulesEngine.example.order.code.model.Order;
import com.tfr.rulesEngine.rule.Rule;
import com.tfr.rulesEngine.rule._Rule;

import static com.tfr.rulesEngine.config.Constants.*;
import static com.tfr.rulesEngine.example.order.code.function.Constants.*;

/**
 *
 *
 * Created by Erik on 7/3/2017.
 */
public interface DiscountRules {

    _Rule<Order, Order> LABOR_DAY_SALE = new Rule.RuleBuilder<Order, Order>(
            "laborDaySale",
            o -> LABOR_DAY.equals(o.getOrderDate()))
            .consumer(o -> o.addDiscount(LABOR_DAY_DEAL))
            .group(DEFAULT_GROUP)
            .nextGroup("Summer")
            .build();

    _Rule<Order, Order> NO_SINGLE_DAY_SALE = new Rule.RuleBuilder<Order, Order>(
            "noSingleDaySale",
            o -> true)
            .consumer(o -> {})
            .group(DEFAULT_GROUP)
            .nextGroup("Summer")
            .build();


    _Rule<Order, Order> SUMMER_SALE = new Rule.RuleBuilder<Order, Order>(
            "summerSale",
            o -> SUMMER_SALE_START.isBefore(o.getOrderDate()) && SUMMER_SALE_END.isAfter(o.getOrderDate()))
            .consumer(o -> o.addDiscount(SUMMER_SALE_DEAL))
            .group("Summer")
            .build();

}
