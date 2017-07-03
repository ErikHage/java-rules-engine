package com.tfr.rulesEngine.example.beer.test;

import com.google.common.collect.Lists;
import com.tfr.rulesEngine.example.beer.code.Beer;
import com.tfr.rulesEngine.example.beer.code.BeerCharacteristicRuleProcessor;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 *
 * Created by Erik on 6/29/2017.
 */
public class TestBeerCharacteristicRuleProcessor {

    @Test
    public void testProcess_GivenIPA_ExpectSuccess() {
        Beer beer = new Beer("IPA", 4.9, 65, 45, 1.076);
        runTest(beer, 4, Lists.newArrayList("Session","Hoppy","Red","Malty"));
    }

    @Test
    public void testProcess_GivenRed_ExpectSuccess() {
        Beer beer = new Beer("Red", 8.0, 30, 45, 1.095);
        runTest(beer, 3, Lists.newArrayList("Strong","Red","Malty"));
    }

    @Test
    public void testProcess_GivenHefeweizen_ExpectSuccess() {
        Beer beer = new Beer("Hefeweizen", 4.5, 25, 25, 1.045);
        runTest(beer, 2, Lists.newArrayList("Session","Light"));
    }

    private void runTest(Beer beer, int expectedOutputSize, List<String> expectedOutput) {
        BeerCharacteristicRuleProcessor beerCharacteristicRuleProcessor = new BeerCharacteristicRuleProcessor();
        List<String> output = beerCharacteristicRuleProcessor.process(beer);
        assertEquals(expectedOutputSize, output.size());
        for(String s : expectedOutput) {
            assertTrue(output.contains(s));
        }
    }

}
