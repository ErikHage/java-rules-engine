package com.tfr.rulesEngine.rule;

import java.util.HashMap;
import java.util.Set;

/**
 *
 * Created by Erik on 7/3/2017.
 */
public class RuleMap<I,O> extends HashMap<String, RuleSet<I,O>> implements _RuleMap<I,O> {

    public RuleMap(_RuleSet<I,O> ruleSet) {
        ruleSet.stream().forEach(r -> {
            if(!containsKey(r.getGroup())) {
                put(r.getGroup(), new RuleSet<>());
            }
            get(r.getGroup()).add(r);
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
