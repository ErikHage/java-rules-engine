package com.tfr.rulesEngine.rule;

import com.tfr.rulesEngine.rule.Rule;
import com.tfr.rulesEngine.rule.RuleFactory;
import com.tfr.rulesEngine.rule.chain.ChainingRule;
import com.tfr.rulesEngine.rule.link.LinkingRule;
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
    public void testGetLinkingRule_ExpectLinkingRuleWithLink() {
        Rule<String, String> rule = RuleFactory.getLinkingRule("testRule", 100, (i) -> true, (i) -> "String", "nextRule");
        assertTrue(rule instanceof LinkingRule);
        assertEquals("nextRule", ((LinkingRule)rule).getNext());
    }

    @Test
    public void testGetChainingRule_ExpectChainingRuleWithNext() {
        Rule<String, String> rule = RuleFactory.getChainingRule("testRule", 100, (i) -> true, (i) -> "String", "nextRule");
        assertTrue(rule instanceof ChainingRule);
        assertEquals("nextRule", ((ChainingRule)rule).getNext());
    }

}
