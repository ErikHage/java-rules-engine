package com.tfr.rulesEngine.rule.chain;

import com.tfr.rulesEngine.exception.DuplicateRuleException;
import org.junit.Test;

import static com.tfr.rulesEngine.testData.TestRules.*;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 *
 * Created by Erik on 6/25/2017.
 */
public class TestChainingRuleSet {

    @Test
    public void testAddRule_GivenChainingRule_ExpectSuccess() {
        ChainingRuleSet<Integer> ruleSet = new ChainingRuleSet<>("set1");
        assertTrue(ruleSet.add(CHAIN_DOUBLE_INT));
        assertTrue(ruleSet.contains(CHAIN_DOUBLE_INT));
    }

    @Test
    public void testAddRemoveRule_ExpectSuccess() {
        ChainingRuleSet<Integer> ruleSet = new ChainingRuleSet<>("set1");
        assertTrue(ruleSet.add(CHAIN_DOUBLE_INT));
        assertTrue(ruleSet.contains(CHAIN_DOUBLE_INT));
        assertTrue(ruleSet.remove(CHAIN_DOUBLE_INT));
        assertFalse(ruleSet.contains(CHAIN_DOUBLE_INT));
    }

    @Test (expected = DuplicateRuleException.class)
    public void testAddRule_GivenDuplicateRule_ExpectRuleException() {
        ChainingRuleSet<Integer> ruleSet = new ChainingRuleSet<>("set1");
        assertTrue(ruleSet.add(CHAIN_DOUBLE_INT));
        assertTrue(ruleSet.contains(CHAIN_DOUBLE_INT));
        ruleSet.add(CHAIN_DOUBLE_INT);
    }

}
