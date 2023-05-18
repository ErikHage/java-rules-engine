package com.tfr.rulesEngine.example.beer.test;

import com.tfr.rulesEngine.example.beer.code.Beer;
import com.tfr.rulesEngine.example.beer.code.BeerStyleRuleProcessor;
import org.junit.jupiter.api.Test;

import java.util.List;

import static com.tfr.rulesEngine.example.beer.code.Styles.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 *
 */
public class TestBeerStyleRuleProcessor {

    private final BeerStyleRuleProcessor beerStyleRuleProcessor = new BeerStyleRuleProcessor();

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

    @Test
    public void testProcess_GivenSteamboatPorter_ExpectEmptyOutput() {
        Beer rosieRedAle = new Beer("Steamboat Porter", 6.4, 23, 150, 1.075);
        List<String> output = beerStyleRuleProcessor.process(rosieRedAle);
        assertEquals(0, output.size());
    }

}