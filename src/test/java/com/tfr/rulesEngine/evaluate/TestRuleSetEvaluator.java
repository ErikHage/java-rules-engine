package com.tfr.rulesEngine.evaluate;

import com.tfr.rulesEngine.rule._RuleSet;
import com.tfr.rulesEngine.rule.RuleSet;
import org.junit.Test;

import java.util.Optional;

import static com.tfr.rulesEngine.testData.TestRules.*;
import static org.junit.Assert.*;

/**
 *
 * Created by Erik on 6/29/2017.
 */
public class TestRuleSetEvaluator {

    @Test
    public void testEvaluate_GivenSingleMatch_GivenOneRuleSingle() {
        _RuleSet<Integer, String> ruleSet = new RuleSet<>();
        ruleSet.add(SMALL_INT);
        runTest(ruleSet, 1, "int<10");
        runTest(ruleSet, 11, null);
    }

    @Test
    public void testEvaluate_GivenSingleMatch_GivenTwoRules() {
        _RuleSet<Integer, String> ruleSet = new RuleSet<>();
        ruleSet.add(SMALL_INT);
        ruleSet.add(MED_INT);
        runTest(ruleSet, 1, "int<10");
        runTest(ruleSet, 11, "int<100");
        runTest(ruleSet, 101, null);
    }

    @Test
    public void testEvaluate_GivenSingleMatch_GivenThreeRules() {
        _RuleSet<Integer, String> ruleSet = new RuleSet<>();
        ruleSet.add(SMALL_INT);
        ruleSet.add(MED_INT);
        ruleSet.add(LARGE_INT);
        runTest(ruleSet, 1, "int<10");
        runTest(ruleSet, 11, "int<100");
        runTest(ruleSet, 101, "int<1000");
        runTest(ruleSet, 1001, null);
    }

    @Test
    public void testEvaluate_GivenSingleMatch_GivenFourRules() {
        _RuleSet<Integer, String> ruleSet = new RuleSet<>();
        ruleSet.add(SMALL_INT);
        ruleSet.add(MED_INT);
        ruleSet.add(LARGE_INT);
        ruleSet.add(HUGE_INT);
        runTest(ruleSet, 1, "int<10");
        runTest(ruleSet, 11, "int<100");
        runTest(ruleSet, 101, "int<1000");
        runTest(ruleSet, 1001, "int>=1000");
    }

    @Test
    public void testEvaluate_GivenSingleMatch_GivenAddingRulesWithHigherPriority_ExpectChangeInOutput() {
        _RuleSet<Integer, String> ruleSet = new RuleSet<>();
        ruleSet.add(LARGE_INT);
        runTest(ruleSet, 1, "int<1000");
        ruleSet.add(MED_INT);
        runTest(ruleSet, 1, "int<100");
        ruleSet.add(SMALL_INT);
        runTest(ruleSet, 1, "int<10");
    }

    @Test
    public void testEvaluate_GivenMultipleGroups_Given10_Expect() {
        RuleSet<Integer, Integer> ruleSet = new RuleSet<>();
        ruleSet.add(DOUBLE_INT);
        ruleSet.add(TRIPLE_INT);
        ruleSet.add(PLUS_5);
        ruleSet.add(PLUS_10);
        runTest(ruleSet, 11, 16);
        runTest(ruleSet, 10, null);
        runTest(ruleSet, 9, 19);
    }

    private <I,O> void runTest(_RuleSet<I,O> ruleSet, I input, O expected) {
        Optional<O> expectedOutput = Optional.ofNullable(expected);

        _Evaluator<I,O> evaluator = new RuleEvaluator<>(ruleSet);
        Optional<O> output = evaluator.evaluate(input);

        if(expectedOutput.isPresent() && output.isPresent()) {
            assertEquals(expectedOutput.get(), output.get());
        }
        //else both empty, so ok
    }


}
