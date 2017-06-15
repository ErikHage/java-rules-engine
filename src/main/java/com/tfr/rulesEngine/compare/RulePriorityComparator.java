package com.tfr.rulesEngine.compare;

import com.tfr.rulesEngine.rule.Rule;

import java.util.Comparator;

/**
 *
 * Created by Erik on 6/14/2017.
 */
public class RulePriorityComparator implements Comparator<Rule> {

    @Override
    public int compare(Rule rule1, Rule rule2) {
        return rule1.getPriority() - rule2.getPriority();
    }
}
