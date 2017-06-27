package com.tfr.rulesEngine.rule.link;

import com.tfr.rulesEngine.rule.RuleSet;
import com.tfr.rulesEngine.rule.RuleSetBuilder;

/**
 *
 * Created by Erik on 6/25/2017.
 */
public class LinkingRuleSetBuilder<I,O> extends RuleSetBuilder<I,O, LinkingRule> {

    private String name;

    public LinkingRuleSetBuilder(String name) {
        super(name);
    }

    public RuleSet<I,O, LinkingRule> build() {
        return new LinkingRuleSet<>(name, rules);
    }

}
