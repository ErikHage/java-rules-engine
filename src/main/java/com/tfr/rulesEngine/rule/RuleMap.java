package com.tfr.rulesEngine.rule;

import java.util.HashMap;
import java.util.Set;

/**
 *
 */
public class RuleMap<I,O> extends HashMap<String, RuleSet<I,O>> implements _RuleMap<I,O> {

    public RuleMap(_RuleSet<I,O> ruleSet) {
        ruleSet.stream().forEach(r -> {
            if(!containsKey(r.getGroupName())) {
                put(r.getGroupName(), new RuleSet<>());
            }
            get(r.getGroupName()).add(r);
        });
    }

    @Override
    public _RuleSet<I,O> getRuleGroup(String groupName) {
        return get(groupName);
    }

    @Override
    public Set<String> getGroupNames() {
        return keySet();
    }

    @Override
    public int getNumberOfGroups() {
        return size();
    }


}
