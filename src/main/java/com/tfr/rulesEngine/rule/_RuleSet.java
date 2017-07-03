package com.tfr.rulesEngine.rule;

import java.util.Set;
import java.util.stream.Stream;

/**
 *
 * Created by Erik on 6/14/2017.
 */
public interface _RuleSet<I,O> extends Iterable<_Rule<I,O>> {

    Set<_Rule<I,O>> getRules();

    Stream<_Rule<I,O>> stream();

    boolean add(_Rule<I,O> rule);
    boolean contains(_Rule<I,O> rule);
    boolean remove(_Rule<I,O> rule);

}
