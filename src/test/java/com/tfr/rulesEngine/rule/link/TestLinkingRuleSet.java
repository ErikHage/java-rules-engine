package com.tfr.rulesEngine.rule.link;

import com.tfr.rulesEngine.exception.DuplicateRuleException;
import org.junit.Test;

import static com.tfr.rulesEngine.testData.TestRules.*;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 *
 * Created by Erik on 6/25/2017.
 */
public class TestLinkingRuleSet {

    @Test
    public void testAddRule_GivenLinkingRule_ExpectSuccess() {
        LinkingRuleSet<Integer, Integer> ruleSet = new LinkingRuleSet<>("set1");
        assertTrue(ruleSet.add(DOUBLE_INT));
        assertTrue(ruleSet.contains(DOUBLE_INT));
    }

    @Test
    public void testAddRule_GivenChainingRule_ExpectSuccess() {
        LinkingRuleSet<Integer, Integer> ruleSet = new LinkingRuleSet<>("set1");
        assertTrue(ruleSet.add(CHAIN_DOUBLE_INT));
        assertTrue(ruleSet.contains(CHAIN_DOUBLE_INT));
    }

    @Test
    public void testAddRemoveRule_ExpectSuccess() {
        LinkingRuleSet<Integer, Integer> ruleSet = new LinkingRuleSet<>("set1");
        assertTrue(ruleSet.add(DOUBLE_INT));
        assertTrue(ruleSet.contains(DOUBLE_INT));
        assertTrue(ruleSet.remove(DOUBLE_INT));
        assertFalse(ruleSet.contains(DOUBLE_INT));
    }

    @Test (expected = DuplicateRuleException.class)
    public void testAddRule_GivenDuplicateRule_ExpectRuleException() {
        LinkingRuleSet<Integer, Integer> ruleSet = new LinkingRuleSet<>("set1");
        assertTrue(ruleSet.add(DOUBLE_INT));
        assertTrue(ruleSet.contains(DOUBLE_INT));
        ruleSet.add(DOUBLE_INT);
    }

}
