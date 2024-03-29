package com.tfr.rulesEngine.evaluate;

import com.tfr.rulesEngine.data.AuditEntry;
import com.tfr.rulesEngine.data.EvaluationResult;
import com.tfr.rulesEngine.rule.RuleMap;
import com.tfr.rulesEngine.rule._Rule;
import com.tfr.rulesEngine.rule._RuleMap;
import com.tfr.rulesEngine.rule._RuleSet;

import java.util.List;

import static com.tfr.rulesEngine.config.Constants.DEFAULT_GROUP;
import static com.tfr.rulesEngine.config.Constants.TERMINAL_GROUP;

/**
 *
 */
public class MatchFirstRuleEvaluator<I,O> implements _Evaluator<I,O> {

    private final _RuleMap<I,O> ruleMap;

    protected MatchFirstRuleEvaluator(_RuleSet<I,O> ruleSet) {
        ruleMap = new RuleMap<>(ruleSet);
    }

    @Override
    public EvaluationResult<I,O> evaluate(I input, O output) {
        EvaluationResult<I,O> evaluationResult = new EvaluationResult<>(input, output);

        String currentGroup = DEFAULT_GROUP;
        while (!TERMINAL_GROUP.equals(currentGroup)) {
            currentGroup = evaluateGroup(currentGroup, evaluationResult);
        }

        System.out.println("Reached TERMINAL match");

        List<AuditEntry> audits = evaluationResult.getAuditTrail();

        System.out.println("Audit Trail:");
        audits.forEach(System.out::println);

        return evaluationResult;
    }

    private String evaluateGroup(String group, EvaluationResult<I,O> evaluationResult) {
        System.out.print("Evaluating Group: " + group);
        _RuleSet<I,O> ruleGroup = ruleMap.getRuleGroup(group);
        System.out.println(" , of size " + ruleGroup.size());

        String nextGroup = ruleGroup.stream()
                .filter(r -> testMatchCondition(r, evaluationResult))
                .findFirst()
                .map(rule -> applyOnMatchHandler(rule, evaluationResult))
                .orElse(TERMINAL_GROUP);

        System.out.println("Next group : " + nextGroup);

        return nextGroup;
    }

    private boolean testMatchCondition(_Rule<I,O> rule, EvaluationResult<I,O> evaluationResult) {
        boolean isMatch = rule.testMatchCondition(evaluationResult.getFacts().value());

        if (!isMatch) {
            evaluationResult.appendNotMatchedAudit(rule);
        }

        return isMatch;
    }

    private String applyOnMatchHandler(_Rule<I,O> rule, EvaluationResult<I,O> evaluationResult) {
        System.out.println("Matched: " + rule);

        rule.applyOnMatchHandler(evaluationResult);
        evaluationResult.appendMatchedAudit(rule, evaluationResult.getKnowledge());

        return rule.getNextGroupName();
    }

}