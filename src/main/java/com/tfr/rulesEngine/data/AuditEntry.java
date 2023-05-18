package com.tfr.rulesEngine.data;

public record AuditEntry<O>(String ruleSetName, String ruleName, boolean wasMatched, Knowledge<O> knowledgeState) {

}
