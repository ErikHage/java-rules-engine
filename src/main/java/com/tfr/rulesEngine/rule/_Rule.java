package com.tfr.rulesEngine.rule;

import java.util.function.Function;
import java.util.function.Predicate;

/**
 *
 * Created by Erik on 6/14/2017.
 */
public interface _Rule<I,O> extends Comparable<_Rule<I,O>> {

    String getName();
    String getGroup();
    int getPriority();
    Predicate<I> getPredicate();
    Function<I,O> getFunction();
    String getNextGroup();

}
