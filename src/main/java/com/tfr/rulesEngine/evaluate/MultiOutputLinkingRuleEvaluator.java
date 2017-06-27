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
public class MultiOutputLinkingRuleEvaluator<I,O> implements Evaluator<I,O> {

    private final String initialSet;
    private final Map<String, RuleSet<I,O>> ruleSetMap;

    public MultiOutputLinkingRuleEvaluator(String initialSet, List<RuleSet<I,O>> ruleSets) {
        this.initialSet = initialSet;
        this.ruleSetMap = Maps.newHashMap();
        ruleSets.forEach(rs -> ruleSetMap.put(rs.getName(), rs));
    }

    @Override
    public List<O> evaluate(I input) {
        List<O> output = Lists.newArrayList();
        evaluate(initialSet, input, output);
        return output;
    }

    private void evaluate(String setName, I input, List<O> output) {
        for(Rule<I,O> rule: ruleSetMap.get(setName)) {
            if(rule.getPredicate().test(input)) {
                output.add(rule.getFunction().apply(input));
                if (!Strings.isNullOrEmpty(rule.getNext())) {
                    evaluate(rule.getNext(), input, output);
                }
                break;
            }
        }
    }
}
