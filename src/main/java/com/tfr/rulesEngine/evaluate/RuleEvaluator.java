package com.tfr.rulesEngine.evaluate;

import com.google.common.collect.Lists;
import com.tfr.rulesEngine.rule.*;

import java.util.List;

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
    public List<O> evaluate(I input) {
        List<O> output = Lists.newArrayList();
        evaluateGroup(DEFAULT_GROUP, input, output);
        System.out.println("Result: " + output);
        return output;
    }

    private List<O> evaluateGroup(String group, I input, List<O> output) {
        System.out.print("Evaluating Group: " + group);
        _RuleSet<I,O> ruleGroup = ruleMap.getRuleGroup(group);
        System.out.println(" , of size " + ruleGroup.getRules().size());

        ruleGroup.stream()
                .filter(r -> r.testMatchCondition(input))
                .findFirst()
                .ifPresent(rule -> applyOnMatchHandler(rule, input, output));

        return output;
    }

    private void applyOnMatchHandler(_Rule<I,O> rule, I input, List<O> output) {
        System.out.println("Matched: " + rule);
        rule.applyOnMatchHandler(input).ifPresent(output::add);
        if (TERMINAL_GROUP.equals(rule.getNextGroupName())) {
            System.out.println("Reached TERMINAL match");
        } else {
            System.out.println("Next group : " + rule.getNextGroupName());
            evaluateGroup(rule.getNextGroupName(), input, output);
        }
    }

}