package com.tfr.rulesEngine.rule.chain;

import com.google.common.collect.Sets;
import com.tfr.rulesEngine.exception.RuleException;
import com.tfr.rulesEngine.rule.Rule;
import com.tfr.rulesEngine.rule.RuleSet;

import java.util.Iterator;
import java.util.Set;

/**
 *
 * Created by Erik Hage on 6/17/2017.
 */
public class ChainingRuleSet<I,O> implements RuleSet<I,O> {

    private String name;
    private Set<Rule<I,O>> rules;

    public ChainingRuleSet(String name) {
        this.name = name;
        this.rules = Sets.newTreeSet();
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public Set<Rule<I, O>> getRules() {
        return this.rules;
    }

    @Override
    public boolean add(Rule<I, O> rule) {
        if(!(rule instanceof ChainingRule)) {
            throw new RuleException("Rules in this set must be of type ChainingRule");
        }
        if(rules.contains(rule)) {
            return false;
        }
        rules.add(rule);
        return true;
    }

    @Override
    public boolean contains(Rule<I,O> rule) {
        return rules.contains(rule);
    }

    @Override
    public boolean remove(Rule<I,O> rule) {
        return rules.remove(rule);
    }

    @Override
    public Iterator<Rule<I,O>> iterator() {
        return rules.iterator();
    }
}
