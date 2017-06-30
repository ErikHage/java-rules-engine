package com.tfr.rulesEngine.evaluate;

import com.google.common.collect.Lists;
import com.tfr.rulesEngine.rule.RuleSet;
import com.tfr.rulesEngine.rule.simple.SimpleRuleSet;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static com.tfr.rulesEngine.testData.TestRules.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 *
 * Created by Erik on 6/29/2017.
 */
public class TestRuleSetEvaluator {

    private RuleSet<Integer, String> ruleSet;

    @Before
    public void setUp() {
        ruleSet = new SimpleRuleSet<>("intRules");
    }

    @Test
    public void testEvaluate_GivenSingleMatch_GivenOneRuleSingle() {
        ruleSet.add(SMALL_INT);
        runTestSingleMatch(ruleSet, 1, 1, "int<10");
        runTestSingleMatch(ruleSet, 11, 0, null);
    }

    @Test
    public void testEvaluate_GivenSingleMatch_GivenTwoRules() {
        ruleSet.add(SMALL_INT);
        ruleSet.add(MED_INT);
        runTestSingleMatch(ruleSet, 1, 1, "int<10");
        runTestSingleMatch(ruleSet, 11, 1, "int<100");
        runTestSingleMatch(ruleSet, 101, 0, null);
    }

    @Test
    public void testEvaluate_GivenSingleMatch_GivenThreeRules() {
        ruleSet.add(SMALL_INT);
        ruleSet.add(MED_INT);
        ruleSet.add(LARGE_INT);
        runTestSingleMatch(ruleSet, 1, 1, "int<10");
        runTestSingleMatch(ruleSet, 11, 1, "int<100");
        runTestSingleMatch(ruleSet, 101, 1, "int<1000");
        runTestSingleMatch(ruleSet, 1001, 0, null);
    }

    @Test
    public void testEvaluate_GivenSingleMatch_GivenFourRules() {
        ruleSet.add(SMALL_INT);
        ruleSet.add(MED_INT);
        ruleSet.add(LARGE_INT);
        ruleSet.add(HUGE_INT);
        runTestSingleMatch(ruleSet, 1, 1, "int<10");
        runTestSingleMatch(ruleSet, 11, 1, "int<100");
        runTestSingleMatch(ruleSet, 101, 1, "int<1000");
        runTestSingleMatch(ruleSet, 1001, 1, "int>=1000");
    }

    @Test
    public void testEvaluate_GivenSingleMatch_GivenAddingRulesWithHigherPriority_ExpectChangeInOutput() {
        ruleSet.add(LARGE_INT);
        runTestSingleMatch(ruleSet, 1, 1, "int<1000");
        ruleSet.add(MED_INT);
        runTestSingleMatch(ruleSet, 1, 1, "int<100");
        ruleSet.add(SMALL_INT);
        runTestSingleMatch(ruleSet, 1, 1, "int<10");
    }

    @Test
    public void testEvaluate_GivenMultiMatch_GivenOneRule() {
        ruleSet.add(SMALL_INT);
        runTestMultiMatch(ruleSet, 1, 1, Lists.newArrayList("int<10"));
        runTestMultiMatch(ruleSet, 11, 0, Lists.newArrayList());
    }

    @Test
    public void testEvaluate_GivenMultiMatch_GivenTwoRules() {
        ruleSet.add(SMALL_INT);
        ruleSet.add(MED_INT);
        runTestMultiMatch(ruleSet, 1, 2, Lists.newArrayList("int<10","int<100"));
        runTestMultiMatch(ruleSet, 11, 1, Lists.newArrayList("int<100"));
        runTestMultiMatch(ruleSet, 101, 0, Lists.newArrayList());
    }

    @Test
    public void testEvaluate_GivenMultiMatch_GivenThreeRules() {
        ruleSet.add(SMALL_INT);
        ruleSet.add(MED_INT);
        ruleSet.add(LARGE_INT);
        runTestMultiMatch(ruleSet, 1, 3, Lists.newArrayList("int<10","int<100","int<1000"));
        runTestMultiMatch(ruleSet, 11, 2, Lists.newArrayList("int<100","int<1000"));
        runTestMultiMatch(ruleSet, 101, 1, Lists.newArrayList("int<1000"));
        runTestMultiMatch(ruleSet, 1001, 0, Lists.newArrayList());
    }

    @Test
    public void testEvaluate_GivenMultiMatch_GivenFourRules() {
        ruleSet.add(SMALL_INT);
        ruleSet.add(MED_INT);
        ruleSet.add(LARGE_INT);
        ruleSet.add(HUGE_INT);
        runTestMultiMatch(ruleSet, 1, 3, Lists.newArrayList("int<10","int<100","int<1000"));
        runTestMultiMatch(ruleSet, 11, 2, Lists.newArrayList("int<100","int<1000"));
        runTestMultiMatch(ruleSet, 101, 1, Lists.newArrayList("int<1000"));
        runTestMultiMatch(ruleSet, 1001, 1, Lists.newArrayList("int>=1000"));
    }

    @Test
    public void testEvaluate_GivenMultiMatch_GivenAddingRulesWithHigherPriority_ExpectChangeInOutput() {
        ruleSet.add(LARGE_INT);
        runTestMultiMatch(ruleSet, 1, 1, Lists.newArrayList("int<1000"));
        ruleSet.add(MED_INT);
        runTestMultiMatch(ruleSet, 1, 2, Lists.newArrayList("int<100","int<1000"));
        ruleSet.add(SMALL_INT);
        runTestMultiMatch(ruleSet, 1, 3, Lists.newArrayList("int<10","int<100","int<1000"));
    }

    @Test
    public void testEvaluate_GivenSingleMatchPerSet_GivenOneRuleSingle() {
        ruleSet.add(SMALL_INT);
        runTestSingleMatchPerSet(ruleSet, 1, 1, "int<10");
        runTestSingleMatchPerSet(ruleSet, 11, 0, null);
    }

    @Test
    public void testEvaluate_GivenSingleMatchPerSet_GivenTwoRules() {
        ruleSet.add(SMALL_INT);
        ruleSet.add(MED_INT);
        runTestSingleMatchPerSet(ruleSet, 1, 1, "int<10");
        runTestSingleMatchPerSet(ruleSet, 11, 1, "int<100");
        runTestSingleMatchPerSet(ruleSet, 101, 0, null);
    }

    @Test
    public void testEvaluate_GivenSingleMatchPerSet_GivenThreeRules() {
        ruleSet.add(SMALL_INT);
        ruleSet.add(MED_INT);
        ruleSet.add(LARGE_INT);
        runTestSingleMatchPerSet(ruleSet, 1, 1, "int<10");
        runTestSingleMatchPerSet(ruleSet, 11, 1, "int<100");
        runTestSingleMatchPerSet(ruleSet, 101, 1, "int<1000");
        runTestSingleMatchPerSet(ruleSet, 1001, 0, null);
    }

    @Test
    public void testEvaluate_GivenSingleMatchPerSet_GivenFourRules() {
        ruleSet.add(SMALL_INT);
        ruleSet.add(MED_INT);
        ruleSet.add(LARGE_INT);
        ruleSet.add(HUGE_INT);
        runTestSingleMatchPerSet(ruleSet, 1, 1, "int<10");
        runTestSingleMatchPerSet(ruleSet, 11, 1, "int<100");
        runTestSingleMatchPerSet(ruleSet, 101, 1, "int<1000");
        runTestSingleMatchPerSet(ruleSet, 1001, 1, "int>=1000");
    }

    @Test
    public void testEvaluate_GivenSingleMatchPerSet_GivenAddingRulesWithHigherPriority_ExpectChangeInOutput() {
        ruleSet.add(LARGE_INT);
        runTestSingleMatchPerSet(ruleSet, 1, 1, "int<1000");
        ruleSet.add(MED_INT);
        runTestSingleMatchPerSet(ruleSet, 1, 1, "int<100");
        ruleSet.add(SMALL_INT);
        runTestSingleMatchPerSet(ruleSet, 1, 1, "int<10");
    }


    private <I,O> void runTestSingleMatch(RuleSet<I,O> ruleSet, I input, int expectedOutputSize, O expectedOutput) {
        List<O> expectedOutputs = new ArrayList<>();
        if(expectedOutput != null)
            expectedOutputs.add(expectedOutput);
        runTest(ruleSet, input, expectedOutputSize, expectedOutputs, Evaluator.EvaluationStyle.SINGLE_MATCH);
    }

    private <I,O> void runTestSingleMatchPerSet(RuleSet<I,O> ruleSet, I input, int expectedOutputSize, O expectedOutput) {
        List<O> expectedOutputs = new ArrayList<>();
        if(expectedOutput != null)
            expectedOutputs.add(expectedOutput);
        runTest(ruleSet, input, expectedOutputSize, expectedOutputs, Evaluator.EvaluationStyle.SINGLE_MATCH_PER_SET);
    }

    private <I,O> void runTestMultiMatch(RuleSet<I,O> ruleSet, I input, int expectedOutputSize, List<O> expectedOutput) {
        runTest(ruleSet, input, expectedOutputSize, expectedOutput, Evaluator.EvaluationStyle.MULTI_MATCH);
    }

    private <I,O> void runTest(RuleSet<I,O> ruleSet, I input, int expectedOutputSize, List<O> expectedOutput,
                               Evaluator.EvaluationStyle evaluationStyle) {
        Evaluator<I,O> firstMatchEvaluator = new RuleSetEvaluator<>(ruleSet, evaluationStyle);
        List<O> output = firstMatchEvaluator.evaluate(input);
        assertEquals(expectedOutputSize, output.size());
        for(O expected : expectedOutput) {
            assertTrue(output.contains(expected));
        }
    }


}
