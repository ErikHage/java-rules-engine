package com.tfr.rulesEngine.example.beer.code;

import com.tfr.rulesEngine.evaluate._Evaluator;
import com.tfr.rulesEngine.rule._RuleSet;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * Created by Erik on 6/28/2017.
 */
public class BeerCharacteristicRuleProcessor {

//    //TODO new _Evaluator: multiple sets, 0-1 matches per set, no links
//    private _Evaluator<Beer,String> evaluator;
//
//    public BeerCharacteristicRuleProcessor() {
//        List<_RuleSet<Beer,String>> ruleSets = new ArrayList<>();
//
//        ruleSets.add(new RuleSetBuilder<Beer,String>("IBU Rules")
//                .addRule(CharacteristicRules.IS_HOPPY)
//                .build());
//
//        ruleSets.add(new RuleSetBuilder<Beer,String>("SRM Rules")
//                .addRule(CharacteristicRules.IS_LIGHT)
//                .addRule(CharacteristicRules.IS_RED)
//                .addRule(CharacteristicRules.IS_DARK)
//                .build());
//
//        ruleSets.add(new RuleSetBuilder<Beer,String>("OG Rules")
//                .addRule(CharacteristicRules.IS_MALTY)
//                .build());
//
//        ruleSets.add(new RuleSetBuilder<Beer,String>("ABV Rules")
//                .addRule(CharacteristicRules.IS_SESSION)
//                .addRule(CharacteristicRules.IS_STRONG)
//                .build());
//
//        this.evaluator = new MultiRuleSetEvaluator<>("IBU Rules", ruleSets, _Evaluator.EvaluationStyle.SINGLE_MATCH_PER_SET);
//    }
//
//    public List<String> process(Beer beer) {
//        List<String> output = evaluator.evaluate(beer);
//        System.out.println(output.toString());
//        return output;
//    }

}
