package com.tfr.rulesEngine.rule;

import java.util.Optional;
import java.util.function.Function;
import java.util.function.Predicate;

/**
 *
 * Created by Erik on 6/14/2017.
 */
public interface _Rule<I,O> extends Comparable<_Rule<I,O>> {

    String getName();
    String getGroupName();
    int getPriority();
    Predicate<I> getMatchCondition();
    Function<I,Optional<O>> getOnMatchHandler();
    String getNextGroupName();

}
