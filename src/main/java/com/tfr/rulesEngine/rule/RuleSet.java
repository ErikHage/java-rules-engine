package com.tfr.rulesEngine.rule;

import com.google.common.collect.Sets;
import com.tfr.rulesEngine.exception.DuplicateRuleException;

import java.util.Iterator;
import java.util.Set;

/**
 *
 * Created by Erik on 6/14/2017.
 */
//TODO R extends Rule<I,O>
public abstract class RuleSet<I,O,R extends Rule> implements Iterable<R> {

    private String name;
    private Set<R> rules;

    public RuleSet(String name) {
        this(name, Sets.newTreeSet());
    }

    public RuleSet(String name, Set<R> rules) {
        this.name = name;
        this.rules = Sets.newTreeSet();
        rules.forEach(this::add);
    }

    public String getName() {
        return this.name;
    }

    public Set<R> getRules() {
        return this.rules;
    }

    public boolean add(R rule) {
        boolean addedSuccessfully = rules.add(rule);
        if(!addedSuccessfully) {
            throw new DuplicateRuleException(rule);
        }
        return addedSuccessfully;
    }

    public boolean contains(R rule) {
        return rules.contains(rule);
    }

    public boolean remove(R rule) {
        return rules.remove(rule);
    }

    public Iterator<R> iterator() {
        return rules.iterator();
    }

}
