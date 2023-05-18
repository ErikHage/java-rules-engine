package com.tfr.rulesEngine.example.beer.code;

import com.tfr.rulesEngine.rule._Rule;
import com.tfr.rulesEngine.rule.Rule;

import java.util.List;

/**
 *
 */
public interface CharacteristicRules {

    String ABV_GROUP = "abvGroup";
    String SRM_GROUP = "srmGroup";
    String OG_GROUP = "ogGroup";

    _Rule<Beer, List<String>> IS_HOPPY = new Rule.RuleBuilder<Beer,List<String>>(
            "Beer Is Hoppy",
            (b) -> b.getIbu() > 50)
            .onMatchHandler((i,o) -> o.add("Hoppy"))
            .nextGroupName(SRM_GROUP)
            .build();

    _Rule<Beer, List<String>> IS_NOT_HOPPY = new Rule.RuleBuilder<Beer,List<String>>(
            "otherIbu",
            (b) -> b.getIbu() <= 50)
            .onMatchHandler((i,o) -> {})
            .nextGroupName(SRM_GROUP)
            .build();



    _Rule<Beer, List<String>> IS_DARK = new Rule.RuleBuilder<Beer,List<String>>(
            "Beer Is Dark",
            (b) -> b.getSrm() > 80)
            .onMatchHandler((i,o) -> o.add("Dark"))
            .groupName(SRM_GROUP)
            .nextGroupName(OG_GROUP)
            .build();

    _Rule<Beer, List<String>> IS_RED = new Rule.RuleBuilder<Beer,List<String>>(
            "OtherSrm",
            (b) -> b.getSrm() >= 30 && b.getSrm() <=80)
            .onMatchHandler((i,o) -> {})
            .groupName(SRM_GROUP)
            .nextGroupName(OG_GROUP)
            .build();

    _Rule<Beer, List<String>> IS_LIGHT = new Rule.RuleBuilder<Beer,List<String>>(
            "Beer Is Light",
            (b) -> b.getSrm() < 30)
            .onMatchHandler((i,o) -> o.add("Light"))
            .groupName(SRM_GROUP)
            .nextGroupName(OG_GROUP)
            .build();



    _Rule<Beer, List<String>> IS_MALTY = new Rule.RuleBuilder<Beer,List<String>>(
            "Beer Is Malty",
            (b) -> b.getOg() > 1.075)
            .onMatchHandler((i,o) -> o.add("Malty"))
            .groupName(OG_GROUP)
            .nextGroupName(ABV_GROUP)
            .build();

    _Rule<Beer, List<String>> IS_DRY = new Rule.RuleBuilder<Beer,List<String>>(
            "OtherOg",
            (b) -> b.getOg() <= 1.075)
            .onMatchHandler((i,o) -> {})
            .groupName(OG_GROUP)
            .nextGroupName(ABV_GROUP)
            .build();



    _Rule<Beer, List<String>> IS_SESSION = new Rule.RuleBuilder<Beer,List<String>>(
            "Beer Is Session",
            (b) -> b.getAbv() <= 5.0)
            .onMatchHandler((i,o) -> o.add("Session"))
            .groupName(ABV_GROUP)
            .build();

    _Rule<Beer, List<String>> IS_MID_ALCOHOL = new Rule.RuleBuilder<Beer,List<String>>(
            "otherAbv",
            (b) -> b.getAbv() > 5.0 && b.getAbv() <= 7.5)
            .onMatchHandler((i,o) -> {})
            .groupName(ABV_GROUP)
            .build();

    _Rule<Beer, List<String>> IS_STRONG = new Rule.RuleBuilder<Beer,List<String>>(
            "Beer Is Strong",
            (b) -> b.getAbv() > 7.5)
            .onMatchHandler((i,o) -> o.add("Strong"))
            .groupName(ABV_GROUP)
            .build();

}
