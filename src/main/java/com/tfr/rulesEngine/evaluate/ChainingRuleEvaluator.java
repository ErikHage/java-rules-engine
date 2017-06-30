package com.tfr.rulesEngine.evaluate;

import com.google.common.base.Strings;
import com.google.common.collect.Lists;
import com.tfr.rulesEngine.rule.Rule;
import com.tfr.rulesEngine.rule.RuleSet;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.tfr.rulesEngine.evaluate.Evaluator.EvaluationStyle.*;

/**
 *
 * Created by Erik Hage on 6/17/2017.
 */
public class ChainingRuleEvaluator<I> extends MultiRuleSetEvaluator<I,I> {

    public ChainingRuleEvaluator(String initialSet, List<RuleSet<I,I>> ruleSets,
                                 EvaluationStyle evaluationStyle) {
        super(initialSet, ruleSets, evaluationStyle);
    }

    public ChainingRuleEvaluator(String initialSet, List<RuleSet<I,I>> ruleSets) {
        this(initialSet, ruleSets, EvaluationStyle.SINGLE_MATCH);
    }

    @Override
    public List<I> evaluate(I input) {
        List<I> output = Lists.newArrayList();

        if(SINGLE_MATCH_PER_SET.equals(evaluationStyle)) {
            output = unlinkedEvaluation(input);
        } else {
            chainingEvaluation(initialSet, input, output);
        }

        return output;
    }

    private List<I> chainingEvaluation(String setName, I input, List<I> output) {
        for(Rule<I,I> rule: ruleSetMap.get(setName)) {
            if(rule.getPredicate().test(input)) {
                if (!Strings.isNullOrEmpty(rule.getNext())) {
                    I result = rule.getFunction().apply(input);
                    if(MULTI_MATCH.equals(evaluationStyle)) {
                        output.add(result);
                    }
                    chainingEvaluation(rule.getNext(), result, output);
                } else {
                    output.add(rule.getFunction().apply(input));
                    break;
                }
            }
        }
        return output;
    }
}
