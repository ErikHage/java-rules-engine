package com.tfr.rulesEngine.rule.simple;

import com.tfr.rulesEngine.rule.Rule;

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

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getPriority() {
        return priority;
    }

    @Override
    public Predicate<I> getPredicate() {
        return predicate;
    }

    @Override
    public Function<I,O> getFunction() {
        return function;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SimpleRule<?, ?> that = (SimpleRule<?, ?>) o;

        return name.equals(that.name);
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }

    @Override
    public int compareTo(Rule<I, O> other) {
        return other.getPriority() - this.priority;
    }

    @Override
    public String toString() {
        return "SimpleRule{" +
                "name='" + name + '\'' +
                ", priority=" + priority +
                '}';
    }

}
