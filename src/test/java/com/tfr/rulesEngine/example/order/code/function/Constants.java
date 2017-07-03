package com.tfr.rulesEngine.example.order.code.function;

import com.google.common.collect.Sets;

import java.util.Set;
/**
 *
 * Created by Erik on 7/3/2017.
 */
public interface Constants {

    Set<String> STATES_IN_TAX_SCOPE = Sets.newHashSet(
            "NY","NJ","FL","PA","DE","WA","TX","CA"
    );

}
