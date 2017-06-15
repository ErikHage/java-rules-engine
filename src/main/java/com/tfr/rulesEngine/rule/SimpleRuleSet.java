package com.tfr.rulesEngine.rule;

import com.tfr.rulesEngine.compare.RulePriorityComparator;

import java.util.Set;
import java.util.TreeSet;

/**
 *
 * Created by Erik on 6/14/2017.
 */
public class SimpleRuleSet<I,O> {

    private final String setName;
    private final Set<Rule<I,O>> rules;

    public SimpleRuleSet(String setName) {
        this.setName = setName;
        this.rules = new TreeSet<>(new RulePriorityComparator());
    }

    public String getSetName() {
        return setName;
    }

    public Set<Rule<I, O>> getRules() {
        return rules;
    }

    public boolean addRule(Rule<I,O> rule) {
        if(rules.contains(rule)) {
            return false;
        }
        rules.add(rule);
        return true;
    }

}
