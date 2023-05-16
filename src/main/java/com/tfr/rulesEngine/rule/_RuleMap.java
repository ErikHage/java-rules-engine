package com.tfr.rulesEngine.rule;

import java.util.Set;

/**
 *
 */
public interface _RuleMap<I,O> {

    _RuleSet<I,O> getRuleGroup(String groupName);

    Set<String> getGroupNames();

    int getNumberOfGroups();
}
