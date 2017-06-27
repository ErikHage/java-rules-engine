package com.tfr.rulesEngine.rule.simple;

import com.tfr.rulesEngine.exception.RuleException;
import com.tfr.rulesEngine.rule.RuleSet;
import org.junit.Test;

import static com.tfr.rulesEngine.testData.TestRules.*;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 *
 * Created by Erik on 6/25/2017.
 */
public class TestSimpleRuleSetBuilder {

    @Test
    public void testBuildSimpleRuleSet_GivenOneRule_ExpectSuccess() {
        RuleSet<Integer, String, SimpleRule<Integer, String>> ruleSet;
        ruleSet = new SimpleRuleSetBuilder<Integer, String>("testSet")
                .addRule(SMALL_INT)
                .build();

        assertNotNull(ruleSet);
        assertTrue(ruleSet.contains(SMALL_INT));
    }

    @Test(expected = RuleException.class)
    public void testBuildSimpleRuleSet_GivenDuplicateRule_ExpectException() {
        new SimpleRuleSetBuilder<Integer, String>("testSet")
                .addRule(SMALL_INT)
                .addRule(SMALL_INT)
                .build();
    }

    @Test
    public void testBuildSimpleRuleSet_GivenTwoRules() {
        RuleSet<Integer, String, SimpleRule<Integer, String>> ruleSet;
        ruleSet = new SimpleRuleSetBuilder<Integer, String>("testSet")
                .addRule(SMALL_INT)
//                .addRule(TEN_CHAR_RULE) //TODO: works :-)
                .build();

        assertNotNull(ruleSet);
        assertTrue(ruleSet.contains(SMALL_INT));
    }

}

