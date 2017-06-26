package com.tfr.rulesEngine.rule;

/**
 * Created by Erik on 6/25/2017.
 */
public interface RuleSetBuilder<I,O> {

    RuleSetBuilder<I,O> addRule(Rule<I,O> rule);
    RuleSet<I,O> build();

}
