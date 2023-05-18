package com.tfr.rulesEngine.rule;

import org.junit.jupiter.api.Test;

import java.util.Optional;
import java.util.function.BiConsumer;
import java.util.function.Function;
import java.util.function.Predicate;

import static com.tfr.rulesEngine.config.Constants.*;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 */
public class TestRule {

    private final Predicate<Integer> predicateTest = i -> true;
    private final BiConsumer<Integer, Integer> consumerTest = (i,o) -> {};

    @Test
    public void testRuleBuilder_GivenRequiredFields_ExpectRule() {
        _Rule<Integer, Integer> rule = new Rule.RuleBuilder<Integer, Integer>("name", predicateTest)
                .onMatchHandler(consumerTest)
                .build();
        assertEquals("name", rule.getName());
        assertEquals(predicateTest, rule.getMatchCondition());
        assertNotNull(rule.getOnMatchHandler());
        assertEquals(DEFAULT_GROUP, rule.getGroupName());
        assertEquals(TERMINAL_GROUP, rule.getNextGroupName());
        assertEquals(DEFAULT_PRIORITY, rule.getPriority());
    }

    @Test
    public void testRuleBuilder_GivenRequiredFieldsAndPriority_ExpectRule() {
        _Rule<Integer, Integer> rule = new Rule.RuleBuilder<Integer, Integer>("name", predicateTest)
                .onMatchHandler(consumerTest)
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
                .onMatchHandler(consumerTest)
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
                .onMatchHandler(consumerTest)
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
                .onMatchHandler(consumerTest)
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

    @Test
    public void testEquals_GivenRulesWithSameName_ExpectEquals() {
        _Rule<Integer, Integer> rule1 = new Rule.RuleBuilder<Integer, Integer>("name", predicateTest)
                .build();
        _Rule<Integer, Integer> rule2 = new Rule.RuleBuilder<Integer, Integer>("name", predicateTest)
                .build();

        assertEquals(rule1, rule2);
    }

    @Test
    public void testEquals_GivenRulesWithDifferentName_ExpectNotEquals() {
        _Rule<Integer, Integer> rule1 = new Rule.RuleBuilder<Integer, Integer>("name1", predicateTest)
                .build();
        _Rule<Integer, Integer> rule2 = new Rule.RuleBuilder<Integer, Integer>("name2", predicateTest)
                .build();

        assertNotEquals(rule1, rule2);
    }
}
