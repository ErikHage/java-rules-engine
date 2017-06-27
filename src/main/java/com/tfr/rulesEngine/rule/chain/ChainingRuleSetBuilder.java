package com.tfr.rulesEngine.rule.chain;

import com.tfr.rulesEngine.rule.RuleSet;
import com.tfr.rulesEngine.rule.RuleSetBuilder;

/**
 *
 * Created by Erik on 6/25/2017.
 */
public class ChainingRuleSetBuilder<I> extends RuleSetBuilder<I,I, ChainingRule<I>> {

    public ChainingRuleSetBuilder(String name) {
        super(name);
    }

    /*public RuleSet<I,I, ChainingRule> build() {
        return new ChainingRuleSet<>(name, rules);
    }*/

    @Override
    public RuleSet<I, I, ChainingRule<I>> build() {
        return new ChainingRuleSet<I>(name, rules);
    }
}
