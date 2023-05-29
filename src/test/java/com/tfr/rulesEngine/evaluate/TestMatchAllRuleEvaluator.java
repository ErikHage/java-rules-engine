package com.tfr.rulesEngine.evaluate;

import com.tfr.rulesEngine.data.AuditEntry;
import com.tfr.rulesEngine.rule.RuleSet;
import com.tfr.rulesEngine.rule._RuleSet;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.tfr.rulesEngine.config.Constants.DEFAULT_GROUP;
import static com.tfr.rulesEngine.testData.TestRules.*;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestMatchAllRuleEvaluator {

    private final boolean notMatched = false;
    private final boolean matched = true;

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
        runTest(ruleSet, 1, new ArrayList<>(), List.of("int<10", "int<100"));
        runTest(ruleSet, 11, new ArrayList<>(), List.of("int<100"));
        runTest(ruleSet, 101, new ArrayList<>(), List.of());
    }

    @Test
    public void testEvaluate_GivenSingleMatch_GivenThreeRules() {
        _RuleSet<Integer, List<String>> ruleSet = new RuleSet<>();
        ruleSet.add(SMALL_INT);
        ruleSet.add(MED_INT);
        ruleSet.add(LARGE_INT);
        runTest(ruleSet, 1, new ArrayList<>(), List.of("int<10", "int<100","int<1000" ));
        runTest(ruleSet, 11, new ArrayList<>(), List.of("int<100", "int<1000"));
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
        runTest(ruleSet, 1, new ArrayList<>(), List.of("int<10", "int<100", "int<1000"));
        runTest(ruleSet, 11, new ArrayList<>(), List.of("int<100", "int<1000"));
        runTest(ruleSet, 101, new ArrayList<>(), List.of("int<1000"));
        runTest(ruleSet, 1001, new ArrayList<>(), List.of("int>=1000"));
    }

    @Test
    public void testEvaluate_GivenSingleMatch_GivenAddingRulesWithHigherPriority_ExpectChangeInOutput() {
        _RuleSet<Integer, List<String>> ruleSet = new RuleSet<>();
        ruleSet.add(LARGE_INT);
        runTest(ruleSet, 1, new ArrayList<>(), List.of("int<1000"));
        ruleSet.add(MED_INT);
        runTest(ruleSet, 1, new ArrayList<>(), List.of("int<100", "int<1000"));
        ruleSet.add(SMALL_INT);
        runTest(ruleSet, 1, new ArrayList<>(), List.of("int<10", "int<100", "int<1000"));
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

    @Test
    public void testEvaluate_GivenMultipleNextGroups_ExpectMultipleMatches() {
        RuleSet<String, Map<String,String>> ruleSet = new RuleSet<>();
        ruleSet.add(HAS_COLOR);
        ruleSet.add(HAS_SIZE);
        ruleSet.add(IS_LARGE);
        ruleSet.add(IS_SMALL);
        ruleSet.add(IS_RED);
        ruleSet.add(IS_BLUE);

        runTest(ruleSet, "color is red, size is large", new HashMap<>(), Map.of(
                "color", "red",
                "size", "large"
        ));
        runTest(ruleSet, "color is blue, size is small", new HashMap<>(), Map.of(
                "color", "blue",
                "size", "small"
        ));
        runTest(ruleSet, "color is red, and item is medium", new HashMap<>(), Map.of(
                "color", "red"
        ));
    }

    @Test
    public void testEvaluate_GivenMultipleGroups_ExpectAuditTrail() {
        RuleSet<String, List<String>> ruleSet = new RuleSet<>();
        ruleSet.add(LONG_STRING_RULE);
        ruleSet.add(SHORT_STRING_RULE);
        ruleSet.add(HAS_CHAR_A_RULE);

        var evaluator = new MatchAllRuleEvaluator<>(ruleSet);
        var result = evaluator.evaluate("abcdef", new ArrayList<>());

        var expectedAuditTrail = new ArrayList<AuditEntry>();
        expectedAuditTrail.add(new AuditEntry(DEFAULT_GROUP, LONG_STRING_RULE.getName(), notMatched, null));
        expectedAuditTrail.add(new AuditEntry(DEFAULT_GROUP, SHORT_STRING_RULE.getName(), matched, "[<10 chars]"));
        expectedAuditTrail.add(new AuditEntry(DEFAULT_GROUP, HAS_CHAR_A_RULE.getName(), matched, "[<10 chars, contains 'a' or 'A']"));

        assertEquals("abcdef", result.getFacts().value());
        assertEquals(List.of("<10 chars", "contains 'a' or 'A'"), result.getKnowledge().value());
        assertEquals(3, result.getAuditTrail().size());
        assertEquals(2, result.getNumberOfMatches());
        assertEquals(expectedAuditTrail, result.getAuditTrail());
    }

    private <I,O> void runTest(_RuleSet<I,O> ruleSet, I input, O output, O expected) {
        var evaluator = new MatchAllRuleEvaluator<>(ruleSet);
        var result = evaluator.evaluate(input, output);

        assertEquals(input, result.getFacts().value());
        assertEquals(expected, result.getKnowledge().value());
    }
}
