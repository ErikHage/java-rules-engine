package com.tfr.rulesEngine.example.order.test;

import com.tfr.rulesEngine.example.order.code.model.Item;
import com.tfr.rulesEngine.example.order.code.model.Order;
import com.tfr.rulesEngine.example.order.code.processor.OrderRuleProcessor;
import com.tfr.rulesEngine.example.order.code.rule.SpringRuleConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;

import static com.tfr.rulesEngine.example.order.code.function.Constants.*;
import static org.junit.Assert.assertEquals;

/**
 *
 * Created by Erik on 7/3/2017.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@Import(SpringRuleConfig.class)
public class TestOrderRuleProcessor {

    @Autowired
    private OrderRuleProcessor processor;

    @Test
    public void test() {
        Order order = new Order(LocalDate.of(2017, 7, 4));
        order.addItem(new Item("Shirt", 20.0, Item.Type.CLOTHING, "Men's Shirt"));
        order.setProperty("STATE", "NY");

        processor.process(order);
        System.out.println(order);

        assertEquals(1, order.getDiscounts().size());
        assertEquals(SUMMER_SALE_DEAL, order.getDiscounts().get(0));
        assertEquals("Y", order.getProperty("IN_SCOPE_FOR_TAX"));
        assertEquals("NY", order.getProperty("STATE"));
        assertEquals("Y", order.getProperty("CONTAINS_TAXABLE_PRODUCTS"));
    }

}
