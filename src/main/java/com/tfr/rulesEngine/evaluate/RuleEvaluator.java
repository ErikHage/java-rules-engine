package com.tfr.rulesEngine.evaluate;

import com.google.common.collect.Lists;
import com.tfr.rulesEngine.rule.*;

import java.util.List;
import java.util.Optional;

import static com.tfr.rulesEngine.config.Constants.*;

/**
 *
 * Created by Erik on 6/29/2017.
 */
public class RuleEvaluator<I,O> implements _Evaluator<I,O> {

    private final _RuleMap<I,O> ruleMap;

    public RuleEvaluator(_RuleSet<I,O> ruleSet) {
        ruleMap = new RuleMap<>(ruleSet);
    }

    @Override
    public Optional<O> evaluate(I input) {
        Optional<O> output = evaluateGroup(DEFAULT_GROUP, input);
        System.out.println("Result: " + output);
        return output;
    }

    private Optional<O> evaluateGroup(String group, I input) {
        System.out.print("Evaluating Group: " + group);
        _RuleSet<I,O> ruleGroup = ruleMap.getRuleGroup(group);
        System.out.println(" , of size " + ruleGroup.getRules().size());

        return ruleGroup.stream()
                .filter(r -> r.getPredicate().test(input))
                .findFirst()
                .flatMap(rule -> {
                    System.out.println("Matched: " + rule);
                    rule.getFunction().apply(input);
                    if (TERMINAL_GROUP.equals(rule.getNextGroup())) {
                        System.out.println("Reached TERMINAL match");
                        return rule.getFunction().apply(input);
                    } else {
                        System.out.println("Next group : " + rule.getNextGroup());
                        return evaluateGroup(rule.getNextGroup(), input);
                    }
                });
    }

    @Override
    public List<O> evaluateMulti(I input) {
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
                .filter(r -> {
                    System.out.println("Testing rule: " + r);
                    return r.getPredicate().test(input);
                })
                .findFirst()
                .ifPresent(rule -> {
                    System.out.println("Matched: " + rule);
                    rule.getFunction().apply(input).ifPresent(output::add);
                    if (TERMINAL_GROUP.equals(rule.getNextGroup())) {
                        System.out.println("Reached TERMINAL match");
                    } else {
                        System.out.println("Next group : " + rule.getNextGroup());
                        evaluateGroup(rule.getNextGroup(), input, output);
                    }
                });

        return output;
    }

}