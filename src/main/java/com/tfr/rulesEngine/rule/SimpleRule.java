package com.tfr.rulesEngine.rule;

import java.util.function.Function;
import java.util.function.Predicate;

/**
 *
 * Created by Erik on 6/14/2017.
 */
public class SimpleRule<I,O> implements Rule<I,O> {

    protected final String name;
    protected final int priority;
    protected final Predicate<I> predicate;
    protected final Function<I,O> function;

    public SimpleRule(String name, Predicate<I> predicate, Function<I, O> function) {
        this(name, 0, predicate, function);
    }

    public SimpleRule(String name, int priority, Predicate<I> predicate, Function<I, O> function) {
        this.name = name;
        this.priority = priority;
        this.predicate = predicate;
        this.function = function;
    }

    public boolean evaluate(I input) {
        return predicate.test(input);
    }

    public O getResult(I input) {
        return function.apply(input);
    }

    public String getName() {
        return name;
    }

    public int getPriority() {
        return priority;
    }

    public Predicate<I> getPredicate() {
        return predicate;
    }

    public Function<I,O> getFunction() {
        return function;
    }

    @Override
    public String toString() {
        return "SimpleRule{" +
                "name='" + name + '\'' +
                ", priority=" + priority +
                '}';
    }
}
