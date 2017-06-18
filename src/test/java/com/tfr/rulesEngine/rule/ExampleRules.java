package com.tfr.rulesEngine.rule;

import com.tfr.rulesEngine.rule.chain.ChainingRule;
import com.tfr.rulesEngine.rule.simple.SimpleRule;

/**
 *
 * Created by Erik Hage on 6/16/2017.
 */
public class ExampleRules {

    public Rule<Integer, String> smallInt = new SimpleRule<>(
            "smallInt",
            100,
            (i) -> i < 10,
            (i) -> "int<10"
    );

    public Rule<Integer, String> mediumInt = new SimpleRule<>(
            "mediumInt",
            90,
            (i) -> i < 100,
            (i) -> "int<100"
    );

    public Rule<Integer, String> largeInt = new SimpleRule<>(
            "largeInt",
            80,
            (i) -> i < 1000,
            (i) -> "int<1000"
    );

    public Rule<Integer, String> hugeInt = new SimpleRule<>(
            "hugeInt",
            70,
            (i) -> i < 10000,
            (i) -> "int<10000"
    );




    public Rule<Integer, Integer> doubleInt = new ChainingRule<>(
            "tripleInt",
            100,
            (i) -> i>10,
            (i) -> i*2,
            "set2"
    );

    public Rule<Integer, Integer> tripleInt = new ChainingRule<>(
            "tripleInt",
            150,
            (i) -> i<=10,
            (i) -> i*3,
            "set3"
    );

    public Rule<Integer, Integer> plus5 = new ChainingRule<>(
            "plus5",
            150,
            (i) -> true,
            (i) -> i + 5,
            null
    );

    public Rule<Integer, Integer> plus10 = new ChainingRule<>(
            "plus10",
            150,
            (i) -> true,
            (i) -> i + 10,
            null
    );
}
