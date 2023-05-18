package com.tfr.rulesEngine.data;

import java.util.*;

public class EvaluationResult<I,O> {

    private final Facts<I> facts;
    private final Knowledge<O> knowledge;
    private final List<AuditEntry<O>> auditTrail;

    public EvaluationResult(I input, O output) {
        this.facts = new Facts<>(input);
        this.knowledge = new Knowledge<>(output);
        auditTrail = new LinkedList<>();
    }

    public Facts<I> getFacts() {
        return facts;
    }

    public Knowledge<O> getKnowledge() {
        return knowledge;
    }

    public List<AuditEntry<O>> getAuditTrail() {
        return auditTrail;
    }
}
