package com.tfr.rulesEngine.rule.chain;

import com.tfr.rulesEngine.rule.simple.SimpleRule;

import java.util.function.Function;
import java.util.function.Predicate;

/**
 *
 * Created by Erik Hage on 6/16/2017.
 */
public class ChainingRule<I,O> extends SimpleRule<I,O> {

    private String next;

    public ChainingRule(String name, Predicate<I> predicate, Function<I, O> function, String next) {
        super(name, predicate, function);
        this.next = next;
    }

    public ChainingRule(String name, int priority, Predicate<I> predicate, Function<I, O> function, String next) {
        super(name, priority, predicate, function);
        this.next = next;
    }

    public String next() {
        return this.next;
    }
}
