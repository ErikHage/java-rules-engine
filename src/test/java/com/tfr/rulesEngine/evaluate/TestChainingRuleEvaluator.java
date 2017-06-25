package com.tfr.rulesEngine.evaluate;

import com.google.common.collect.Lists;
import com.tfr.rulesEngine.rule.chain.ChainingRuleSet;
import org.junit.Before;
import org.junit.Test;

import java.util.*;

import static com.tfr.rulesEngine.testData.TestRules.*;
import static org.junit.Assert.assertEquals;

/**
 *
 * Created by Erik Hage on 6/17/2017.
 */
public class TestChainingRuleEvaluator {

    private List<ChainingRuleSet<Integer>> sets;

    @Before
    public void setUp() {
        sets = Lists.newArrayList();
    }

    @Test
    public void testEvaluate_GivenLinkedSetOfRules_ExpectSuccess() {
        sets.add(CHAIN_SET_1);
        sets.add(CHAIN_SET_2);
        sets.add(CHAIN_SET_3);
        runTest(CHAIN_SET_1.getName(), sets, 11, 1, Lists.newArrayList(27));
        runTest(CHAIN_SET_1.getName(), sets, 9, 1, Lists.newArrayList(37));
        runTest(CHAIN_SET_1.getName(), sets, 10, 0, Lists.newArrayList());
    }

    private <I> void runTest(String initialSet, List<ChainingRuleSet<I>> sets, I input, int expectedOutputSize, List<I> expectedOutputs) {
        Evaluator<I,I> linkingRuleEvaluator = new ChainingRuleEvaluator<>(initialSet, sets);
        List<I> output = linkingRuleEvaluator.evaluate(input);
        assertEquals(expectedOutputSize, output.size());
        for(int i=0; i<output.size(); i++) {
            assertEquals(expectedOutputs.get(i), output.get(i));
        }
    }

}
