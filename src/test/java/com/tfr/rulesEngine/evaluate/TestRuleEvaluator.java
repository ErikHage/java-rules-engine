package com.tfr.rulesEngine.evaluate;

import com.tfr.rulesEngine.data.EvaluationResult;
import com.tfr.rulesEngine.rule._RuleSet;
import com.tfr.rulesEngine.rule.RuleSet;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.tfr.rulesEngine.testData.TestRules.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 *
 */
public class TestRuleEvaluator {

    @Test
    public void testEvaluate_GivenSingleMatch_GivenOneRuleSingle() {
        _RuleSet<Integer, List<String>> ruleSet = new RuleSet<>();
        ruleSet.add(SMALL_INT);
        runTest(ruleSet, 1, new ArrayList<>(), List.of("int<10"));
        runTest(ruleSet, 11, new ArrayList<>(), List.of());
    }

    @Test
    public void testEvaluate_GivenSingleMatch_GivenTwoRules() {
        _RuleSet<Integer, List<String>> ruleSet = new RuleSet<>();
        ruleSet.add(SMALL_INT);
        ruleSet.add(MED_INT);
        runTest(ruleSet, 1, new ArrayList<>(), List.of("int<10"));
        runTest(ruleSet, 11, new ArrayList<>(), List.of("int<100"));
        runTest(ruleSet, 101, new ArrayList<>(), List.of());
    }

    @Test
    public void testEvaluate_GivenSingleMatch_GivenThreeRules() {
        _RuleSet<Integer, List<String>> ruleSet = new RuleSet<>();
        ruleSet.add(SMALL_INT);
        ruleSet.add(MED_INT);
        ruleSet.add(LARGE_INT);
        runTest(ruleSet, 1, new ArrayList<>(), List.of("int<10"));
        runTest(ruleSet, 11, new ArrayList<>(), List.of("int<100"));
        runTest(ruleSet, 101, new ArrayList<>(), List.of("int<1000"));
        runTest(ruleSet, 1001, new ArrayList<>(), List.of());
    }

    @Test
    public void testEvaluate_GivenSingleMatch_GivenFourRules() {
        _RuleSet<Integer, List<String>> ruleSet = new RuleSet<>();
        ruleSet.add(SMALL_INT);
        ruleSet.add(MED_INT);
        ruleSet.add(LARGE_INT);
        ruleSet.add(HUGE_INT);
        runTest(ruleSet, 1, new ArrayList<>(), List.of("int<10"));
        runTest(ruleSet, 11, new ArrayList<>(), List.of("int<100"));
        runTest(ruleSet, 101, new ArrayList<>(), List.of("int<1000"));
        runTest(ruleSet, 1001, new ArrayList<>(), List.of("int>=1000"));
    }

    @Test
    public void testEvaluate_GivenSingleMatch_GivenAddingRulesWithHigherPriority_ExpectChangeInOutput() {
        _RuleSet<Integer, List<String>> ruleSet = new RuleSet<>();
        ruleSet.add(LARGE_INT);
        runTest(ruleSet, 1, new ArrayList<>(), List.of("int<1000"));
        ruleSet.add(MED_INT);
        runTest(ruleSet, 1, new ArrayList<>(), List.of("int<100"));
        ruleSet.add(SMALL_INT);
        runTest(ruleSet, 1, new ArrayList<>(), List.of("int<10"));
    }

    @Test
    public void testEvaluate_GivenMultipleGroups_Given10_Expect() {
        RuleSet<Integer, List<Integer>> ruleSet = new RuleSet<>();
        ruleSet.add(DOUBLE_INT);
        ruleSet.add(TRIPLE_INT);
        ruleSet.add(PLUS_5);
        ruleSet.add(PLUS_10);
        runTest(ruleSet, 11, new ArrayList<>(), List.of(22, 16));
        runTest(ruleSet, 10, new ArrayList<>(), List.of());
        runTest(ruleSet, 9, new ArrayList<>(), List.of(27, 19));
    }

    private <I,O> void runTest(_RuleSet<I,O> ruleSet, I input, O output, O expected) {
        _Evaluator<I,O> evaluator = new RuleEvaluator<>(ruleSet);
        EvaluationResult<I,O> result = evaluator.evaluate(input, output);

        assertEquals(input, result.getFacts().facts());
        assertEquals(expected, result.getKnowledge().value());
    }
}
