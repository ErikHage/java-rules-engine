package com.tfr.rulesEngine.evaluate;

import com.google.common.base.Strings;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.tfr.rulesEngine.rule.Rule;
import com.tfr.rulesEngine.rule.chain.ChainingRule;
import com.tfr.rulesEngine.rule.chain.ChainingRuleSet;

import java.util.List;
import java.util.Map;

/**
 *
 * Created by Erik Hage on 6/17/2017.
 */
public class ChainingRuleEvaluator<I> implements Evaluator<I,I> {

    private final String initialSet;
    private final Map<String, ChainingRuleSet<I>> ruleSetMap;

    public ChainingRuleEvaluator(String initialSet, List<ChainingRuleSet<I>> ruleSets) {
        this.initialSet = initialSet;
        this.ruleSetMap = Maps.newHashMap();
        ruleSets.forEach(rs -> ruleSetMap.put(rs.getName(), rs));
    }

    @Override
    public List<I> evaluate(I input) {
        List<I> output = Lists.newArrayList();
        chain(initialSet, input, output);
        return output;
    }

    private void chain(String setName, I input, List<I> output) {
        for(Rule<I,I> rule: ruleSetMap.get(setName)) {
            if(rule.getPredicate().test(input)) {
                I result = rule.getFunction().apply(input);
                String next = ((ChainingRule) rule).getNext();
                if (!Strings.isNullOrEmpty(next)) {
                    chain(next, result, output);
                } else {
                    output.add(result);
                }
                break;
            }
        }
    }
}
