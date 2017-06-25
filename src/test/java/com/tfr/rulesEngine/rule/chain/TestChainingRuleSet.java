package com.tfr.rulesEngine.rule.chain;

import com.tfr.rulesEngine.exception.RuleException;
import com.tfr.rulesEngine.rule.RuleSet;
import org.junit.Test;

import static com.tfr.rulesEngine.testData.TestRules.*;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 *
 * Created by Erik on 6/25/2017.
 */
public class TestChainingRuleSet {

    @Test(expected = RuleException.class)
    public void testAddRule_GivenSimpleRule_ExpectException() {
        RuleSet<Integer, Integer> ruleSet = new ChainingRuleSet<>("set1");
        ruleSet.add(SMALL_INT_INT);
    }

    @Test(expected = RuleException.class)
    public void testAddRule_GivenLinkingRule_ExpectException() {
        RuleSet<Integer, Integer> ruleSet = new ChainingRuleSet<>("set1");
        assertTrue(ruleSet.add(DOUBLE_INT));
        assertTrue(ruleSet.contains(DOUBLE_INT));
    }

    @Test
    public void testAddRule_GivenChainingRule_ExpectSuccess() {
        RuleSet<Integer, Integer> ruleSet = new ChainingRuleSet<>("set1");
        assertTrue(ruleSet.add(CHAIN_DOUBLE_INT));
        assertTrue(ruleSet.contains(CHAIN_DOUBLE_INT));
    }

    @Test
    public void testAddRemoveRule_ExpectSuccess() {
        RuleSet<Integer, Integer> ruleSet = new ChainingRuleSet<>("set1");
        assertTrue(ruleSet.add(CHAIN_DOUBLE_INT));
        assertTrue(ruleSet.contains(CHAIN_DOUBLE_INT));
        assertTrue(ruleSet.remove(CHAIN_DOUBLE_INT));
        assertFalse(ruleSet.contains(CHAIN_DOUBLE_INT));
    }

    @Test (expected = RuleException.class)
    public void testAddRule_GivenDuplicateRule_ExpectRuleException() {
        RuleSet<Integer, Integer> ruleSet = new ChainingRuleSet<>("set1");
        assertTrue(ruleSet.add(CHAIN_DOUBLE_INT));
        assertTrue(ruleSet.contains(CHAIN_DOUBLE_INT));
        ruleSet.add(CHAIN_DOUBLE_INT);
    }

}
