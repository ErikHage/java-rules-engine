package com.tfr.rulesEngine.rule;

import java.util.Set;
import java.util.stream.Stream;

/**
 *
 */
public interface _RuleSet<I,O> extends Iterable<_Rule<I,O>> {
    boolean add(_Rule<I,O> rule);
    boolean contains(_Rule<I,O> rule);
    boolean remove(_Rule<I,O> rule);
    int size();
    Stream<_Rule<I,O>> stream();

}
