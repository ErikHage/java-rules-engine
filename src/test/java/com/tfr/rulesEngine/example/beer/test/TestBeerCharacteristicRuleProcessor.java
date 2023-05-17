package com.tfr.rulesEngine.example.beer.test;

import com.tfr.rulesEngine.example.beer.code.Beer;
import com.tfr.rulesEngine.example.beer.code.BeerCharacteristicRuleProcessor;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 *
 */
public class TestBeerCharacteristicRuleProcessor {

    @Test
    public void testProcess_GivenIPA_ExpectSuccess() {
        Beer beer = new Beer("IPA", 4.9, 65, 45, 1.076);
        runTest(beer, 3, List.of("Session","Hoppy","Malty"));
    }

    @Test
    public void testProcess_GivenRed_ExpectSuccess() {
        Beer beer = new Beer("Red", 8.0, 30, 45, 1.095);
        runTest(beer, 2, List.of("Strong","Malty"));
    }

    @Test
    public void testProcess_GivenHefeweizen_ExpectSuccess() {
        Beer beer = new Beer("Hefeweizen", 4.5, 25, 25, 1.045);
        runTest(beer, 2, List.of("Session","Light"));
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
