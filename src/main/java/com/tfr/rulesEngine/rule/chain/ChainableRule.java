package com.tfr.rulesEngine.rule.chain;

import com.tfr.rulesEngine.rule.simple.SimpleRule;

import java.util.function.Function;
import java.util.function.Predicate;

/**
 *
 * Created by Erik Hage on 6/16/2017.
 */
public class ChainableRule<I,O> extends SimpleRule<I,O> implements Chainable<I,O> {

    private String next;

    public ChainableRule(String name, Predicate<I> predicate, Function<I, O> function, String next) {
        super(name, predicate, function);
        this.next = next;
    }

    public ChainableRule(String name, int priority, Predicate<I> predicate, Function<I, O> function, String next) {
        super(name, priority, predicate, function);
        this.next = next;
    }

    @Override
    public String next() {
        return this.next;
    }
}
