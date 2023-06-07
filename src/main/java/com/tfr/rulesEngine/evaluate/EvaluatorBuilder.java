package com.tfr.rulesEngine.evaluate;

import com.tfr.rulesEngine.rule._RuleSet;

import java.security.InvalidParameterException;

public class EvaluatorBuilder<I,O> {

    private MatchingStrategy matchingStrategy;
    private _RuleSet<I,O> ruleSet;

    public EvaluatorBuilder() {
        matchingStrategy = MatchingStrategy.MATCH_FIRST;
    }

    public EvaluatorBuilder<I,O> withMatchingStrategy(MatchingStrategy matchingStrategy) {
        this.matchingStrategy = matchingStrategy;
        return this;
    }

    public EvaluatorBuilder<I,O> withRules(_RuleSet<I,O> ruleSet) {
        this.ruleSet = ruleSet;
        return this;
    }

    public _Evaluator<I,O> build() {
        if (ruleSet == null) {
            throw new InvalidParameterException("RuleSet cannot be null");
        }

        switch (matchingStrategy) {
            case MATCH_FIRST -> {
                return new MatchFirstRuleEvaluator<>(this.ruleSet);
            }
            case MATCH_ALL -> {
                return new MatchAllRuleEvaluator<>(this.ruleSet);
            }
            default -> throw new InvalidParameterException("Provided strategy invalid: " + matchingStrategy);
        }
    }
}
