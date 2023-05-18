package com.tfr.rulesEngine.testData;

import com.tfr.rulesEngine.rule._Rule;
import com.tfr.rulesEngine.rule.Rule;

import java.util.List;

/**
 *
 */
public interface TestRules {

//    _Rule<String, String> LONG_STRING_RULE = new Rule.RuleBuilder<String,String>(
//            "longString",
//            (i) -> i.length() > 10)
//            .onMatchHandler((i) -> "String has more than 10 characters")
//            .build();
//
//    _Rule<String, String> SHORT_STRING_RULE = new Rule.RuleBuilder<String,String>(
//            "shortString",
//            (i) -> i.length() < 10)
//            .onMatchHandler((i) -> "String has less than 10 characters")
//            .build();
//
//    _Rule<String, String> TEN_CHAR_RULE = new Rule.RuleBuilder<String,String>(
//            "tenChar",
//            (i) -> i.length() == 10)
//            .onMatchHandler((i) -> "String has exactly 10 characters")
//            .build();
//
//
//
//    _Rule<Integer, Boolean> INT_EQUALS_10 = new Rule.RuleBuilder<Integer, Boolean>(
//            "intEquals10",
//            (i) -> i==10)
//            .onMatchHandler((i) -> true)
//            .build();
//
//    _Rule<Integer, Boolean> INT_EQUALS_1 = new Rule.RuleBuilder<Integer, Boolean>(
//            "intEquals1",
//            (i) -> i==1)
//            .onMatchHandler((i) -> true)
//            .build();



    _Rule<Integer, List<String>> SMALL_INT = new Rule.RuleBuilder<Integer,List<String>>(
            "smallInt",
            (i) -> i < 10)
            .onMatchHandler((i,o) -> {
                 o.add("int<10");
            })
            .priority(100)
            .build();

    _Rule<Integer, List<String>> MED_INT = new Rule.RuleBuilder<Integer,List<String>>(
            "mediumInt",
            (i) -> i < 100)
            .onMatchHandler((i,o) -> {
                o.add("int<100");
            })
            .priority(90)
            .build();

    _Rule<Integer, List<String>> LARGE_INT = new Rule.RuleBuilder<Integer,List<String>>(
            "largeInt",
            (i) -> i < 1000)
            .onMatchHandler((i,o) -> {
                o.add("int<1000");
            })
            .priority(80)
            .build();

    _Rule<Integer, List<String>> HUGE_INT = new Rule.RuleBuilder<Integer,List<String>>(
            "hugeInt",
            (i) -> i >= 1000)
            .onMatchHandler((i,o) -> {
                o.add("int>=1000");
            })
            .priority(70)
            .build();



    _Rule<Integer, List<Integer>> SMALL_INT_INT = new Rule.RuleBuilder<Integer, List<Integer>>(
            "smallIntInt",
            (i) -> i < 10)
            .onMatchHandler((i,o) -> {
                o.add(i);
            })
            .priority(100)
            .build();


    _Rule<Integer, List<Integer>> DOUBLE_INT = new Rule.RuleBuilder<Integer, List<Integer>>(
            "doubleInt",
            (i) -> i>10)
            .onMatchHandler((i,o) -> {
                o.add(i*2);
            })
            .priority(100)
            .nextGroupName("set2")
            .build();

    _Rule<Integer, List<Integer>> TRIPLE_INT = new Rule.RuleBuilder<Integer, List<Integer>>(
            "tripleInt",
            (i) -> i<10)
            .onMatchHandler((i,o) -> {
                o.add(i*3);
            })
            .priority(150)
            .nextGroupName("set3")
            .build();

    _Rule<Integer, List<Integer>> PLUS_5 = new Rule.RuleBuilder<Integer, List<Integer>>(
            "plus5",
            (i) -> true)
            .onMatchHandler((i,o) -> {
                o.add(i+5);
            })
            .groupName("set2")
            .build();

    _Rule<Integer, List<Integer>> PLUS_10 = new Rule.RuleBuilder<Integer, List<Integer>>(
            "plus10",
            (i) -> true)
            .onMatchHandler((i,o) -> {
                o.add(i+10);
            })
            .groupName("set3")
            .build();

}
