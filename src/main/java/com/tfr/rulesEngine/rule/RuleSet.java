package com.tfr.rulesEngine.rule;

import com.tfr.rulesEngine.exception.DuplicateRuleException;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Stream;

/**
 *
 */
public class RuleSet<I,O> implements _RuleSet<I,O> {

    private final Set<_Rule<I,O>> rules;

    public RuleSet() {
        rules = new TreeSet<>();
    }

    public RuleSet(Set<_Rule<I,O>> rules) {
        this.rules = new TreeSet<>(rules);
    }

    public Set<_Rule<I,O>> getRules() {
        return rules;
    }

    @Override
    public boolean add(_Rule<I,O> rule) {
        if(!rules.add(rule)) {
            throw new DuplicateRuleException("_RuleSet already contains a rule named: " + rule.getName());
        }
        return true;
    }

    @Override
    public boolean contains(_Rule<I,O> rule) {
        return rules.contains(rule);
    }

    @Override
    public boolean remove(_Rule<I,O> rule) {
        return rules.remove(rule);
    }

    @Override
    public Iterator<_Rule<I,O>> iterator() {
        return rules.iterator();
    }

    @Override
    public Stream<_Rule<I,O>> stream() {
        return rules.stream();
    }

    public static class RuleSetBuilder<I,O> {

        private final Set<_Rule<I,O>> rules;

        public RuleSetBuilder() {
            this.rules = new HashSet<>();
        }

        public RuleSetBuilder<I,O> addRule(_Rule<I,O> rule) {
            if(rules.contains(rule)) {
                throw new DuplicateRuleException("Set already contains a rule with name " + rule.getName());
            }
            rules.add(rule);
            return this;
        }

        public RuleSet<I,O> build() {
            return new RuleSet<>(rules);
        }
    }
}
