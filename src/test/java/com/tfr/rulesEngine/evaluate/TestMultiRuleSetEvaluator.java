package com.tfr.rulesEngine.evaluate;

import com.google.common.collect.Lists;
import com.tfr.rulesEngine.rule.RuleSet;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static com.tfr.rulesEngine.testData.TestRules.SET_1;
import static com.tfr.rulesEngine.testData.TestRules.SET_2;
import static com.tfr.rulesEngine.testData.TestRules.SET_3;
import static org.junit.Assert.assertEquals;
import static com.tfr.rulesEngine.evaluate.Evaluator.EvaluationStyle.*;
import static org.junit.Assert.assertTrue;

/**
 *
 * Created by Erik on 6/24/2017.
 */
public class TestMultiRuleSetEvaluator {

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
        runTest(SET_1.getName(), sets, 11, 1, Lists.newArrayList(16), SINGLE_MATCH);
        runTest(SET_1.getName(), sets, 9, 1, Lists.newArrayList(19), SINGLE_MATCH);
        runTest(SET_1.getName(), sets, 10, 0, Lists.newArrayList(), SINGLE_MATCH);
    }

    @Test
    public void testEvaluate_GivenMultiMatch_GivenLinkedSetOfRules_ExpectSuccess() {
        sets.add(SET_1);
        sets.add(SET_2);
        sets.add(SET_3);
        runTest(SET_1.getName(), sets, 11, 2, Lists.newArrayList(22,16), MULTI_MATCH);
        runTest(SET_1.getName(), sets, 9, 2, Lists.newArrayList(27,19), MULTI_MATCH);
        runTest(SET_1.getName(), sets, 10, 0, Lists.newArrayList(), MULTI_MATCH);
    }

    @Test
    public void testEvaluate_GivenSingleMatchPerSet_GivenLinkedSetOfRules_ExpectSuccess() {
        sets.add(SET_1);
        sets.add(SET_2);
        sets.add(SET_3);
        runTest(SET_1.getName(), sets, 11, 3, Lists.newArrayList(22,16,22), SINGLE_MATCH_PER_SET);
        runTest(SET_1.getName(), sets, 9, 3, Lists.newArrayList(27,19,14), SINGLE_MATCH_PER_SET);
        runTest(SET_1.getName(), sets, 10, 2, Lists.newArrayList(15,20), SINGLE_MATCH_PER_SET);
    }

    private <I,O> void runTest(String initialSet, List<RuleSet<I,O>> sets, I input, int expectedOutputSize,
                               List<O> expectedOutputs, Evaluator.EvaluationStyle evaluationStyle) {
        Evaluator<I,O> linkingRuleEvaluator = new MultiRuleSetEvaluator<>(initialSet, sets, evaluationStyle);
        List<O> output = linkingRuleEvaluator.evaluate(input);
        assertEquals(expectedOutputSize, output.size());
        for(O expected: expectedOutputs) {
            assertTrue(output.contains(expected));
        }
    }

}
