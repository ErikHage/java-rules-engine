package com.tfr.rulesEngine.rule;

import java.util.Set;

/**
 *
 * Created by Erik on 6/14/2017.
 */
public interface RuleSet<I,O> extends Iterable<Rule<I,O>> {

    String getName();
    Set<Rule<I,O>> getRules();

    boolean add(Rule<I,O> rule);
    boolean contains(Rule<I,O> rule);
    boolean remove(Rule<I,O> rule);

}
