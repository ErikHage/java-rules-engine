package com.tfr.rulesEngine.example.order.code.rule;

import com.google.common.collect.Lists;
import com.tfr.rulesEngine.evaluate.ChainingRuleEvaluator;
import com.tfr.rulesEngine.evaluate.Evaluator;
import com.tfr.rulesEngine.evaluate.RuleSetEvaluator;
import com.tfr.rulesEngine.example.order.code.model.Order;
import com.tfr.rulesEngine.rule.RuleSet;
import com.tfr.rulesEngine.rule.simple.SimpleRuleSetBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

import static com.tfr.rulesEngine.example.order.code.rule.DiscountRules.LABOR_DAY_SALE;

/**
 *
 * Created by Erik on 7/3/2017.
 */
@Configuration
public class SpringRuleConfig {

    @Bean("TaxRuleEvaluator")
    public RuleSetEvaluator<Order, Order> taxRuleEvaluator() {
        return new RuleSetEvaluator<>(
                taxRuleSet(),
                Evaluator.EvaluationStyle.SINGLE_MATCH);
    }

    @Bean("TaxRuleSet")
    public RuleSet<Order, Order> taxRuleSet() {
        return new SimpleRuleSetBuilder<Order, Order>("taxRules")
                .addRule(TaxRules.STATE_RULE)
                .build();
    }

    @Bean("DiscountRuleEvaluator")
    public ChainingRuleEvaluator<Order> discountRuleEvaluator() {
        return new ChainingRuleEvaluator<>(
                "discountInit",
                discountRuleSets(),
                Evaluator.EvaluationStyle.SINGLE_MATCH
        );
    }

    @Bean("DiscountRuleSets")
    public List<RuleSet<Order, Order>> discountRuleSets() {
        List<RuleSet<Order, Order>> discountRuleSets = Lists.newArrayList();

        discountRuleSets.add(new SimpleRuleSetBuilder<Order, Order>("discountInit")
                .addRule(LABOR_DAY_SALE)
                .build());

        return discountRuleSets;
    }
}
