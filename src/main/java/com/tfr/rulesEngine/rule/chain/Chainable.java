package com.tfr.rulesEngine.rule.chain;

import com.tfr.rulesEngine.rule.Rule;

/**
 *
 * Created by Erik Hage on 6/16/2017.
 */
public interface Chainable<I,O> extends Rule<I,O> {

    String next();

}
