package com.tfr.rulesEngine.evaluate;

import com.google.common.collect.Lists;
import com.tfr.rulesEngine.rule.Rule;
import com.tfr.rulesEngine.rule.simple.SimpleRuleSet;

import java.util.List;

/**
 *
 * Created by Erik Hage on 6/16/2017.
 */
public class MultiMatchEvaluator<I,O> implements Evaluator<I,O> {

    private final SimpleRuleSet<I,O> ruleSet;

    public MultiMatchEvaluator(SimpleRuleSet<I,O> ruleSet) {
        this.ruleSet = ruleSet;
    }

    public List<O> evaluate(I input) {
        List<O> output = Lists.newArrayList();
        for(Rule<I,O> rule : ruleSet) {
            if(rule.getPredicate().test(input)) {
                output.add(rule.getFunction().apply(input));
            }
        }
        return output;
    }

}
