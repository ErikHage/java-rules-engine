package com.tfr.rulesEngine.evaluate;

import com.google.common.collect.Lists;
import com.tfr.rulesEngine.rule.Rule;

import java.util.List;

/**
 *
 * Created by Erik on 6/22/2017.
 */
public class SingleRuleEvaluator<I,O> implements Evaluator<I,O> {

    private final Rule<I,O> rule;

    public SingleRuleEvaluator(Rule<I,O> rule) {
        this.rule = rule;
    }

    @Override
    public List<O> evaluate(I input) {
        List<O> output = Lists.newArrayList();
        if(rule.getPredicate().test(input)) {
            output.add(rule.getFunction().apply(input));
        }
        return output;
    }

    @Override
    public EvaluationStyle getEvaluationStyle() {
        return EvaluationStyle.SINGLE_MATCH_PER_SET;
    }
}
