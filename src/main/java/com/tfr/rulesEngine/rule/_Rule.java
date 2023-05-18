package com.tfr.rulesEngine.rule;

import com.tfr.rulesEngine.data.EvaluationResult;

import java.util.function.Consumer;
import java.util.function.Predicate;

/**
 *
 */
public interface _Rule<I,O> extends Comparable<_Rule<I,O>> {

    String getName();
    String getGroupName();
    String getNextGroupName();

    int getPriority();

    Predicate<I> getMatchCondition();
    boolean testMatchCondition(I input);

    Consumer<EvaluationResult<I,O>> getOnMatchHandler();
    void applyOnMatchHandler(EvaluationResult<I,O> evaluationResult);

}
