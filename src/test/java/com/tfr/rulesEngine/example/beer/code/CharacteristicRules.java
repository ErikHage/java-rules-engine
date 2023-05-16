package com.tfr.rulesEngine.example.beer.code;

import com.tfr.rulesEngine.rule._Rule;
import com.tfr.rulesEngine.rule.Rule;

/**
 *
 * Created by Erik on 6/28/2017.
 */
public interface CharacteristicRules {

    String ABV_GROUP = "abvGroup";
    String SRM_GROUP = "srmGroup";
    String OG_GROUP = "ogGroup";

    _Rule<Beer, String> IS_HOPPY = new Rule.RuleBuilder<Beer,String>(
            "Beer Is Hoppy",
            (b) -> b.getIbu() > 50)
            .onMatchHandler(b -> "Hoppy")
            .nextGroupName(SRM_GROUP)
            .build();

    _Rule<Beer, String> IS_NOT_HOPPY = new Rule.RuleBuilder<Beer,String>(
            "otherIbu",
            (b) -> b.getIbu() <= 50)
            .onMatchHandler(b -> {})
            .nextGroupName(SRM_GROUP)
            .build();



    _Rule<Beer, String> IS_DARK = new Rule.RuleBuilder<Beer,String>(
            "Beer Is Dark",
            (b) -> b.getSrm() > 80)
            .onMatchHandler(b -> "Dark")
            .groupName(SRM_GROUP)
            .nextGroupName(OG_GROUP)
            .build();

    _Rule<Beer, String> IS_RED = new Rule.RuleBuilder<Beer,String>(
            "OtherSrm",
            (b) -> b.getSrm() >= 30 && b.getSrm() <=80)
            .onMatchHandler(b -> {})
            .groupName(SRM_GROUP)
            .nextGroupName(OG_GROUP)
            .build();

    _Rule<Beer, String> IS_LIGHT = new Rule.RuleBuilder<Beer,String>(
            "Beer Is Light",
            (b) -> b.getSrm() < 30)
            .onMatchHandler(b -> "Light")
            .groupName(SRM_GROUP)
            .nextGroupName(OG_GROUP)
            .build();



    _Rule<Beer, String> IS_MALTY = new Rule.RuleBuilder<Beer,String>(
            "Beer Is Malty",
            (b) -> b.getOg() > 1.075)
            .onMatchHandler(b -> "Malty")
            .groupName(OG_GROUP)
            .nextGroupName(ABV_GROUP)
            .build();

    _Rule<Beer, String> IS_DRY = new Rule.RuleBuilder<Beer,String>(
            "OtherOg",
            (b) -> b.getOg() <= 1.075)
            .onMatchHandler(b -> {})
            .groupName(OG_GROUP)
            .nextGroupName(ABV_GROUP)
            .build();



    _Rule<Beer, String> IS_SESSION = new Rule.RuleBuilder<Beer,String>(
            "Beer Is Session",
            (b) -> b.getAbv() <= 5.0)
            .onMatchHandler(b -> "Session")
            .groupName(ABV_GROUP)
            .build();

    _Rule<Beer, String> IS_MID_ALCOHOL = new Rule.RuleBuilder<Beer,String>(
            "otherAbv",
            (b) -> b.getAbv() > 5.0 && b.getAbv() <= 7.5)
            .onMatchHandler(b -> {})
            .groupName(ABV_GROUP)
            .build();

    _Rule<Beer, String> IS_STRONG = new Rule.RuleBuilder<Beer,String>(
            "Beer Is Strong",
            (b) -> b.getAbv() > 7.5)
            .onMatchHandler(b -> "Strong")
            .groupName(ABV_GROUP)
            .build();

}
