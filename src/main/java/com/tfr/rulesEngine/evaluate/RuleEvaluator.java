package com.tfr.rulesEngine.evaluate;

import com.tfr.rulesEngine.rule.*;

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
        Optional<O> output = evaluateSet(DEFAULT_GROUP, input);
        System.out.println("Result: " + output);
        return output;
    }

    private Optional<O> evaluateSet(String group, I input) {
        System.out.print("Evaluating Group: " + group);
        _RuleSet<I,O> ruleGroup = ruleMap.getRuleGroup(group);
        System.out.println(" , of size " + ruleGroup.getRules().size());

        Optional<_Rule<I,O>> match = ruleGroup.stream()
                .filter(r -> r.getPredicate().test(input))
                .findFirst();

        if(!match.isPresent()) {
            System.out.println("No match in this set");
            return Optional.empty();
        }

        if(TERMINAL_GROUP.equals(match.get().getNextGroup())) {
            System.out.println("Reached TERMINAL match");
            return Optional.of(match.get().getFunction().apply(input));
        } else {
            System.out.println("Next group : " + match.get().getNextGroup());
            return evaluateSet(match.get().getNextGroup(), input);
        }
    }

}