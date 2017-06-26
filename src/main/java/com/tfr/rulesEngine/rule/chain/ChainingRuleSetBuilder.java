package com.tfr.rulesEngine.rule.chain;

import com.google.common.collect.Sets;
import com.tfr.rulesEngine.exception.RuleException;
import com.tfr.rulesEngine.rule.Rule;
import com.tfr.rulesEngine.rule.RuleSet;
import com.tfr.rulesEngine.rule.RuleSetBuilder;
import com.tfr.rulesEngine.rule.link.LinkingRuleSet;

import java.util.Set;

/**
 *
 * Created by Erik on 6/25/2017.
 */
public class ChainingRuleSetBuilder<I> implements RuleSetBuilder<I,I> {

    private String name;
    private Set<Rule<I,I>> rules;

    public ChainingRuleSetBuilder(String name) {
        this.name = name;
        this.rules = Sets.newHashSet();
    }

    public RuleSet<I,I> build() {
        return new ChainingRuleSet<>(name, rules);
    }

    public ChainingRuleSetBuilder<I> addRule(Rule<I,I> rule) {
        if(rules.contains(rule)) {
            throw new RuleException("Set already contains a rule with name " + rule.getName());
        }
        rules.add(rule);
        return this;
    }


}
