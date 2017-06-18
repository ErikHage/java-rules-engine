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
public class ChainingRuleEvaluator<I,O> implements Evaluator<I,O> {

    private final String initialSet;
    private final Map<String, ChainingRuleSet<I,O>> ruleSetMap;

    public ChainingRuleEvaluator(String initialSet, List<ChainingRuleSet<I,O>> ruleSets) {
        this.initialSet = initialSet;
        this.ruleSetMap = Maps.newHashMap();
        ruleSets.forEach(rs -> ruleSetMap.put(rs.getName(), rs));
    }

    @Override
    public List<O> evaluate(I input) {
        List<O> output = Lists.newArrayList();
        chain(initialSet, input, output);
        return output;
    }

    private void chain(String setName, I input, List<O> output) {
        for(Rule<I,O> rule: ruleSetMap.get(setName)) {
            if(rule.getPredicate().test(input)) {
                output.add(rule.getFunction().apply(input));
                String next = ((ChainingRule)rule).next();
                if(!Strings.isNullOrEmpty(next)) {
                    chain(next, input, output);
                }
                break;
            } else {
                //log miss
            }
        }
    }
}
