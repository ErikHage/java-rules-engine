package com.tfr.rulesEngine.evaluate;

import com.tfr.rulesEngine.data.EvaluationResult;
import com.tfr.rulesEngine.rule.*;

import static com.tfr.rulesEngine.config.Constants.*;

/**
 *
 */
public class RuleEvaluator<I,O> implements _Evaluator<I,O> {

    private final _RuleMap<I,O> ruleMap;

    public RuleEvaluator(_RuleSet<I,O> ruleSet) {
        ruleMap = new RuleMap<>(ruleSet);
    }

    @Override
    public EvaluationResult<I,O> evaluate(I input, O output) {
        EvaluationResult<I,O> evaluationResult = new EvaluationResult<>(input, output);

        String currentGroup = DEFAULT_GROUP;
        while (!TERMINAL_GROUP.equals(currentGroup)) {
            currentGroup = evaluateGroup(currentGroup, input, evaluationResult);
        }

        System.out.println("Reached TERMINAL match");

        return evaluationResult;
    }

    private String evaluateGroup(String group, I input, EvaluationResult<I,O> evaluationResult) {
        System.out.print("Evaluating Group: " + group);
        _RuleSet<I,O> ruleGroup = ruleMap.getRuleGroup(group);
        System.out.println(" , of size " + ruleGroup.getRules().size());

        String nextGroup = ruleGroup.stream()
                .filter(r -> r.testMatchCondition(input))
                .findFirst()
                .map(rule -> applyOnMatchHandler(rule, evaluationResult))
                .orElse(TERMINAL_GROUP);

        System.out.println("Next group : " + nextGroup);

        return nextGroup;
    }

    private String applyOnMatchHandler(_Rule<I,O> rule, EvaluationResult<I,O> evaluationResult) {
        System.out.println("Matched: " + rule);

        rule.applyOnMatchHandler(evaluationResult);

        return rule.getNextGroupName();
    }

}