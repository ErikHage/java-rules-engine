package com.tfr.rulesEngine.evaluate;

import com.tfr.rulesEngine.rule.ExampleRules;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 *
 * Created by Erik on 6/22/2017.
 */
public class TestSingleRuleEvaluator {

    private ExampleRules exampleRules = new ExampleRules();

    @Test
    public void testEvaluate_GivenIntIntRuleInput20_ExpectOutput40() {
        Evaluator<Integer, Integer> singleRuleEvaluator = new SingleRuleEvaluator<>(exampleRules.doubleInt);
        List<Integer> output = singleRuleEvaluator.evaluate(20);
        assertEquals(1, output.size());
        assertEquals(40, output.get(0), 0.1);
    }

    @Test
    public void testEvaluate_GivenIntIntRuleInput20_ExpectNoOutput() {
        Evaluator<Integer, Integer> singleRuleEvaluator = new SingleRuleEvaluator<>(exampleRules.doubleInt);
        List<Integer> output = singleRuleEvaluator.evaluate(10);
        assertEquals(0, output.size());
    }

}
