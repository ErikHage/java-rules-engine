package com.tfr.rulesEngine.data;

import java.util.Objects;

public final class AuditEntry {

    private static final String TO_STRING_FORMAT = "[group:%s][rule:%s] match:%s knowledge:%s";

    private final String ruleSetName;
    private final String ruleName;
    private final Boolean wasMatched;
    private String knowledgeState;

    public AuditEntry(String ruleSetName, String ruleName, Boolean wasMatched, String knowledgeState) {
        this.ruleSetName = ruleSetName;
        this.ruleName = ruleName;
        this.wasMatched = wasMatched;
        this.knowledgeState = knowledgeState;
    }

    @Override
    public String toString() {
        return String.format(TO_STRING_FORMAT, ruleSetName, ruleName, wasMatched, knowledgeState);
    }

    public String ruleSetName() {
        return ruleSetName;
    }

    public String ruleName() {
        return ruleName;
    }

    public Boolean wasMatched() {
        return wasMatched;
    }

    public String knowledgeState() {
        return knowledgeState;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != this.getClass()) return false;
        var that = (AuditEntry) obj;
        return Objects.equals(this.ruleSetName, that.ruleSetName) &&
                Objects.equals(this.ruleName, that.ruleName) &&
                Objects.equals(this.wasMatched, that.wasMatched) &&
                Objects.equals(this.knowledgeState, that.knowledgeState);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ruleSetName, ruleName, wasMatched, knowledgeState);
    }

}
