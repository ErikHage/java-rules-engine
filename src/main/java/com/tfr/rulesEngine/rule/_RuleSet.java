package com.tfr.rulesEngine.rule;

import java.util.Set;
import java.util.stream.Stream;

/**
 *
 */
public interface _RuleSet<I,O> extends Iterable<_Rule<I,O>> {

    Set<_Rule<I,O>> getRules();

    Stream<_Rule<I,O>> stream();

    boolean add(_Rule<I,O> rule);

    boolean contains(_Rule<I,O> rule);

    boolean remove(_Rule<I,O> rule);

}
