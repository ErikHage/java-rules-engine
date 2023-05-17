package com.tfr.rulesEngine.evaluate;

import java.util.List;
import java.util.Optional;

/**
 *
 */
public interface _Evaluator<I,O> {

    List<O> evaluate(I input);
}
