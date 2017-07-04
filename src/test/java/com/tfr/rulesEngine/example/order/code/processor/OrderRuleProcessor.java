package com.tfr.rulesEngine.example.order.code.processor;

import com.tfr.rulesEngine.evaluate._Evaluator;
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

    private _Evaluator<Order, Order> taxEvaluator;
    private _Evaluator<Order, Order> discountEvaluator;

    @Autowired
    public OrderRuleProcessor(@Qualifier("TaxRuleEvaluator") _Evaluator<Order, Order> taxEvaluator,
                              @Qualifier("DiscountRuleEvaluator") _Evaluator<Order, Order> discountEvaluator) {
        this.taxEvaluator = taxEvaluator;
        this.discountEvaluator = discountEvaluator;
    }

    public Order process(Order order) {
        taxEvaluator.evaluateMulti(order);
        discountEvaluator.evaluateMulti(order);
        return order;
    }


}
