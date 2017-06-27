package com.tfr.rulesEngine.exception;

import com.tfr.rulesEngine.rule.Rule;

/**
 *
 * Created by Erik on 6/26/2017.
 */
public class DuplicateRuleException extends RuleException {

    public DuplicateRuleException(Rule rule) {
        super("Rule already exists with name: " + rule.getName());
    }

}
