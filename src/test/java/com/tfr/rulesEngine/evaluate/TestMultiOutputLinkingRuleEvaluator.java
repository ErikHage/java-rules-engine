package com.tfr.rulesEngine.evaluate;

import com.google.common.collect.Lists;
import com.tfr.rulesEngine.rule.RuleSet;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static com.tfr.rulesEngine.testData.TestRules.SET_1;
import static com.tfr.rulesEngine.testData.TestRules.SET_2;
import static com.tfr.rulesEngine.testData.TestRules.SET_3;
import static org.junit.Assert.assertEquals;

/**
 *
 * Created by Erik on 6/24/2017.
 */
public class TestMultiOutputLinkingRuleEvaluator {

    private List<RuleSet<Integer, Integer>> sets;

    @Before
    public void setUp() {
        sets = Lists.newArrayList();
    }

    @Test
    public void testEvaluate_GivenLinkedSetOfRules_ExpectSuccess() {
        sets.add(SET_1);
        sets.add(SET_2);
        sets.add(SET_3);
        runTest(SET_1.getName(), sets, 11, 2, Lists.newArrayList(22, 16));
        runTest(SET_1.getName(), sets, 9, 2, Lists.newArrayList(27, 19));
        runTest(SET_1.getName(), sets, 10, 0, Lists.newArrayList());
    }

    private <I,O> void runTest(String initialSet, List<RuleSet<I,O>> sets, I input, int expectedOutputSize, List<O> expectedOutputs) {
        Evaluator<I,O> linkingRuleEvaluator = new MultiOutputLinkingRuleEvaluator<>(initialSet, sets);
        List<O> output = linkingRuleEvaluator.evaluate(input);
        assertEquals(expectedOutputSize, output.size());
        for(int i=0; i<output.size(); i++) {
            assertEquals(expectedOutputs.get(i), output.get(i));
        }
    }

}
