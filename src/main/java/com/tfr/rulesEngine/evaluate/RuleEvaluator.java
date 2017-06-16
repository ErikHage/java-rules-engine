package com.tfr.rulesEngine.evaluate;

/**
 *
 * Created by Erik on 6/15/2017.
 */
public interface RuleEvaluator<I,O> {

    O evaluate(I input);

}
