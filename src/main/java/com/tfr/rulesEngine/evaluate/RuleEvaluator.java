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

        evaluateGroup(DEFAULT_GROUP, input, evaluationResult);

        return evaluationResult;
    }

    private void evaluateGroup(String group, I input, EvaluationResult<I,O> evaluationResult) {
        System.out.print("Evaluating Group: " + group);
        _RuleSet<I,O> ruleGroup = ruleMap.getRuleGroup(group);
        System.out.println(" , of size " + ruleGroup.getRules().size());

        ruleGroup.stream()
                .filter(r -> r.testMatchCondition(input))
                .findFirst()
                .ifPresent(rule -> applyOnMatchHandler(rule, input, evaluationResult));
    }

    private void applyOnMatchHandler(_Rule<I,O> rule, I input, EvaluationResult<I,O> evaluationResult) {
        System.out.println("Matched: " + rule);

        rule.applyOnMatchHandler(evaluationResult);

        if (TERMINAL_GROUP.equals(rule.getNextGroupName())) {
            System.out.println("Reached TERMINAL match");
        } else {
            System.out.println("Next group : " + rule.getNextGroupName());
            evaluateGroup(rule.getNextGroupName(), input, evaluationResult);
        }
    }

}