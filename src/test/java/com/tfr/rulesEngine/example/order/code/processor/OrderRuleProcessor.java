package com.tfr.rulesEngine.example.order.code.processor;

import com.tfr.rulesEngine.evaluate.ChainingRuleEvaluator;
import com.tfr.rulesEngine.evaluate.RuleSetEvaluator;
import com.tfr.rulesEngine.example.order.code.model.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

/**
 *
 * Created by Erik on 7/3/2017.
 */
@Component("OrderRuleProcessor")
public class OrderRuleProcessor {

    private RuleSetEvaluator<Order, Order> taxEvaluator;
    private ChainingRuleEvaluator<Order> discountEvaluator;

    @Autowired
    public OrderRuleProcessor(@Qualifier("TaxRuleEvaluator")RuleSetEvaluator<Order, Order> taxEvaluator,
                              @Qualifier("DiscountRuleEvaluator") ChainingRuleEvaluator<Order> discountEvaluator) {
        this.taxEvaluator = taxEvaluator;
        this.discountEvaluator = discountEvaluator;
    }

    public Order process(Order order) {
        order = taxEvaluator.evaluate(order).get(0);
        order = discountEvaluator.evaluate(order).get(0);
        return order;
    }


}
