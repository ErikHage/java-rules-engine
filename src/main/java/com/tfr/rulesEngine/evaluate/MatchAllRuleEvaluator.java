package com.tfr.rulesEngine.evaluate;

import com.tfr.rulesEngine.data.AuditEntry;
import com.tfr.rulesEngine.data.EvaluationResult;
import com.tfr.rulesEngine.rule.*;

import java.util.ArrayList;
import java.util.List;

import static com.tfr.rulesEngine.config.Constants.*;

/**
 *
 */
public class MatchAllRuleEvaluator<I,O> implements _Evaluator<I,O> {

    private final _RuleMap<I,O> ruleMap;

    protected MatchAllRuleEvaluator(_RuleSet<I,O> ruleSet) {
        ruleMap = new RuleMap<>(ruleSet);
    }

    @Override
    public EvaluationResult<I,O> evaluate(I input, O output) {
        EvaluationResult<I,O> evaluationResult = new EvaluationResult<>(input, output);

        List<String> groups = List.of(DEFAULT_GROUP);
        while (groups.size() > 0) {
            List<String> nextGroups = new ArrayList<>();
            for (String group : groups) {
                List<String> results = evaluateGroup(group, evaluationResult)
                        .stream()
                        .filter(this::isNotTerminalGroup)
                        .toList();
                nextGroups.addAll(results);
            }
            groups = new ArrayList<>(nextGroups);
        }

        System.out.println("Evaluated all rules");

        List<AuditEntry> audits = evaluationResult.getAuditTrail();

        System.out.println("Audit Trail:");
        audits.forEach(System.out::println);

        return evaluationResult;
    }

    private List<String> evaluateGroup(String group, EvaluationResult<I,O> evaluationResult) {
        System.out.print("Evaluating Group: " + group);
        _RuleSet<I,O> ruleGroup = ruleMap.getRuleGroup(group);
        System.out.println(" , of size " + ruleGroup.size());

        List<String> nextGroups = ruleGroup.stream()
                .filter(r -> testMatchCondition(r, evaluationResult))
                .map(rule -> applyOnMatchHandler(rule, evaluationResult))
                .toList();

        System.out.println("Next groups : " + nextGroups);

        return nextGroups;
    }

    private boolean isNotTerminalGroup(String groupName) {
        return !TERMINAL_GROUP.equals(groupName);
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