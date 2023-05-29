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
public class BeerStyleRuleProcessor {

    private final _Evaluator<Beer,List<String>> evaluator;

    public BeerStyleRuleProcessor() {
        _RuleSet<Beer,List<String>> ruleSet = new RuleSet.RuleSetBuilder<Beer,List<String>>()
                .addRule(StyleRules.IS_IPA_STYLE)
                .addRule(StyleRules.IS_NOT_IPA_STYLE)
                .addRule(StyleRules.IS_AMBER_STYLE)
                .addRule(StyleRules.IS_NOT_AMBER_STYLE)
                .addRule(StyleRules.IS_RED_STYLE)
                .addRule(StyleRules.IS_NOT_RED_STYLE)
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