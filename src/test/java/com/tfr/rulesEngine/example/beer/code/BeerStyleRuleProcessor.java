package com.tfr.rulesEngine.example.beer.code;

import com.tfr.rulesEngine.evaluate._Evaluator;
import com.tfr.rulesEngine.evaluate.RuleEvaluator;
import com.tfr.rulesEngine.rule.RuleSet;
import com.tfr.rulesEngine.rule._RuleSet;

import java.util.List;

/**
 *
 * Created by Erik on 6/27/2017.
 */
public class BeerStyleRuleProcessor {

    private _Evaluator<Beer,String> evaluator;

    public BeerStyleRuleProcessor() {
        _RuleSet<Beer,String> ruleSet = new RuleSet.RuleSetBuilder<Beer,String>()
                .addRule(StyleRules.IS_IPA_STYLE)
                .addRule(StyleRules.IS_NOT_IPA_STYLE)
                .addRule(StyleRules.IS_AMBER_STYLE)
                .addRule(StyleRules.IS_NOT_AMBER_STYLE)
                .addRule(StyleRules.IS_RED_STYLE)
                .addRule(StyleRules.IS_NOT_RED_STYLE)
                .build();
        this.evaluator = new RuleEvaluator<>(ruleSet);
    }

    public List<String> process(Beer beer) {
        List<String> output = evaluator.evaluateMulti(beer);
        System.out.println(output.toString());
        return output;
    }

}