package com.tfr.rulesEngine.rule.simple;

import com.tfr.rulesEngine.exception.DuplicateRuleException;
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
        SimpleRuleSet<Integer, String> ruleSet = new SimpleRuleSet<>("set1");
        assertTrue(ruleSet.add(SMALL_INT));
        assertTrue(ruleSet.contains(SMALL_INT));
    }

    @Test
    public void testAddRule_GivenLinkingRule_ExpectSuccess() {
        SimpleRuleSet<Integer, Integer> ruleSet = new SimpleRuleSet<>("set1");
        assertTrue(ruleSet.add(DOUBLE_INT));
        assertTrue(ruleSet.contains(DOUBLE_INT));
    }

    @Test
    public void testAddRule_GivenChainingRule_ExpectSuccess() {
        SimpleRuleSet<Integer, Integer> ruleSet = new SimpleRuleSet<>("set1");
        assertTrue(ruleSet.add(CHAIN_DOUBLE_INT));
        assertTrue(ruleSet.contains(CHAIN_DOUBLE_INT));
    }

    @Test
    public void testAddRemoveRule_ExpectSuccess() {
        SimpleRuleSet<Integer, String> ruleSet = new SimpleRuleSet<>("set1");
        assertTrue(ruleSet.add(SMALL_INT));
        assertTrue(ruleSet.contains(SMALL_INT));
        assertTrue(ruleSet.remove(SMALL_INT));
        assertFalse(ruleSet.contains(SMALL_INT));
    }

    @Test (expected = DuplicateRuleException.class)
    public void testAddRule_GivenDuplicateRule_ExpectRuleException() {
        SimpleRuleSet<Integer, String> ruleSet = new SimpleRuleSet<>("set1");
        assertTrue(ruleSet.add(SMALL_INT));
        assertTrue(ruleSet.contains(SMALL_INT));
        ruleSet.add(SMALL_INT);
    }



}
