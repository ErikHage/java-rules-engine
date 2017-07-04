package com.tfr.rulesEngine.example.order.code.rule;

import com.tfr.rulesEngine.evaluate.RuleEvaluator;
import com.tfr.rulesEngine.evaluate._Evaluator;
import com.tfr.rulesEngine.example.order.code.model.Order;
import com.tfr.rulesEngine.rule.RuleSet;
import com.tfr.rulesEngine.rule._RuleSet;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import static com.tfr.rulesEngine.example.order.code.rule.DiscountRules.*;

/**
 *
 * Created by Erik on 7/3/2017.
 */
@Configuration
@ComponentScan(basePackages = {"com.tfr.rulesEngine.example.order"})
public class SpringRuleConfig {

    @Bean("TaxRuleEvaluator")
    public _Evaluator<Order, Order> taxRuleEvaluator() {
        return new RuleEvaluator<>(taxRuleSet());
    }

    @Bean("TaxRuleSet")
    public _RuleSet<Order, Order> taxRuleSet() {
        return new RuleSet.RuleSetBuilder<Order, Order>()
                .addRule(TaxRules.IS_TAXABLE)
                .addRule(TaxRules.NOT_TAXABLE)
                .addRule(TaxRules.ORDER_CONTAINS_TAXABLE_PRODUCT)
                .addRule(TaxRules.ORDER_DOES_NOT_CONTAIN_TAXABLE_PRODUCT)
                .build();
    }

    @Bean("DiscountRuleEvaluator")
    public _Evaluator<Order,Order> discountRuleEvaluator() {
        return new RuleEvaluator<>(discountRuleSets());
    }

    @Bean("DiscountRuleSets")
    public _RuleSet<Order, Order> discountRuleSets() {
        return new RuleSet.RuleSetBuilder<Order, Order>()
                .addRule(LABOR_DAY_SALE)
                .addRule(NO_SINGLE_DAY_SALE)
                .addRule(SUMMER_SALE)
                .build();
    }
}
