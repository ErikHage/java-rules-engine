package com.tfr.rulesEngine.example.beer.code;

import com.tfr.rulesEngine.rule._Rule;
import com.tfr.rulesEngine.rule.Rule;

import java.util.List;

import static com.tfr.rulesEngine.example.beer.code.Styles.*;

/**
 *
 */
public interface StyleRules {

    _Rule<Beer, List<String>> IS_IPA_STYLE = new Rule.RuleBuilder<Beer,List<String>>(
            "IPA_RULE",
            (b) -> b.isStyle(IPA))
            .onMatchHandler((i,o) -> o.add(IPA.getName()))
            .nextGroupName("Amber")
            .build();

    _Rule<Beer,List<String>> IS_NOT_IPA_STYLE = new Rule.RuleBuilder<Beer,List<String>>(
            "NOT_IPA_RULE",
            (b) -> !b.isStyle(IPA))
            .nextGroupName("Amber")
            .build();




    _Rule<Beer,List<String>> IS_AMBER_STYLE = new Rule.RuleBuilder<Beer,List<String>>(
            "AMBER_RULE",
            (b) -> b.isStyle(AMBER))
            .onMatchHandler((i,o) -> o.add(AMBER.getName()))
            .groupName("Amber")
            .nextGroupName("Red")
            .build();

    _Rule<Beer,List<String>> IS_NOT_AMBER_STYLE = new Rule.RuleBuilder<Beer,List<String>>(
            "NOT_AMBER_RULE",
            (b) -> !b.isStyle(AMBER))
            .groupName("Amber")
            .nextGroupName("Red")
            .build();


    _Rule<Beer,List<String>> IS_RED_STYLE = new Rule.RuleBuilder<Beer,List<String>>(
            "RED_RULE",
            (b) -> b.isStyle(RED))
            .onMatchHandler((i,o) -> o.add(RED.getName()))
            .groupName("Red")
            .build();

    _Rule<Beer,List<String>> IS_NOT_RED_STYLE = new Rule.RuleBuilder<Beer,List<String>>(
            "NOT_RED_RULE",
            (b) -> !b.isStyle(RED))
            .groupName("Red")
            .build();


}