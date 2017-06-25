package com.tfr.rulesEngine.rule.link;

import com.tfr.rulesEngine.exception.RuleException;
import com.tfr.rulesEngine.rule.Rule;
import com.tfr.rulesEngine.rule.simple.SimpleRuleSet;

/**
 *
 * Created by Erik Hage on 6/17/2017.
 */
public class LinkingRuleSet<I,O> extends SimpleRuleSet<I,O> {

    public LinkingRuleSet(String name) {
        super(name);
    }

    @Override
    public boolean add(Rule<I,O> rule) {
        if(!(rule instanceof LinkingRule)) {
            throw new RuleException("Rules in this set must be of type LinkingRule");
        }
        if(getRules().contains(rule)) {
            throw new RuleException("RuleSet already contains a rule named: " + rule.getName());
        }
        getRules().add(rule);
        return true;
    }
}
