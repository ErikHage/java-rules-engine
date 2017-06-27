package com.tfr.rulesEngine.rule;

import com.tfr.rulesEngine.rule.simple.SimpleRuleSet;

/**
 *
 * Created by Erik on 6/26/2017.
 */
public class GenericRuleSetBuilder<I,O> extends RuleSetBuilder<I,O,Rule<I,O>> {

    public GenericRuleSetBuilder(String name) {
        super(name);
    }

    @Override
    public RuleSet<I, O, Rule<I, O>> build() {
        return null; //TODO
    }
}
