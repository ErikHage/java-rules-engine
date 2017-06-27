package com.tfr.rulesEngine.rule;

import com.google.common.collect.Sets;
import com.tfr.rulesEngine.exception.DuplicateRuleException;

import java.util.Set;

/**
 *
 * Created by Erik on 6/25/2017.
 */
public abstract class RuleSetBuilder<I,O,R extends Rule> {

    protected String name;
    protected Set<R> rules;

    public RuleSetBuilder(String name) {
        this.name = name;
        this.rules = Sets.newHashSet();
    }

    public RuleSetBuilder<I,O,R> addRule(R rule) {
        boolean addedSuccessfully = rules.add(rule);
        if(!addedSuccessfully) {
            throw new DuplicateRuleException(rule);
        }
        return this;
    }

    public abstract RuleSet<I,O,R> build();

}
