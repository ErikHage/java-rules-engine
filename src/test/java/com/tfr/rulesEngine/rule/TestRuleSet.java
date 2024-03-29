package com.tfr.rulesEngine.rule;

import com.tfr.rulesEngine.exception.DuplicateRuleException;
import org.junit.jupiter.api.Test;

import java.util.Iterator;
import java.util.List;

import static com.tfr.rulesEngine.testData.TestRules.*;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 */
public class TestRuleSet {

    @Test
    public void testAddRule_GivenSimpleRule_ExpectSuccess() {
        _RuleSet<Integer, List<String>> ruleSet = new RuleSet<>();
        assertTrue(ruleSet.add(SMALL_INT));
        assertTrue(ruleSet.contains(SMALL_INT));
    }

    @Test
    public void testAddRemoveRule_ExpectSuccess() {
        _RuleSet<Integer, List<String>> ruleSet = new RuleSet<>();
        assertTrue(ruleSet.add(SMALL_INT));
        assertTrue(ruleSet.contains(SMALL_INT));
        assertTrue(ruleSet.remove(SMALL_INT));
        assertFalse(ruleSet.contains(SMALL_INT));
    }

    @Test
    public void testAddRule_GivenDuplicateRule_ExpectRuleException() {
        _RuleSet<Integer, List<String>> ruleSet = new RuleSet<>();
        assertTrue(ruleSet.add(SMALL_INT));
        assertTrue(ruleSet.contains(SMALL_INT));

        assertThrows(DuplicateRuleException.class, () -> {
            ruleSet.add(SMALL_INT);
        });
    }

    @Test
    public void testIterator_PriorityOrder() {
        _RuleSet<Integer, List<String>> ruleSet = new RuleSet<>();
        ruleSet.add(SMALL_INT);
        ruleSet.add(HUGE_INT);
        ruleSet.add(LARGE_INT);
        ruleSet.add(MED_INT);

        Iterator<_Rule<Integer, List<String>>> iterator = ruleSet.iterator();
        assertEquals(SMALL_INT, iterator.next());
        assertEquals(MED_INT, iterator.next());
        assertEquals(LARGE_INT, iterator.next());
        assertEquals(HUGE_INT, iterator.next());
        assertFalse(iterator.hasNext());
    }


    /* Test Builder */
    @Test
    public void testBuildSimpleRuleSet_GivenOneRule_ExpectSuccess() {
        _RuleSet<Integer, List<String>> ruleSet;
        ruleSet = new RuleSet.RuleSetBuilder<Integer, List<String>>()
                .addRule(SMALL_INT)
                .build();

        assertNotNull(ruleSet);
        assertTrue(ruleSet.contains(SMALL_INT));
    }

    @Test
    public void testBuildSimpleRuleSet_GivenMultipleRuleTypes_ExpectSuccess() {
        _RuleSet<Integer, List<Integer>> ruleSet;
        ruleSet = new RuleSet.RuleSetBuilder<Integer, List<Integer>>()
                .addRule(SMALL_INT_INT)
                .addRule(DOUBLE_INT)
                .build();

        assertNotNull(ruleSet);
        assertTrue(ruleSet.contains(SMALL_INT_INT));
        assertTrue(ruleSet.contains(DOUBLE_INT));
    }

    @Test
    public void testBuildSimpleRuleSet_GivenDuplicateRule_ExpectException() {
        assertThrows(DuplicateRuleException.class, () -> {
            new RuleSet.RuleSetBuilder<Integer, List<String>>()
                    .addRule(SMALL_INT)
                    .addRule(SMALL_INT)
                    .build();
        });
    }



}
