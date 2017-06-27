package com.tfr.rulesEngine.evaluate;

import com.google.common.base.Strings;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.tfr.rulesEngine.rule.Rule;
import com.tfr.rulesEngine.rule.RuleSet;

import java.util.List;
import java.util.Map;

/**
 *
 * Created by Erik Hage on 6/17/2017.
 */
public class ChainingRuleEvaluator<I> implements Evaluator<I,I> {

    private final String initialSet;
    private final Map<String, RuleSet<I,I>> ruleSetMap;

    public ChainingRuleEvaluator(String initialSet, List<RuleSet<I,I>> ruleSets) {
        this.initialSet = initialSet;
        this.ruleSetMap = Maps.newHashMap();
        ruleSets.forEach(rs -> ruleSetMap.put(rs.getName(), rs));
    }

    @Override
    public List<I> evaluate(I input) {
        List<I> output = Lists.newArrayList();
        evaluate(initialSet, input, output);
        return output;
    }

    private void evaluate(String setName, I input, List<I> output) {
        for(Rule<I,I> rule: ruleSetMap.get(setName)) {
            if(rule.getPredicate().test(input)) {
                I result = rule.getFunction().apply(input);
                if (!Strings.isNullOrEmpty(rule.getNext())) {
                    evaluate(rule.getNext(), result, output);
                } else {
                    output.add(result);
                }
                break;
            }
        }
    }
}
