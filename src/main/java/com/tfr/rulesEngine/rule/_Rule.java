package com.tfr.rulesEngine.rule;

import java.util.Optional;
import java.util.function.Function;
import java.util.function.Predicate;

/**
 *
 */
public interface _Rule<I,O> extends Comparable<_Rule<I,O>> {

    String getName();
    String getGroupName();
    int getPriority();
    Predicate<I> getMatchCondition();
    boolean testMatchCondition(I input);
    Function<I,Optional<O>> getOnMatchHandler();
    Optional<O> applyOnMatchHandler(I input);
    String getNextGroupName();

}
