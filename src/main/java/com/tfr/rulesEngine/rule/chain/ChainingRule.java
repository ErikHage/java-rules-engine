package com.tfr.rulesEngine.rule.chain;

import com.tfr.rulesEngine.rule.link.LinkingRule;

import java.util.function.Function;
import java.util.function.Predicate;

/**
 *
 * Created by Erik Hage on 6/16/2017.
 */
public class ChainingRule<I> extends LinkingRule<I,I> {

    public ChainingRule(String name, int priority, Predicate<I> predicate, Function<I,I> function, String next) {
        super(name, priority, predicate, function, next);
    }

}
