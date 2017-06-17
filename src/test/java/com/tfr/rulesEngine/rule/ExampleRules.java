package com.tfr.rulesEngine.rule;

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
}
