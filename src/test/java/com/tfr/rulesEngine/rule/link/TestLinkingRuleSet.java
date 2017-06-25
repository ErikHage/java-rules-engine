package com.tfr.rulesEngine.rule.link;

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
public class TestLinkingRuleSet {

    @Test (expected = RuleException.class)
    public void testAddRule_GivenSimpleRule_ExpectException() {
        RuleSet<Integer, String> ruleSet = new LinkingRuleSet<>("set1");
        ruleSet.add(SMALL_INT);
    }

    @Test
    public void testAddRule_GivenLinkingRule_ExpectSuccess() {
        RuleSet<Integer, Integer> ruleSet = new LinkingRuleSet<>("set1");
        assertTrue(ruleSet.add(DOUBLE_INT));
        assertTrue(ruleSet.contains(DOUBLE_INT));
    }

    @Test
    public void testAddRule_GivenChainingRule_ExpectSuccess() {
        RuleSet<Integer, Integer> ruleSet = new LinkingRuleSet<>("set1");
        assertTrue(ruleSet.add(CHAIN_DOUBLE_INT));
        assertTrue(ruleSet.contains(CHAIN_DOUBLE_INT));
    }

    @Test
    public void testAddRemoveRule_ExpectSuccess() {
        RuleSet<Integer, Integer> ruleSet = new LinkingRuleSet<>("set1");
        assertTrue(ruleSet.add(DOUBLE_INT));
        assertTrue(ruleSet.contains(DOUBLE_INT));
        assertTrue(ruleSet.remove(DOUBLE_INT));
        assertFalse(ruleSet.contains(DOUBLE_INT));
    }

    @Test (expected = RuleException.class)
    public void testAddRule_GivenDuplicateRule_ExpectRuleException() {
        RuleSet<Integer, Integer> ruleSet = new LinkingRuleSet<>("set1");
        assertTrue(ruleSet.add(DOUBLE_INT));
        assertTrue(ruleSet.contains(DOUBLE_INT));
        ruleSet.add(DOUBLE_INT);
    }

}
