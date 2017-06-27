package com.tfr.rulesEngine.rule.link;

import com.tfr.rulesEngine.rule.RuleSet;

import java.util.Set;

/**
 *
 * Created by Erik Hage on 6/17/2017.
 */
public class LinkingRuleSet<I,O> extends RuleSet<I,O, LinkingRule> {

    public LinkingRuleSet(String name) {
        super(name);
    }

    public LinkingRuleSet(String name, Set<LinkingRule> rules) {
        super(name, rules);
    }

}
