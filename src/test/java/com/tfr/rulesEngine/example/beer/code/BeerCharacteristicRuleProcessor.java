package com.tfr.rulesEngine.example.beer.code;

import com.tfr.rulesEngine.data.EvaluationResult;
import com.tfr.rulesEngine.evaluate.EvaluatorBuilder;
import com.tfr.rulesEngine.evaluate.MatchingStrategy;
import com.tfr.rulesEngine.evaluate._Evaluator;
import com.tfr.rulesEngine.rule.RuleSet;
import com.tfr.rulesEngine.rule._RuleSet;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class BeerCharacteristicRuleProcessor {

    private final _Evaluator<Beer,List<String>> evaluator;

    public BeerCharacteristicRuleProcessor() {
        _RuleSet<Beer,List<String>> ruleSet = new RuleSet.RuleSetBuilder<Beer,List<String>>()
                .addRule(CharacteristicRules.IS_HOPPY)
                .addRule(CharacteristicRules.IS_NOT_HOPPY)
                .addRule(CharacteristicRules.IS_LIGHT)
                .addRule(CharacteristicRules.IS_RED)
                .addRule(CharacteristicRules.IS_DARK)
                .addRule(CharacteristicRules.IS_MALTY)
                .addRule(CharacteristicRules.IS_DRY)
                .addRule(CharacteristicRules.IS_SESSION)
                .addRule(CharacteristicRules.IS_MID_ALCOHOL)
                .addRule(CharacteristicRules.IS_STRONG)
                .build();

        this.evaluator = new EvaluatorBuilder<Beer, List<String>>()
                .withMatchingStrategy(MatchingStrategy.MATCH_FIRST)
                .withRules(ruleSet)
                .build();
    }

    public List<String> process(Beer beer) {
        EvaluationResult<Beer,List<String>> result = evaluator.evaluate(beer, new ArrayList<>());

        return result.getKnowledge().value();
    }

}
