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
    public void testProcess_GivenJuly4thNYOneClothingItem_ExpectDiscountAndTaxable() {
        Order order = new Order(LocalDate.of(2017, 7, 4));
        order.addItem(new Item("Shirt", 20.0, Item.Type.CLOTHING, "Men's Shirt"));
        order.setProperty("STATE", "NY");

        runTest(order, 1, "Y", "Y");
        assertEquals(SUMMER_SALE_DEAL, order.getDiscounts().get(0));
    }

    @Test
    public void testProcess_GivenJuly4thNYTwoFoodItems_ExpectDiscountAndNotTaxable() {
        Order order = new Order(LocalDate.of(2017, 7, 4));
        order.addItem(new Item("Apples", 3.0, Item.Type.FOOD, "Gala"));
        order.addItem(new Item("Apples", 5.0, Item.Type.FOOD, "Honeycrisp"));
        order.setProperty("STATE", "NY");

        runTest(order, 1, "Y", "N");
        assertEquals(SUMMER_SALE_DEAL, order.getDiscounts().get(0));
    }

    @Test
    public void testProcess_GivenJune10thNYTwoFoodItems_ExpectNoDiscountAndNotTaxable() {
        Order order = new Order(LocalDate.of(2017, 6, 10));
        order.addItem(new Item("Apples", 3.0, Item.Type.FOOD, "Gala"));
        order.addItem(new Item("Apples", 5.0, Item.Type.FOOD, "Honeycrisp"));
        order.setProperty("STATE", "NY");

        runTest(order, 0, "Y", "N");
    }

    @Test
    public void testProcess_GivenJune10thOKTwoFoodItems_ExpectNoDiscountAndNotInScopeForTax() {
        Order order = new Order(LocalDate.of(2017, 6, 10));
        order.addItem(new Item("Apples", 3.0, Item.Type.FOOD, "Gala"));
        order.addItem(new Item("Apples", 5.0, Item.Type.FOOD, "Honeycrisp"));
        order.setProperty("STATE", "OK");

        runTest(order, 0, "N", null);
    }


    private Order runTest(Order order, int expectedDiscountNumber, String expectedInScopeForTax,
                          String expectedContainsTaxableProducts) {
        processor.process(order);

        System.out.println(order);

        assertEquals(expectedDiscountNumber, order.getDiscounts().size());
        assertEquals(expectedInScopeForTax, order.getProperty("IN_SCOPE_FOR_TAX"));
        assertEquals(expectedContainsTaxableProducts, order.getProperty("CONTAINS_TAXABLE_PRODUCTS"));

        return order;
    }

}
