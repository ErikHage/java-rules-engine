package com.tfr.rulesEngine.data;

import com.tfr.rulesEngine.rule._Rule;

import java.util.*;

public class EvaluationResult<I,O> {

    private final Facts<I> facts;
    private final Knowledge<O> knowledge;
    private final List<AuditEntry> auditTrail;

    public EvaluationResult(I input, O output) {
        this.facts = new Facts<>(input);
        this.knowledge = new Knowledge<>(output);
        auditTrail = new LinkedList<>();
    }

    public void appendNotMatchedAudit(_Rule<I,O> rule) {
        auditTrail.add(new AuditEntry(rule.getGroupName(), rule.getName(), false, null));
    }

    public void appendMatchedAudit(_Rule<I,O> rule, Knowledge<O> knowledge) {
        auditTrail.add(new AuditEntry(rule.getGroupName(), rule.getName(), true, knowledge.toAuditString()));
    }

    public Facts<I> getFacts() {
        return facts;
    }

    public Knowledge<O> getKnowledge() {
        return knowledge;
    }

    public List<AuditEntry> getAuditTrail() {
        return auditTrail;
    }
}
