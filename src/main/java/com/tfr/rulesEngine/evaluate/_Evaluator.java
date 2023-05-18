package com.tfr.rulesEngine.evaluate;

import com.tfr.rulesEngine.data.EvaluationResult;

/**
 *
 */
public interface _Evaluator<I,O> {

    EvaluationResult<I,O> evaluate(I input, O output);
}
