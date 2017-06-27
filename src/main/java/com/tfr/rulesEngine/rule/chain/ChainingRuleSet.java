package com.tfr.rulesEngine.rule.chain;

import com.tfr.rulesEngine.rule.RuleSet;

import java.util.Set;

/**
 *
 * Created by Erik Hage on 6/17/2017.
 */
public class ChainingRuleSet<I> extends RuleSet<I,I, ChainingRule<I>> {

    public ChainingRuleSet(String name) {
        super(name);
    }

    public ChainingRuleSet(String name, Set<ChainingRule<I>> rules) {
        super(name, rules);
    }

}
