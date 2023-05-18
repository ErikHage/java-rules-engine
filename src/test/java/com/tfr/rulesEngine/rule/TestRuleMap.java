package com.tfr.rulesEngine.rule;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static com.tfr.rulesEngine.config.Constants.DEFAULT_GROUP;
import static com.tfr.rulesEngine.testData.TestRules.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 *
 */
public class TestRuleMap {

    private _RuleMap<Integer, List<Integer>> ruleMap;

    @BeforeEach
    public void setUp() {
        _RuleSet<Integer, List<Integer>> ruleSet = new RuleSet.RuleSetBuilder<Integer, List<Integer>>()
                .addRule(DOUBLE_INT)
                .addRule(TRIPLE_INT)
                .addRule(PLUS_10)
                .addRule(PLUS_5)
                .build();
        ruleMap = new RuleMap<>(ruleSet);
    }

    @Test
    public void testGetRuleGroup_GivenDefaultSet_ExpectTwoRules() {
        _RuleSet<Integer, List<Integer>> defaultSet = ruleMap.getRuleGroup(DEFAULT_GROUP);
        assertEquals(2, defaultSet.getRules().size());
        assertTrue(defaultSet.contains(DOUBLE_INT));
        assertTrue(defaultSet.contains(TRIPLE_INT));
    }

    @Test
    public void testGetRuleGroup_GivenSet2_ExpectOneRule() {
        _RuleSet<Integer, List<Integer>> set2 = ruleMap.getRuleGroup("set2");
        assertEquals(1, set2.getRules().size());
        assertTrue(set2.contains(PLUS_5));
    }

    @Test
    public void testGetRuleGroup_GivenSet3_ExpectOneRule() {
        _RuleSet<Integer, List<Integer>> set3 = ruleMap.getRuleGroup("set3");
        assertEquals(1, set3.getRules().size());
        assertTrue(set3.contains(PLUS_10));
    }

    @Test
    public void testGetRuleGroupNames_ExpectThreeGroupNames() {
        assertEquals(3, ruleMap.getGroupNames().size());

        assertTrue(ruleMap.getGroupNames().contains(DEFAULT_GROUP));
        assertTrue(ruleMap.getGroupNames().contains("set2"));
        assertTrue(ruleMap.getGroupNames().contains("set3"));
    }

    @Test
    public void testGetNumberOfGroups_ExpectThreeGroups() {
        assertEquals(3, ruleMap.getNumberOfGroups());
    }


}
