package com.tfr.rulesEngine.rule;

import com.tfr.rulesEngine.rule.simple.SimpleRule;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 *
 * Created by Erik on 6/25/2017.
 */
public class TestRuleFactory {

    @Test
    public void testGetRule_ExpectSimpleRuleWithPriority0() {
        Rule<String, String> rule = RuleFactory.getRule("testRule", (i) -> true, (i) -> "String");
        assertTrue(rule instanceof SimpleRule);
        assertEquals(0, rule.getPriority());
    }

    @Test
    public void testGetRule_ExpectSimpleRuleWithPriority100() {
        Rule<String, String> rule = RuleFactory.getRule("testRule", 100, (i) -> true, (i) -> "String");
        assertTrue(rule instanceof SimpleRule);
        assertEquals(100, rule.getPriority());
    }

    @Test
    public void testGetRule_ExpectSimpleRuleWithPriority100NextABC() {
        Rule<String, String> rule = RuleFactory.getRule("testRule", 100, (i) -> true, (i) -> "String", "ABC");
        assertTrue(rule instanceof SimpleRule);
        assertEquals(100, rule.getPriority());
        assertEquals("ABC", rule.getNext());
    }


}
