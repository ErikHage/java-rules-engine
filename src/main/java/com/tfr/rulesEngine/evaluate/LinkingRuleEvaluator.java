package com.tfr.rulesEngine.evaluate;

import com.google.common.base.Strings;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.tfr.rulesEngine.rule.Rule;
import com.tfr.rulesEngine.rule.link.LinkingRule;
import com.tfr.rulesEngine.rule.link.LinkingRuleSet;

import java.util.List;
import java.util.Map;

/**
 *
 * Created by Erik Hage on 6/17/2017.
 */
public class LinkingRuleEvaluator<I,O> implements Evaluator<I,O> {

    private final String initialSet;
    private final Map<String, LinkingRuleSet<I,O>> ruleSetMap;

    public LinkingRuleEvaluator(String initialSet, List<LinkingRuleSet<I,O>> ruleSets) {
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
                String next = ((LinkingRule) rule).getNext();
                if (!Strings.isNullOrEmpty(next)) {
                    chain(next, input, output);
                } else {
                    output.add(rule.getFunction().apply(input));
                }
                break;
            }
        }
    }
}
