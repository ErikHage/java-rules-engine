package com.tfr.rulesEngine.evaluate;

import java.util.List;

/**
 *
 * Created by Erik Hage on 6/17/2017.
 */
public interface Evaluator<I,O> {

    List<O> evaluate(I input);

}
