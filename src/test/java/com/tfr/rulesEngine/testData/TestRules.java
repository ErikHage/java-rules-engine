package com.tfr.rulesEngine.testData;

import com.tfr.rulesEngine.rule._Rule;
import com.tfr.rulesEngine.rule.Rule;

import java.util.List;
import java.util.Map;

import static com.tfr.rulesEngine.config.Constants.TERMINAL_GROUP;

/**
 *
 */
public interface TestRules {

    _Rule<String, List<String>> LONG_STRING_RULE = new Rule.RuleBuilder<String,List<String>>(
            "longString",
            (i) -> i.length() > 10)
            .onMatchHandler((i,o) -> o.add(">10 chars"))
            .priority(30)
            .build();

    _Rule<String, List<String>> SHORT_STRING_RULE = new Rule.RuleBuilder<String,List<String>>(
            "shortString",
            (i) -> i.length() < 10)
            .onMatchHandler((i,o) -> o.add("<10 chars"))
            .priority(20)
            .build();

    _Rule<String, List<String>> HAS_CHAR_A_RULE = new Rule.RuleBuilder<String,List<String>>(
            "hasCharARule",
            (i) -> i.contains("a") || i.contains("A"))
            .onMatchHandler((i,o) -> o.add("contains 'a' or 'A'"))
            .priority(10)
            .build();

    _Rule<Integer, List<String>> SMALL_INT = new Rule.RuleBuilder<Integer,List<String>>(
            "smallInt",
            (i) -> i < 10)
            .onMatchHandler((i,o) -> o.add("int<10"))
            .priority(100)
            .build();

    _Rule<Integer, List<String>> MED_INT = new Rule.RuleBuilder<Integer,List<String>>(
            "mediumInt",
            (i) -> i < 100)
            .onMatchHandler((i,o) -> o.add("int<100"))
            .priority(90)
            .build();

    _Rule<Integer, List<String>> LARGE_INT = new Rule.RuleBuilder<Integer,List<String>>(
            "largeInt",
            (i) -> i < 1000)
            .onMatchHandler((i,o) -> o.add("int<1000"))
            .priority(80)
            .build();

    _Rule<Integer, List<String>> HUGE_INT = new Rule.RuleBuilder<Integer,List<String>>(
            "hugeInt",
            (i) -> i >= 1000)
            .onMatchHandler((i,o) -> o.add("int>=1000"))
            .priority(70)
            .build();



    _Rule<Integer, List<Integer>> SMALL_INT_INT = new Rule.RuleBuilder<Integer, List<Integer>>(
            "smallIntInt",
            (i) -> i < 10)
            .onMatchHandler((i,o) -> o.add(i))
            .priority(100)
            .build();


    _Rule<Integer, List<Integer>> DOUBLE_INT = new Rule.RuleBuilder<Integer, List<Integer>>(
            "doubleInt",
            (i) -> i>10)
            .onMatchHandler((i,o) -> o.add(i*2))
            .priority(100)
            .nextGroupName("set2")
            .build();

    _Rule<Integer, List<Integer>> TRIPLE_INT = new Rule.RuleBuilder<Integer, List<Integer>>(
            "tripleInt",
            (i) -> i<10)
            .onMatchHandler((i,o) -> o.add(i*3))
            .priority(150)
            .nextGroupName("set3")
            .build();

    _Rule<Integer, List<Integer>> PLUS_5 = new Rule.RuleBuilder<Integer, List<Integer>>(
            "plus5",
            (i) -> true)
            .onMatchHandler((i,o) -> o.add(i+5))
            .groupName("set2")
            .build();

    _Rule<Integer, List<Integer>> PLUS_10 = new Rule.RuleBuilder<Integer, List<Integer>>(
            "plus10",
            (i) -> true)
            .onMatchHandler((i,o) -> o.add(i+10))
            .groupName("set3")
            .build();

    _Rule<String, Map<String,String>> HAS_COLOR = new Rule.RuleBuilder<String,Map<String,String>>(
            "hasColorRule",
            (i) -> i.contains("color"))
            .onMatchHandler((i,o) -> o.put("color", null))
            .nextGroupName("colorGroup")
            .build();

    _Rule<String, Map<String,String>> HAS_SIZE = new Rule.RuleBuilder<String,Map<String,String>>(
            "hasSizeRule",
            (i) -> i.contains("size"))
            .onMatchHandler((i,o) -> o.put("size", null))
            .nextGroupName("sizeGroup")
            .build();

    _Rule<String, Map<String,String>> IS_LARGE = new Rule.RuleBuilder<String,Map<String,String>>(
            "isLargeRule",
            (i) -> i.contains("large"))
            .groupName("sizeGroup")
            .onMatchHandler((i,o) -> o.put("size", "large"))
            .nextGroupName(TERMINAL_GROUP)
            .build();

    _Rule<String, Map<String,String>> IS_SMALL = new Rule.RuleBuilder<String,Map<String,String>>(
            "isSmallRule",
            (i) -> i.contains("small"))
            .groupName("sizeGroup")
            .onMatchHandler((i,o) -> o.put("size", "small"))
            .nextGroupName(TERMINAL_GROUP)
            .build();

    _Rule<String, Map<String,String>> IS_RED = new Rule.RuleBuilder<String,Map<String,String>>(
            "isRedRule",
            (i) -> i.contains("red"))
            .groupName("colorGroup")
            .onMatchHandler((i,o) -> o.put("color", "red"))
            .nextGroupName(TERMINAL_GROUP)
            .build();

    _Rule<String, Map<String,String>> IS_BLUE = new Rule.RuleBuilder<String,Map<String,String>>(
            "isBlueRule",
            (i) -> i.contains("blue"))
            .groupName("colorGroup")
            .onMatchHandler((i,o) -> o.put("color", "blue"))
            .nextGroupName(TERMINAL_GROUP)
            .build();
}
