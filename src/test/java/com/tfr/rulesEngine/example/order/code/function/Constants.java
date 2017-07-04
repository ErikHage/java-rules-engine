package com.tfr.rulesEngine.example.order.code.function;

import com.google.common.collect.Sets;
import com.tfr.rulesEngine.example.order.code.model.Discount;
import com.tfr.rulesEngine.example.order.code.model.Item;

import java.time.LocalDate;
import java.util.Set;
/**
 *
 * Created by Erik on 7/3/2017.
 */
public interface Constants {

    Set<String> STATES_IN_TAX_SCOPE = Sets.newHashSet(
            "NY","NJ","FL","PA","DE","WA","TX","CA"
    );

    Set<Item.Type> TAXABLE_PRODUCT_TYPES = Sets.newHashSet(
            Item.Type.CLOTHING,
            Item.Type.HOUSEWARES,
            Item.Type.KITCHEN,
            Item.Type.TOOLS);


    LocalDate LABOR_DAY = LocalDate.of(2017, 9, 4);
    Discount LABOR_DAY_DEAL = new Discount("20% off sale for Labor day only", 20.0, Discount.Type.PERCENTAGE);


    LocalDate SUMMER_SALE_START = LocalDate.of(2017, 7, 1);
    LocalDate SUMMER_SALE_END = LocalDate.of(2017, 7, 31);
    Discount SUMMER_SALE_DEAL = new Discount("10% off orders of 5 or more items all July", 10.0, Discount.Type.PERCENTAGE);




}
