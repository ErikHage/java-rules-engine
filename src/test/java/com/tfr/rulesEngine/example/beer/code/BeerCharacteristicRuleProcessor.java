package com.tfr.rulesEngine.example.beer.code;

import com.tfr.rulesEngine.evaluate.RuleEvaluator;
import com.tfr.rulesEngine.evaluate._Evaluator;
import com.tfr.rulesEngine.rule.RuleSet;
import com.tfr.rulesEngine.rule._RuleSet;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 *
 * Created by Erik on 6/28/2017.
 */
public class BeerCharacteristicRuleProcessor {

    private _Evaluator<Beer,String> evaluator;

    public BeerCharacteristicRuleProcessor() {
        _RuleSet<Beer,String> ruleSet = new RuleSet.RuleSetBuilder<Beer,String>()
                .addRule(CharacteristicRules.IS_HOPPY)
                .addRule(CharacteristicRules.IS_NOT_HOPPY)
                .addRule(CharacteristicRules.IS_LIGHT)
                .addRule(CharacteristicRules.IS_RED)
                .addRule(CharacteristicRules.IS_DARK)
                .addRule(CharacteristicRules.IS_MALTY)
                .addRule(CharacteristicRules.IS_DRY)
                .addRule(CharacteristicRules.IS_SESSION)
                .addRule(CharacteristicRules.IS_STRONG)
                .build();

        this.evaluator = new RuleEvaluator<>(ruleSet);
    }

    public List<String> process(Beer beer) {
        List<String> output = evaluator.evaluateMulti(beer);
        System.out.println(output.toString());
        return output;
    }

}
