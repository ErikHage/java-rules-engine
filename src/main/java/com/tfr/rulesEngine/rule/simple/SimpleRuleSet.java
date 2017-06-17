package com.tfr.rulesEngine.rule.simple;

import com.tfr.rulesEngine.rule.Rule;
import com.tfr.rulesEngine.rule.RuleSet;

import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

/**
 *
 * Created by Erik on 6/14/2017.
 */
public class SimpleRuleSet<R extends Rule> implements RuleSet<R> {

    private final String name;
    private final Set<R> rules;

    public SimpleRuleSet(String name) {
        this.name = name;
        this.rules = new TreeSet<>();
    }

    @Override
    public String getName() {
        return name;
    }

    public Set<R> getRules() {
        return rules;
    }

    @Override
    public boolean add(R rule) {
        if(rules.contains(rule)) {
            return false;
        }
        rules.add(rule);
        return true;
    }

    @Override
    public boolean contains(R rule) {
        return rules.contains(rule);
    }

    @Override
    public boolean remove(R rule) {
        return rules.remove(rule);
    }

    @Override
    public Iterator<R> iterator() {
        return rules.iterator();
    }
}
