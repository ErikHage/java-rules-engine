package com.tfr.rulesEngine.rule.link;

import com.tfr.rulesEngine.rule.simple.SimpleRule;

import java.util.function.Function;
import java.util.function.Predicate;

/**
 *
 * Created by Erik on 6/25/2017.
 */
public class LinkingRule<I,O> extends SimpleRule<I,O> {

    protected final String next;

    public LinkingRule(String name, int priority, Predicate<I> predicate, Function<I, O> function, String next) {
        super(name, priority, predicate, function);
        this.next = next;
    }

    public String getNext() {
        return next;
    }
}
