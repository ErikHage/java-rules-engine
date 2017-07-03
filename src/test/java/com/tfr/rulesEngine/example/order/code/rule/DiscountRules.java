package com.tfr.rulesEngine.example.order.code.rule;

/**
 *
 *
 * Created by Erik on 7/3/2017.
 */
public interface DiscountRules {

    DiscountRule LABOR_DAY_SALE = new DiscountRule("laborDaySale", 0, o -> true, o -> o);

}
