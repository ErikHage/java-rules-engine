package com.tfr.rulesEngine.evaluate;

import com.google.common.collect.Lists;
import com.tfr.rulesEngine.rule.RuleSet;
import org.junit.Before;
import org.junit.Test;

import java.util.*;

import static com.tfr.rulesEngine.testData.TestRules.*;
import static org.junit.Assert.*;
import static com.tfr.rulesEngine.evaluate.Evaluator.EvaluationStyle.*;

/**
 *
 * Created by Erik Hage on 6/17/2017.
 */
public class TestChainingRuleEvaluator {

    private List<RuleSet<Integer, Integer>> sets;

    @Before
    public void setUp() {
        sets = Lists.newArrayList();
    }

    @Test
    public void testEvaluate_GivenSingleMatch_GivenLinkedSetOfRules_ExpectSuccess() {
        sets.add(SET_1);
        sets.add(SET_2);
        sets.add(SET_3);
        runTest(SET_1.getName(), sets, 11, 1, Lists.newArrayList(27), SINGLE_MATCH);
        runTest(SET_1.getName(), sets, 9, 1, Lists.newArrayList(37), SINGLE_MATCH);
        runTest(SET_1.getName(), sets, 10, 0, Lists.newArrayList(), SINGLE_MATCH);
    }

    @Test
    public void testEvaluate_GivenSingleMatchPerSet_GivenLinkedSetOfRules_ExpectSuccess() {
        sets.add(SET_1);
        sets.add(SET_2);
        sets.add(SET_3);
        runTest(SET_1.getName(), sets, 11, 3, Lists.newArrayList(22,16,21), SINGLE_MATCH_PER_SET);
        runTest(SET_1.getName(), sets, 9, 3, Lists.newArrayList(27,19,14), SINGLE_MATCH_PER_SET);
        runTest(SET_1.getName(), sets, 10, 2, Lists.newArrayList(15,20), SINGLE_MATCH_PER_SET);
    }

    @Test
    public void testEvaluate_GivenMultiMatch_GivenLinkedSetOfRules_ExpectSuccess() {
        sets.add(SET_1);
        sets.add(SET_2);
        sets.add(SET_3);
        runTest(SET_1.getName(), sets, 11, 2, Lists.newArrayList(22,27), MULTI_MATCH);
        runTest(SET_1.getName(), sets, 9, 2, Lists.newArrayList(27,37), MULTI_MATCH);
        runTest(SET_1.getName(), sets, 10, 0, Lists.newArrayList(), MULTI_MATCH);
    }

    private <I> void runTest(String initialSet, List<RuleSet<I,I>> sets, I input, int expectedOutputSize,
                             List<I> expectedOutputs, Evaluator.EvaluationStyle evaluationStyle) {
        Evaluator<I,I> linkingRuleEvaluator = new ChainingRuleEvaluator<>(initialSet, sets, evaluationStyle);
        List<I> output = linkingRuleEvaluator.evaluate(input);
        assertEquals(expectedOutputSize, output.size());
        for(I expected : expectedOutputs) {
            assertTrue(output.contains(expected));
        }
    }

}
