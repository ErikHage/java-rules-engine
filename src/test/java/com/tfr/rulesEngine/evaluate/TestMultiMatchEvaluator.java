package com.tfr.rulesEngine.evaluate;

import com.google.common.collect.Lists;
import com.tfr.rulesEngine.rule.simple.SimpleRuleSet;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static com.tfr.rulesEngine.testData.TestRules.*;
import static org.junit.Assert.assertEquals;

/**
 *
 * Created by Erik Hage on 6/16/2017.
 */
public class TestMultiMatchEvaluator {

    private SimpleRuleSet<Integer, String> ruleSet;

    @Before
    public void setUp() {
        ruleSet = new SimpleRuleSet<>("intRules");
    }

    @Test
    public void testEvaluate_GivenOneRule() {
        ruleSet.add(SMALL_INT);
        runTest(ruleSet, 1, 1, Lists.newArrayList("int<10"));
        runTest(ruleSet, 11, 0, Lists.newArrayList());
    }

    @Test
    public void testEvaluate_GivenTwoRules() {
        ruleSet.add(SMALL_INT);
        ruleSet.add(MED_INT);
        runTest(ruleSet, 1, 2, Lists.newArrayList("int<10","int<100"));
        runTest(ruleSet, 11, 1, Lists.newArrayList("int<100"));
        runTest(ruleSet, 101, 0, Lists.newArrayList());
    }

    @Test
    public void testEvaluate_GivenThreeRules() {
        ruleSet.add(SMALL_INT);
        ruleSet.add(MED_INT);
        ruleSet.add(LARGE_INT);
        runTest(ruleSet, 1, 3, Lists.newArrayList("int<10","int<100","int<1000"));
        runTest(ruleSet, 11, 2, Lists.newArrayList("int<100","int<1000"));
        runTest(ruleSet, 101, 1, Lists.newArrayList("int<1000"));
        runTest(ruleSet, 1001, 0, Lists.newArrayList());
    }

    @Test
    public void testEvaluate_GivenFourRules() {
        ruleSet.add(SMALL_INT);
        ruleSet.add(MED_INT);
        ruleSet.add(LARGE_INT);
        ruleSet.add(HUGE_INT);
        runTest(ruleSet, 1, 3, Lists.newArrayList("int<10","int<100","int<1000"));
        runTest(ruleSet, 11, 2, Lists.newArrayList("int<100","int<1000"));
        runTest(ruleSet, 101, 1, Lists.newArrayList("int<1000"));
        runTest(ruleSet, 1001, 1, Lists.newArrayList("int>=1000"));
    }

    @Test
    public void testEvaluate_GivenAddingRulesWithHigherPriority_ExpectChangeInOutput() {
        ruleSet.add(LARGE_INT);
        runTest(ruleSet, 1, 1, Lists.newArrayList("int<1000"));
        ruleSet.add(MED_INT);
        runTest(ruleSet, 1, 2, Lists.newArrayList("int<100","int<1000"));
        ruleSet.add(SMALL_INT);
        runTest(ruleSet, 1, 3, Lists.newArrayList("int<10","int<100","int<1000"));
    }

    private <I,O> void runTest(SimpleRuleSet<I,O> ruleSet, I input, int expectedOutputSize, List<O> expectedOutput) {
        Evaluator<I,O> firstMatchEvaluator = new MultiMatchEvaluator<>(ruleSet);
        List<O> output = firstMatchEvaluator.evaluate(input);
        assertEquals(expectedOutputSize, output.size());
        for(int i=0; i<expectedOutput.size(); i++) {
            assertEquals(expectedOutput.get(i), output.get(i));
        }
    }

}
