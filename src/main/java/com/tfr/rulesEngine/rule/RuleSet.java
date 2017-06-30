package com.tfr.rulesEngine.rule;

import java.util.Set;
import java.util.stream.Stream;

/**
 *
 * Created by Erik on 6/14/2017.
 */
public interface RuleSet<I,O> extends Iterable<Rule<I,O>> {

    String getName();
    Set<Rule<I,O>> getRules();

    Stream<Rule<I,O>> stream();

    boolean add(Rule<I,O> rule);
    boolean contains(Rule<I,O> rule);
    boolean remove(Rule<I,O> rule);

}
