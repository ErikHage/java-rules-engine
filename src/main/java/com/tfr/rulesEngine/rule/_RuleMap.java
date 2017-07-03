package com.tfr.rulesEngine.rule;

import java.util.Set;

/**
 *
 * Created by Erik on 7/3/2017.
 */
public interface _RuleMap<I,O> {

    _RuleSet<I,O> getRuleGroup(String groupName);

    Set<String> getGroupNames();
    int getNumberOfGroups();
}
