package com.tfr.rulesEngine.rule.chain;

import com.tfr.rulesEngine.exception.RuleException;
import com.tfr.rulesEngine.rule.Rule;
import com.tfr.rulesEngine.rule.link.LinkingRuleSet;

import java.util.Set;

/**
 *
 * Created by Erik Hage on 6/17/2017.
 */
public class ChainingRuleSet<I> extends LinkingRuleSet<I,I> {

    public ChainingRuleSet(String name) {
        super(name);
    }

    public ChainingRuleSet(String name, Set<Rule<I,I>> rules) {
        super(name, rules);
    }

    @Override
    public boolean add(Rule<I,I> rule) {
        if(!(rule instanceof ChainingRule)) {
            throw new RuleException("Rules in this set must be of type ChainingRule");
        }
        if(getRules().contains(rule)) {
            throw new RuleException("RuleSet already contains a rule named: " + rule.getName());
        }
        getRules().add(rule);
        return true;
    }

}
