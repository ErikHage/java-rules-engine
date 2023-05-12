package com.tfr.rulesEngine.example.beer.test;

import com.google.common.collect.Lists;
import com.tfr.rulesEngine.example.beer.code.Beer;
import com.tfr.rulesEngine.example.beer.code.BeerCharacteristicRuleProcessor;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 *
 * Created by Erik on 6/29/2017.
 */
public class TestBeerCharacteristicRuleProcessor {

    @Test
    public void testProcess_GivenIPA_ExpectSuccess() {
        Beer beer = new Beer("IPA", 4.9, 65, 45, 1.076);
        runTest(beer, 3, Lists.newArrayList("Session","Hoppy","Malty"));
    }

    @Test
    public void testProcess_GivenRed_ExpectSuccess() {
        Beer beer = new Beer("Red", 8.0, 30, 45, 1.095);
        runTest(beer, 2, Lists.newArrayList("Strong","Malty"));
    }

    @Test
    public void testProcess_GivenHefeweizen_ExpectSuccess() {
        Beer beer = new Beer("Hefeweizen", 4.5, 25, 25, 1.045);
        runTest(beer, 2, Lists.newArrayList("Session","Light"));
    }

    private void runTest(Beer beer, int expectedOutputSize, List<String> expectedOutput) {
        BeerCharacteristicRuleProcessor ruleProcessor = new BeerCharacteristicRuleProcessor();
        List<String> output = ruleProcessor.process(beer);
        assertEquals(expectedOutputSize, output.size());
        for(String s : expectedOutput) {
            assertTrue(output.contains(s));
        }
    }

}
