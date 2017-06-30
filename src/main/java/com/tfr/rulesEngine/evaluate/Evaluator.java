package com.tfr.rulesEngine.evaluate;

import java.util.List;

/**
 *
 * Created by Erik Hage on 6/17/2017.
 */
public interface Evaluator<I,O> {

    enum EvaluationStyle {
        SINGLE_MATCH,
        SINGLE_MATCH_PER_SET,
        MULTI_MATCH
    }

    List<O> evaluate(I input);

    EvaluationStyle getEvaluationStyle();

}
