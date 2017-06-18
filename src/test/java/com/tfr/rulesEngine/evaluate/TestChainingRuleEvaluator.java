package com.tfr.rulesEngine.evaluate;

import com.google.common.collect.Lists;
import com.tfr.rulesEngine.rule.ExampleRules;
import com.tfr.rulesEngine.rule.chain.ChainingRuleSet;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 *
 * Created by Erik Hage on 6/17/2017.
 */
public class TestChainingRuleEvaluator {

    private ExampleRules exampleRules = new ExampleRules();

    @Test
    public void test() {
        ChainingRuleSet<Integer, Integer> set1 = new ChainingRuleSet<>("set1");
        set1.add(exampleRules.doubleInt);
        set1.add(exampleRules.tripleInt);
        ChainingRuleSet<Integer, Integer> set2 = new ChainingRuleSet<>("set2");
        set2.add(exampleRules.plus5);
        ChainingRuleSet<Integer, Integer> set3 = new ChainingRuleSet<>("set3");
        set3.add(exampleRules.plus10);

        List<ChainingRuleSet<Integer, Integer>> sets = Lists.newArrayList();
        sets.add(set1);
        sets.add(set2);
        sets.add(set3);

        ChainingRuleEvaluator<Integer, Integer> chainingRuleEvaluator =
                new ChainingRuleEvaluator<>("set1", sets);

        List<Integer> output;
        output = chainingRuleEvaluator.evaluate(10);
        assertEquals(30, output.get(0), 0.1);
        assertEquals(20, output.get(1), 0.1);

        output = chainingRuleEvaluator.evaluate(20);
        assertEquals(40, output.get(0), 0.1);
        assertEquals(25, output.get(1), 0.1);
    }

}
