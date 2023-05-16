package com.tfr.rulesEngine.rule;

import com.google.common.collect.Sets;
import com.tfr.rulesEngine.exception.RuleException;

import java.util.Iterator;
import java.util.Set;
import java.util.stream.Stream;

/**
 *
 * Created by Erik on 6/14/2017.
 */
public class RuleSet<I,O> implements _RuleSet<I,O> {

    private final Set<_Rule<I,O>> rules;

    public RuleSet() {
        rules = Sets.newTreeSet();
    }

    public RuleSet(Set<_Rule<I,O>> rules) {
        this.rules = Sets.newTreeSet(rules);
    }

    public Set<_Rule<I,O>> getRules() {
        return rules;
    }

    @Override
    public boolean add(_Rule<I,O> rule) {
        if(!rules.add(rule)) {
            throw new RuleException("_RuleSet already contains a rule named: " + rule.getName());
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
            this.rules = Sets.newHashSet();
        }

        public RuleSetBuilder<I,O> addRule(_Rule<I,O> rule) {
            if(rules.contains(rule)) {
                throw new RuleException("Set already contains a rule with name " + rule.getName());
            }
            rules.add(rule);
            return this;
        }

        public RuleSet<I,O> build() {
            return new RuleSet<>(rules);
        }
    }
}
