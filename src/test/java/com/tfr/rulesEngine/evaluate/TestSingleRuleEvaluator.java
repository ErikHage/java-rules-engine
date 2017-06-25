package com.tfr.rulesEngine.evaluate;

import com.tfr.rulesEngine.rule.Rule;
import org.junit.Test;

import java.util.List;

import static com.tfr.rulesEngine.testData.TestRules.*;
import static org.junit.Assert.assertEquals;

/**
 *
 * Created by Erik on 6/22/2017.
 */
public class TestSingleRuleEvaluator {

    @Test
    public void testEvaluate_GivenLongRuleStringLength14_ExpectOutputStringHasMoreThan10Characters() {
        runTest(LONG_STRING_RULE, "My long string", 1, "String has more than 10 characters");
    }

    @Test
    public void testEvaluate_GivenLongRuleStringLength9_ExpectNoOutput() {
        runTest(LONG_STRING_RULE, "My 9 stri", 0, null);
    }

    @Test
    public void testEvaluate_Given10CharRuleStringLength10_ExpectOutputStringHasExactly10Characters() {
        runTest(TEN_CHAR_RULE, "My10string", 1, "String has exactly 10 characters");
    }

    @Test
    public void testEvaluate_Given10CharRuleStringLength9_ExpectNoOutput() {
        runTest(LONG_STRING_RULE, "My9string", 0, null);
    }

    @Test
    public void testEvaluate_GivenShortRuleStringLength5_ExpectOutputStringHasLessThan10Characters() {
        runTest(SHORT_STRING_RULE, "Hello", 1, "String has less than 10 characters");
    }

    @Test
    public void testEvaluate_GivenShortRuleStringLength15_ExpectOutputStringHasLessThan10Characters() {
        runTest(SHORT_STRING_RULE, "Hello World!!!!", 0, null);
    }

    @Test
    public void testEvaluate_GivenIntEquals10Rule10_ExpectTrue() {
        runTest(INT_EQUALS_10, 10, 1, true);
    }

    @Test
    public void testEvaluate_GivenIntEquals10Rule8_ExpectFalse() {
        runTest(INT_EQUALS_10, 1, 0, null);
    }

    @Test
    public void testEvaluate_GivenIntEquals1Rule10_ExpectTrue() {
        runTest(INT_EQUALS_1, 10, 0, null);
    }

    @Test
    public void testEvaluate_GivenIntEquals1Rule1_ExpectTrue() {
        runTest(INT_EQUALS_1, 1, 1, true);
    }

    private <I,O> void runTest(Rule<I,O> rule, I input, int expectedOutputSize, O expectedOutput) {
        Evaluator<I, O> singleRuleEvaluator = new SingleRuleEvaluator<>(rule);
        List<O> output = singleRuleEvaluator.evaluate(input);
        assertEquals(expectedOutputSize, output.size());
        if(expectedOutput != null) {
            assertEquals(expectedOutput, output.get(0));
        }
    }

}
