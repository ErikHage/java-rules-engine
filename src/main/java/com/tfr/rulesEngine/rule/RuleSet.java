package com.tfr.rulesEngine.rule;

import java.util.Set;

/**
 *
 * Created by Erik on 6/14/2017.
 */
public interface RuleSet<I,O> {

    String getSetName();
    Set<Rule<I, O>> getRules();
    boolean addRule(Rule<I,O> rule);

}
