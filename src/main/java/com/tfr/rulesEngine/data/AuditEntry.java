package com.tfr.rulesEngine.data;

public record AuditEntry(String ruleSetName, String ruleName, Boolean wasMatched, String knowledgeState) {

    private static final String MATCH_FORMAT = "[%s][%s] match:%s";
    private static final String KNOWLEDGE_FORMAT = "[%s][%s] knowledge:%s";

    @Override
    public String toString() {
        if (wasMatched != null) {
            return String.format(MATCH_FORMAT, ruleSetName, ruleName, wasMatched);
        }
        return String.format(KNOWLEDGE_FORMAT, ruleSetName, ruleName, knowledgeState);
    }
}
