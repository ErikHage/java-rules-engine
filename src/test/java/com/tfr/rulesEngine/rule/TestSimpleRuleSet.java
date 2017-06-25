package com.tfr.rulesEngine.rule;

import com.tfr.rulesEngine.exception.RuleException;
import com.tfr.rulesEngine.rule.simple.SimpleRuleSet;
import org.junit.Test;

import static com.tfr.rulesEngine.testData.TestRules.*;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 *
 * Created by Erik on 6/25/2017.
 */
public class TestSimpleRuleSet {

    @Test
    public void testAddRule_GivenSimpleRule_ExpectSuccess() {
        RuleSet<Integer, String> ruleSet = new SimpleRuleSet<>("set1");
        assertTrue(ruleSet.add(SMALL_INT));
        assertTrue(ruleSet.contains(SMALL_INT));
    }

    @Test
    public void testAddRule_GivenLinkingRule_ExpectSuccess() {
        RuleSet<Integer, Integer> ruleSet = new SimpleRuleSet<>("set1");
        assertTrue(ruleSet.add(DOUBLE_INT));
        assertTrue(ruleSet.contains(DOUBLE_INT));
    }

    @Test
    public void testAddRule_GivenChainingRule_ExpectSuccess() {
        RuleSet<Integer, Integer> ruleSet = new SimpleRuleSet<>("set1");
        assertTrue(ruleSet.add(CHAIN_DOUBLE_INT));
        assertTrue(ruleSet.contains(CHAIN_DOUBLE_INT));
    }

    @Test
    public void testAddRemoveRule_ExpectSuccess() {
        RuleSet<Integer, String> ruleSet = new SimpleRuleSet<>("set1");
        assertTrue(ruleSet.add(SMALL_INT));
        assertTrue(ruleSet.contains(SMALL_INT));
        assertTrue(ruleSet.remove(SMALL_INT));
        assertFalse(ruleSet.contains(SMALL_INT));
    }

    @Test (expected = RuleException.class)
    public void testAddRule_GivenDuplicateRule_ExpectRuleException() {
        RuleSet<Integer, String> ruleSet = new SimpleRuleSet<>("set1");
        assertTrue(ruleSet.add(SMALL_INT));
        assertTrue(ruleSet.contains(SMALL_INT));
        ruleSet.add(SMALL_INT);
    }



}
