package com.tfr.rulesEngine.rule;

import org.junit.jupiter.api.Test;

import java.util.function.Function;
import java.util.function.Predicate;

import static com.tfr.rulesEngine.config.Constants.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 *
 * Created by Erik on 7/3/2017.
 */
public class TestRule {

    private Predicate<Integer> predicateTest = i -> true;
    private Function<Integer, Integer> functionTest = i -> 2;

    @Test
    public void testRuleBuilder_GivenRequiredFields_ExpectRule() {
        _Rule<Integer, Integer> rule = new Rule.RuleBuilder<Integer, Integer>("name", predicateTest)
                .function(functionTest)
                .build();
        assertEquals("name", rule.getName());
        assertEquals(predicateTest, rule.getPredicate());
        assertEquals(DEFAULT_GROUP, rule.getGroup());
        assertEquals(TERMINAL_GROUP, rule.getNextGroup());
        assertEquals(DEFAULT_PRIORITY, rule.getPriority());
    }

    @Test
    public void testRuleBuilder_GivenRequiredFieldsAndPriority_ExpectRule() {
        _Rule<Integer, Integer> rule = new Rule.RuleBuilder<Integer, Integer>("name", predicateTest)
                .function(functionTest)
                .priority(100)
                .build();
        assertEquals("name", rule.getName());
        assertEquals(predicateTest, rule.getPredicate());
        assertEquals(DEFAULT_GROUP, rule.getGroup());
        assertEquals(TERMINAL_GROUP, rule.getNextGroup());
        assertEquals(100, rule.getPriority());
    }

    @Test
    public void testRuleBuilder_GivenRequiredFieldsAndGroup_ExpectRule() {
        _Rule<Integer, Integer> rule = new Rule.RuleBuilder<Integer, Integer>("name", predicateTest)
                .function(functionTest)
                .group("some group")
                .build();
        assertEquals("name", rule.getName());
        assertEquals(predicateTest, rule.getPredicate());
        assertEquals("some group", rule.getGroup());
        assertEquals(TERMINAL_GROUP, rule.getNextGroup());
        assertEquals(DEFAULT_PRIORITY, rule.getPriority());
    }

    @Test
    public void testRuleBuilder_GivenRequiredFieldsAndNextGroup_ExpectRule() {
        _Rule<Integer, Integer> rule = new Rule.RuleBuilder<Integer, Integer>("name", predicateTest)
                .function(functionTest)
                .nextGroup("another group")
                .build();
        assertEquals("name", rule.getName());
        assertEquals(predicateTest, rule.getPredicate());
        assertEquals(DEFAULT_GROUP, rule.getGroup());
        assertEquals("another group", rule.getNextGroup());
        assertEquals(DEFAULT_PRIORITY, rule.getPriority());
    }

    @Test
    public void testRuleBuilder_GivenRequiredFieldsAndOptionalFields_ExpectRule() {
        _Rule<Integer, Integer> rule = new Rule.RuleBuilder<Integer, Integer>("name", predicateTest)
                .function(functionTest)
                .group("some group")
                .priority(100)
                .nextGroup("another group")
                .build();
        assertEquals("name", rule.getName());
        assertEquals(predicateTest, rule.getPredicate());
        assertEquals("some group", rule.getGroup());
        assertEquals("another group", rule.getNextGroup());
        assertEquals(100, rule.getPriority());
    }
}
