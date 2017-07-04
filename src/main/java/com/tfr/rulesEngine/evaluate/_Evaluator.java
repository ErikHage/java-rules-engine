package com.tfr.rulesEngine.evaluate;

import java.util.List;
import java.util.Optional;

/**
 *
 * Created by Erik Hage on 6/17/2017.
 */
public interface _Evaluator<I,O> {

    Optional<O> evaluate(I input);

    List<O> evaluateMulti(I input);

}
