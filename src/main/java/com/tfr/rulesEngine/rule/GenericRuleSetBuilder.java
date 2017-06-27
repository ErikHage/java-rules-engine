package com.tfr.rulesEngine.rule;

/**
 *
 * Created by Erik on 6/26/2017.
 */
public class GenericRuleSetBuilder<I,O> extends RuleSetBuilder<I,O,Rule> {

    public GenericRuleSetBuilder(String name) {
        super(name);
    }

    @Override
    public RuleSet<I, O, Rule> build() {
        return null;
    }
}
