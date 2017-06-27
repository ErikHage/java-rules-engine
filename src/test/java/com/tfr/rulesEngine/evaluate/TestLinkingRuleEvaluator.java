package com.tfr.rulesEngine.evaluate;

import com.google.common.collect.Lists;
import com.tfr.rulesEngine.rule.link.LinkingRuleSet;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static com.tfr.rulesEngine.testData.TestRules.*;
import static org.junit.Assert.assertEquals;

/**
 *
 * Created by Erik Hage on 6/17/2017.
 */
public class TestLinkingRuleEvaluator {

    private List<LinkingRuleSet<Integer, Integer>> sets;

    @Before
    public void setUp() {
        sets = Lists.newArrayList();
    }

    @Test
    public void testEvaluate_GivenLinkedSetOfRules_ExpectSuccess() {
        sets.add(SET_1);
        sets.add(SET_2);
        sets.add(SET_3);
        runTest(SET_1.getName(), sets, 11, 1, Lists.newArrayList(16));
        runTest(SET_1.getName(), sets, 9, 1, Lists.newArrayList(19));
        runTest(SET_1.getName(), sets, 10, 0, Lists.newArrayList());
    }

    private <I,O> void runTest(String initialSet, List<LinkingRuleSet<I,O>> sets, I input, int expectedOutputSize, List<O> expectedOutputs) {
        Evaluator<I,O> linkingRuleEvaluator = new LinkingRuleEvaluator<>(initialSet, sets);
        List<O> output = linkingRuleEvaluator.evaluate(input);
        assertEquals(expectedOutputSize, output.size());
        for(int i=0; i<output.size(); i++) {
            assertEquals(expectedOutputs.get(i), output.get(i));
        }
    }
}
