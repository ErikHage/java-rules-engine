package com.tfr.rulesEngine.example.test.rule;

import com.tfr.rulesEngine.example.model.Beer;
import com.tfr.rulesEngine.example.rule.BeerStyleRuleProcessor;
import org.junit.Test;

import java.util.List;

import static com.tfr.rulesEngine.example.model.Styles.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 *
 * Created by Erik on 6/27/2017.
 */
public class TestBeerStyleRuleProcessor {

    private BeerStyleRuleProcessor beerStyleRuleProcessor = new BeerStyleRuleProcessor();

    @Test
    public void testProcess_GivenChecksAndBalances_ExpectIpa() {
        Beer checksAndBalances = new Beer("Checks and Balances IPA", 6.5, 65, 45, 1.065);
        List<String> output = beerStyleRuleProcessor.process(checksAndBalances);
        assertEquals(1, output.size());
        assertEquals(IPA.getName(), output.get(0));
    }

    @Test
    public void testProcess_GivenRosieRedAle_ExpectAmberAndRed() {
        Beer rosieRedAle = new Beer("Rosie's Red Ale", 5.5, 35, 65, 1.055);
        List<String> output = beerStyleRuleProcessor.process(rosieRedAle);
        assertEquals(2, output.size());
        assertTrue(output.contains(AMBER.getName()));
        assertTrue(output.contains(RED.getName()));
    }

}
