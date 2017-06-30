package com.tfr.rulesEngine.evaluate;

import com.google.common.base.Strings;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.tfr.rulesEngine.rule.Rule;
import com.tfr.rulesEngine.rule.RuleSet;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.tfr.rulesEngine.evaluate.Evaluator.EvaluationStyle.*;

/**
 *
 * Created by Erik on 6/29/2017.
 */
public class MultiRuleSetEvaluator<I,O> implements Evaluator<I,O> {

    final String initialSet;
    final EvaluationStyle evaluationStyle;
    final Map<String, RuleSet<I,O>> ruleSetMap;

    public MultiRuleSetEvaluator(String initialSet, List<RuleSet<I,O>> ruleSets, EvaluationStyle evaluationStyle) {
        this.initialSet = initialSet;
        this.evaluationStyle = evaluationStyle;
        this.ruleSetMap = Maps.newHashMap();
        ruleSets.forEach(rs -> ruleSetMap.put(rs.getName(), rs));
    }

    public MultiRuleSetEvaluator(String initialSet, List<RuleSet<I,O>> ruleSets) {
        this(initialSet, ruleSets, SINGLE_MATCH);
    }

    @Override
    public List<O> evaluate(I input) {
        List<O> output = Lists.newArrayList();

        if(SINGLE_MATCH_PER_SET.equals(evaluationStyle)) {
            output = unlinkedEvaluation(input);
        } else {
            linkingEvaluation(initialSet, input, output);
        }

        return output;
    }

    protected List<O> unlinkedEvaluation(I input) {
        return ruleSetMap.values().stream()
                .map(rs -> rs.stream()
                            .filter(r -> r.getPredicate().test(input))
                            .map(r -> r.getFunction().apply(input))
                            .findFirst())
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(Collectors.toList());
    }

    private List<O> linkingEvaluation(String setName, I input, List<O> output) {
        for(Rule<I,O> rule: ruleSetMap.get(setName)) {
            if(rule.getPredicate().test(input)) {
                if (!Strings.isNullOrEmpty(rule.getNext())) {
                    if(MULTI_MATCH.equals(evaluationStyle)) {
                        output.add(rule.getFunction().apply(input));
                    }
                    linkingEvaluation(rule.getNext(), input, output);
                } else {
                    output.add(rule.getFunction().apply(input));
                    break;
                }
            }
        }
        return output;
    }

    @Override
    public EvaluationStyle getEvaluationStyle() {
        return this.evaluationStyle;
    }
}
