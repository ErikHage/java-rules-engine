package com.tfr.rulesEngine.evaluate;

import com.tfr.rulesEngine.rule.Rule;
import com.tfr.rulesEngine.rule.RuleSet;

/**
 *
 * Created by Erik Hage on 6/16/2017.
 */
public class FirstMatchEvaluator<I,O,R extends Rule<I,O>> {

    private RuleSet<R> ruleSet;

    public FirstMatchEvaluator(RuleSet<R> ruleSet) {
        this.ruleSet = ruleSet;
    }

    public O evaluate(I input) {
       for(Rule<I,O> rule : ruleSet) {
           if (rule.getPredicate().test(input)) {
               return rule.getFunction().apply(input);
           }
       }
       return null;
    }

}
