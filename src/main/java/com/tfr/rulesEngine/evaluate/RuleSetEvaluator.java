package com.tfr.rulesEngine.evaluate;

import com.google.common.collect.Sets;
import com.tfr.rulesEngine.rule.RuleSet;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static com.tfr.rulesEngine.evaluate.Evaluator.EvaluationStyle.*;

/**
 *
 * Created by Erik on 6/29/2017.
 */
public class RuleSetEvaluator<I,O> implements Evaluator<I,O> {

    private final RuleSet<I,O> ruleSet;
    private final EvaluationStyle evaluationStyle;

    private final Set<EvaluationStyle> singleMatch = Sets.newHashSet(
            SINGLE_MATCH,
            SINGLE_MATCH_PER_SET
    );

    public RuleSetEvaluator(RuleSet<I,O> ruleSet, EvaluationStyle evaluationStyle) {
        this.ruleSet = ruleSet;
        this.evaluationStyle = evaluationStyle;
    }

    public RuleSetEvaluator(RuleSet<I,O> ruleSet) {
        this(ruleSet, SINGLE_MATCH);
    }

    @Override
    public List<O> evaluate(I input) {
        return ruleSet.stream()
                .filter((r) -> r.getPredicate().test(input))
                .map((r) -> r.getFunction().apply(input))
                .limit(singleMatch.contains(evaluationStyle) ? 1 : Long.MAX_VALUE)
                .collect(Collectors.toList());
    }

    @Override
    public EvaluationStyle getEvaluationStyle() {
        return this.evaluationStyle;
    }
}