package com.tfr.rulesEngine.example.beer.code;

import com.tfr.rulesEngine.evaluate.Evaluator;
import com.tfr.rulesEngine.evaluate.RuleSetEvaluator;
import com.tfr.rulesEngine.rule.RuleSet;
import com.tfr.rulesEngine.rule.simple.SimpleRuleSetBuilder;

import java.util.List;

/**
 *
 * Created by Erik on 6/27/2017.
 */
public class BeerStyleRuleProcessor {

    private Evaluator<Beer,String> evaluator;

    public BeerStyleRuleProcessor() {
        RuleSet<Beer,String> ruleSet = new SimpleRuleSetBuilder<Beer,String>("Style Rules")
                .addRule(StyleRules.IS_IPA_STYLE)
                .addRule(StyleRules.IS_AMBER_STYLE)
                .addRule(StyleRules.IS_RED_STYLE)
                .build();
        this.evaluator = new RuleSetEvaluator<>(ruleSet, Evaluator.EvaluationStyle.MULTI_MATCH);
    }

    public List<String> process(Beer beer) {
        List<String> output = evaluator.evaluate(beer);
        System.out.println(output.toString());
        return output;
    }

}
