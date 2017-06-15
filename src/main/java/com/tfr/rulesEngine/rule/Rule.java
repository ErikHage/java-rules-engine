package com.tfr.rulesEngine.rule;

import java.util.function.Function;
import java.util.function.Predicate;

/**
 *
 * Created by Erik on 6/14/2017.
 */
public interface Rule<I,O> {

    boolean evaluate(I input);
    O getResult(I input);

    String getName();
    int getPriority();
    Predicate<I> getPredicate();
    Function<I,O> getFunction();

}
