package com.tfr.rulesEngine.evaluate;

import com.tfr.rulesEngine.rule._RuleSet;
import com.tfr.rulesEngine.rule.RuleSet;
import org.junit.jupiter.api.Test;

import java.util.List;

import static com.tfr.rulesEngine.testData.TestRules.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 *
 */
public class TestRuleEvaluator {

    @Test
    public void testEvaluate_GivenSingleMatch_GivenOneRuleSingle() {
        _RuleSet<Integer, String> ruleSet = new RuleSet<>();
        ruleSet.add(SMALL_INT);
        runTest(ruleSet, 1, List.of("int<10"));
        runTest(ruleSet, 11, null);
    }

    @Test
    public void testEvaluate_GivenSingleMatch_GivenTwoRules() {
        _RuleSet<Integer, String> ruleSet = new RuleSet<>();
        ruleSet.add(SMALL_INT);
        ruleSet.add(MED_INT);
        runTest(ruleSet, 1, List.of("int<10"));
        runTest(ruleSet, 11, List.of("int<100"));
        runTest(ruleSet, 101, null);
    }

    @Test
    public void testEvaluate_GivenSingleMatch_GivenThreeRules() {
        _RuleSet<Integer, String> ruleSet = new RuleSet<>();
        ruleSet.add(SMALL_INT);
        ruleSet.add(MED_INT);
        ruleSet.add(LARGE_INT);
        runTest(ruleSet, 1, List.of("int<10"));
        runTest(ruleSet, 11, List.of("int<100"));
        runTest(ruleSet, 101, List.of("int<1000"));
        runTest(ruleSet, 1001, null);
    }

    @Test
    public void testEvaluate_GivenSingleMatch_GivenFourRules() {
        _RuleSet<Integer, String> ruleSet = new RuleSet<>();
        ruleSet.add(SMALL_INT);
        ruleSet.add(MED_INT);
        ruleSet.add(LARGE_INT);
        ruleSet.add(HUGE_INT);
        runTest(ruleSet, 1, List.of("int<10"));
        runTest(ruleSet, 11, List.of("int<100"));
        runTest(ruleSet, 101, List.of("int<1000"));
        runTest(ruleSet, 1001, List.of("int>=1000"));
    }

    @Test
    public void testEvaluate_GivenSingleMatch_GivenAddingRulesWithHigherPriority_ExpectChangeInOutput() {
        _RuleSet<Integer, String> ruleSet = new RuleSet<>();
        ruleSet.add(LARGE_INT);
        runTest(ruleSet, 1, List.of("int<1000"));
        ruleSet.add(MED_INT);
        runTest(ruleSet, 1, List.of("int<100"));
        ruleSet.add(SMALL_INT);
        runTest(ruleSet, 1, List.of("int<10"));
    }

    @Test
    public void testEvaluate_GivenMultipleGroups_Given10_Expect() {
        RuleSet<Integer, Integer> ruleSet = new RuleSet<>();
        ruleSet.add(DOUBLE_INT);
        ruleSet.add(TRIPLE_INT);
        ruleSet.add(PLUS_5);
        ruleSet.add(PLUS_10);
        runTest(ruleSet, 11, List.of(22, 16));
        runTest(ruleSet, 10, List.of());
        runTest(ruleSet, 9, List.of(27, 19));
    }

    private <I,O> void runTest(_RuleSet<I,O> ruleSet, I input, List<O> expected) {
        _Evaluator<I,O> evaluator = new RuleEvaluator<>(ruleSet);
        List<O> output = evaluator.evaluate(input);

        if(output.size() > 0) {
            assertEquals(expected, output);
        }
        //else both empty, so ok
    }


}
