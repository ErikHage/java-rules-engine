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
public class ChainingOutputChainingRuleEvaluator<IO> implements Evaluator<IO,IO> {

    private final String initialSet;
    private final Map<String, ChainingRuleSet<IO,IO>> ruleSetMap;

    public ChainingOutputChainingRuleEvaluator(String initialSet, List<ChainingRuleSet<IO,IO>> ruleSets) {
        this.initialSet = initialSet;
        this.ruleSetMap = Maps.newHashMap();
        ruleSets.forEach(rs -> ruleSetMap.put(rs.getName(), rs));
    }

    @Override
    public List<IO> evaluate(IO input) {
        List<IO> output = Lists.newArrayList();
        chain(initialSet, input, output);
        return output;
    }

    private void chain(String setName, IO input, List<IO> output) {
        for(Rule<IO,IO> rule: ruleSetMap.get(setName)) {
            if(rule.getPredicate().test(input)) {
                IO result = rule.getFunction().apply(input);
                String next = ((ChainingRule) rule).next();
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
