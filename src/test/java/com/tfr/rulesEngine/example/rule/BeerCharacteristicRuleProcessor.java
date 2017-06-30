package com.tfr.rulesEngine.example.rule;

import com.tfr.rulesEngine.evaluate.Evaluator;
import com.tfr.rulesEngine.evaluate.MultiRuleSetEvaluator;
import com.tfr.rulesEngine.example.model.Beer;
import com.tfr.rulesEngine.rule.RuleSet;
import com.tfr.rulesEngine.rule.simple.SimpleRuleSetBuilder;

import java.util.ArrayList;
import java.util.List;

import static com.tfr.rulesEngine.example.rule.CharacteristicRules.*;

/**
 *
 * Created by Erik on 6/28/2017.
 */
public class BeerCharacteristicRuleProcessor {

    //TODO new Evaluator: multiple sets, 0-1 matches per set, no links
    private Evaluator<Beer,String> evaluator;

    public BeerCharacteristicRuleProcessor() {
        List<RuleSet<Beer,String>> ruleSets = new ArrayList<>();

        ruleSets.add(new SimpleRuleSetBuilder<Beer,String>("IBU Rules")
                .addRule(IS_HOPPY)
                .build());

        ruleSets.add(new SimpleRuleSetBuilder<Beer,String>("SRM Rules")
                .addRule(IS_LIGHT)
                .addRule(IS_RED)
                .addRule(IS_DARK)
                .build());

        ruleSets.add(new SimpleRuleSetBuilder<Beer,String>("OG Rules")
                .addRule(IS_MALTY)
                .build());

        ruleSets.add(new SimpleRuleSetBuilder<Beer,String>("ABV Rules")
                .addRule(IS_SESSION)
                .addRule(IS_STRONG)
                .build());

        this.evaluator = new MultiRuleSetEvaluator<>("IBU Rules", ruleSets, Evaluator.EvaluationStyle.SINGLE_MATCH_PER_SET);
    }

    public List<String> process(Beer beer) {
        List<String> output = evaluator.evaluate(beer);
        System.out.println(output.toString());
        return output;
    }

}
