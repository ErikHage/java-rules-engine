package com.tfr.rulesEngine.rule;

import com.tfr.rulesEngine.rule.simple.SimpleRule;

import java.util.function.Function;
import java.util.function.Predicate;

/**
 *
 * Created by Erik on 6/25/2017.
 */
public class RuleFactory {

    public static <I,O> Rule<I,O> getRule(String name, Predicate<I> predicate, Function<I,O> function) {
        return new SimpleRule<>(name,0, predicate, function);
    }

    public static <I,O> Rule<I,O> getRule(String name, int priority, Predicate<I> predicate, Function<I,O> function) {
        return new SimpleRule<>(name, priority, predicate, function);
    }

    public static <I,O> Rule<I,O> getRule(String name, int priority, Predicate<I> predicate, Function<I,O> function, String next) {
        return new SimpleRule<>(name, priority, predicate, function, next);
    }

}
