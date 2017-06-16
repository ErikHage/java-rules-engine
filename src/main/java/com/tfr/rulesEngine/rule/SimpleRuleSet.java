package com.tfr.rulesEngine.rule;

import java.util.Set;
import java.util.TreeSet;

/**
 *
 * Created by Erik on 6/14/2017.
 */
public class SimpleRuleSet<I,O> implements RuleSet<I,O> {

    private final String name;
    private final Set<Rule<I,O>> rules;

    public SimpleRuleSet(String name) {
        this.name = name;
        this.rules = new TreeSet<>();
    }

    @Override
    public String getName() {
        return name;
    }

    public Set<Rule<I, O>> getRules() {
        return rules;
    }

    @Override
    public boolean add(Rule<I, O> rule) {
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

}
