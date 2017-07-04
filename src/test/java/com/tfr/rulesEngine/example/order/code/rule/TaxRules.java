package com.tfr.rulesEngine.example.order.code.rule;

import com.google.common.collect.Sets;
import com.tfr.rulesEngine.example.order.code.model.Item;
import com.tfr.rulesEngine.example.order.code.model.Order;
import com.tfr.rulesEngine.rule.Rule;
import com.tfr.rulesEngine.rule._Rule;

import java.util.Optional;

import static com.tfr.rulesEngine.config.Constants.DEFAULT_GROUP;
import static com.tfr.rulesEngine.example.order.code.function.Constants.STATES_IN_TAX_SCOPE;
import static com.tfr.rulesEngine.example.order.code.function.Constants.TAXABLE_PRODUCT_TYPES;

/**
 *
 * Created by Erik on 7/3/2017.
 */
public interface TaxRules {

    _Rule<Order,Order> IS_TAXABLE = new Rule.RuleBuilder<Order,Order>(
            "taxableRule",
            (o) -> STATES_IN_TAX_SCOPE.contains(o.getProperty("STATE")),
            (o) -> {
                o.setProperty("IN_SCOPE_FOR_TAX", "Y");
                return Optional.empty();
            })
            .group(DEFAULT_GROUP)
            .priority(100)
            .nextGroup("Product Group")
            .build();

    _Rule<Order,Order> NOT_TAXABLE = new Rule.RuleBuilder<Order,Order>(
            "notTaxableRule",
            (o) -> true,
            (o) -> {
                o.setProperty("IN_SCOPE_FOR_TAX", "N");
                return Optional.empty();
            })
            .group(DEFAULT_GROUP)
            .priority(50)
            .build();



    _Rule<Order,Order> ORDER_CONTAINS_TAXABLE_PRODUCT = new Rule.RuleBuilder<Order,Order>(
            "taxableProductRule",
            (o) -> "Y".equals(o.getProperty("IN_SCOPE_FOR_TAX"))
                    && o.getItems()
                    .keySet()
                    .stream()
                    .filter(i -> {
                        System.out.println(i);
                        return TAXABLE_PRODUCT_TYPES.contains(i.getType());
                    })
                    .count() > 0,
            (o) -> {
                o.setProperty("CONTAINS_TAXABLE_PRODUCTS", "Y");
                return Optional.empty();
            })
            .group("Product Group")
            .priority(100)
            .build();

    _Rule<Order,Order> ORDER_DOES_NOT_CONTAIN_TAXABLE_PRODUCT = new Rule.RuleBuilder<Order,Order>(
            "noTaxableProductRule",
            (o) -> true,
            (o) -> {
                o.setProperty("CONTAINS_TAXABLE_PRODUCTS", "N");
                return Optional.empty();
            })
            .group("Product Group")
            .priority(50)
            .build();
}
