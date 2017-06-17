package com.tfr.rulesEngine.evaluate;

import com.tfr.rulesEngine.rule.ExampleRules;
import com.tfr.rulesEngine.rule.Rule;
import com.tfr.rulesEngine.rule.RuleSet;
import com.tfr.rulesEngine.rule.simple.SimpleRuleSet;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 *
 * Created by Erik Hage on 6/16/2017.
 */
public class TestMultiMatchEvaluator {

    private ExampleRules exampleRules = new ExampleRules();

    @Test
    public void testIntToStringRules() {
        RuleSet<Rule<Integer, String>> ruleSet = new SimpleRuleSet<>("intRules");
        ruleSet.add(exampleRules.smallInt);
        ruleSet.add(exampleRules.mediumInt);
        ruleSet.add(exampleRules.hugeInt);
        ruleSet.add(exampleRules.largeInt);

        MultiMatchEvaluator<Integer, String, Rule<Integer, String>> multiMatchEvaluator
                = new MultiMatchEvaluator<>(ruleSet);

        List<String> output = multiMatchEvaluator.evaluate(1);
        assertEquals(4, output.size());
        assertEquals("int<10", output.get(0));
        assertEquals("int<100", output.get(1));
        assertEquals("int<1000", output.get(2));
        assertEquals("int<10000", output.get(3));

        output = multiMatchEvaluator.evaluate(10);
        assertEquals(3, output.size());
        assertEquals("int<100", output.get(0));
        assertEquals("int<1000", output.get(1));
        assertEquals("int<10000", output.get(2));

        output = multiMatchEvaluator.evaluate(100);
        assertEquals(2, output.size());
        assertEquals("int<1000", output.get(0));
        assertEquals("int<10000", output.get(1));

        output = multiMatchEvaluator.evaluate(1000);
        assertEquals(1, output.size());
        assertEquals("int<10000", output.get(0));

    }

}
