package com.tfr.rulesEngine.rule;

import org.junit.jupiter.api.Test;

import java.util.function.Function;
import java.util.function.Predicate;

import static com.tfr.rulesEngine.config.Constants.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 *
 */
public class TestRule {

    private Predicate<Integer> predicateTest = i -> true;
    private Function<Integer, Integer> functionTest = i -> 2;

    @Test
    public void testRuleBuilder_GivenRequiredFields_ExpectRule() {
        _Rule<Integer, Integer> rule = new Rule.RuleBuilder<Integer, Integer>("name", predicateTest)
                .onMatchHandler(functionTest)
                .build();
        assertEquals("name", rule.getName());
        assertEquals(predicateTest, rule.getMatchCondition());
        assertEquals(DEFAULT_GROUP, rule.getGroupName());
        assertEquals(TERMINAL_GROUP, rule.getNextGroupName());
        assertEquals(DEFAULT_PRIORITY, rule.getPriority());
    }

    @Test
    public void testRuleBuilder_GivenRequiredFieldsAndPriority_ExpectRule() {
        _Rule<Integer, Integer> rule = new Rule.RuleBuilder<Integer, Integer>("name", predicateTest)
                .onMatchHandler(functionTest)
                .priority(100)
                .build();
        assertEquals("name", rule.getName());
        assertEquals(predicateTest, rule.getMatchCondition());
        assertEquals(DEFAULT_GROUP, rule.getGroupName());
        assertEquals(TERMINAL_GROUP, rule.getNextGroupName());
        assertEquals(100, rule.getPriority());
    }

    @Test
    public void testRuleBuilder_GivenRequiredFieldsAndGroup_ExpectRule() {
        _Rule<Integer, Integer> rule = new Rule.RuleBuilder<Integer, Integer>("name", predicateTest)
                .onMatchHandler(functionTest)
                .groupName("some group")
                .build();
        assertEquals("name", rule.getName());
        assertEquals(predicateTest, rule.getMatchCondition());
        assertEquals("some group", rule.getGroupName());
        assertEquals(TERMINAL_GROUP, rule.getNextGroupName());
        assertEquals(DEFAULT_PRIORITY, rule.getPriority());
    }

    @Test
    public void testRuleBuilder_GivenRequiredFieldsAndNextGroup_ExpectRule() {
        _Rule<Integer, Integer> rule = new Rule.RuleBuilder<Integer, Integer>("name", predicateTest)
                .onMatchHandler(functionTest)
                .nextGroupName("another group")
                .build();
        assertEquals("name", rule.getName());
        assertEquals(predicateTest, rule.getMatchCondition());
        assertEquals(DEFAULT_GROUP, rule.getGroupName());
        assertEquals("another group", rule.getNextGroupName());
        assertEquals(DEFAULT_PRIORITY, rule.getPriority());
    }

    @Test
    public void testRuleBuilder_GivenRequiredFieldsAndOptionalFields_ExpectRule() {
        _Rule<Integer, Integer> rule = new Rule.RuleBuilder<Integer, Integer>("name", predicateTest)
                .onMatchHandler(functionTest)
                .groupName("some group")
                .priority(100)
                .nextGroupName("another group")
                .build();
        assertEquals("name", rule.getName());
        assertEquals(predicateTest, rule.getMatchCondition());
        assertEquals("some group", rule.getGroupName());
        assertEquals("another group", rule.getNextGroupName());
        assertEquals(100, rule.getPriority());
    }
}
