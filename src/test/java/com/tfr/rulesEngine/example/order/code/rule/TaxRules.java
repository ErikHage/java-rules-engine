package com.tfr.rulesEngine.example.order.code.rule;

import static com.tfr.rulesEngine.example.order.code.function.Constants.STATES_IN_TAX_SCOPE;

/**
 *
 * Created by Erik on 7/3/2017.
 */
public interface TaxRules {

    TaxRule STATE_RULE = new TaxRule("stateRule", 100,
            (o) -> STATES_IN_TAX_SCOPE.contains(o.getProperty("State")),
            (o) -> {
                o.setProperty("IN_SCOPE_FOR_TAX", "Y");
                return o;
            });



}
