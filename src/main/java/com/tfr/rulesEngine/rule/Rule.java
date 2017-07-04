package com.tfr.rulesEngine.rule;

import java.util.Optional;
import java.util.function.Function;
import java.util.function.Predicate;

import static com.tfr.rulesEngine.config.Constants.*;

/**
 *
 * Created by Erik on 6/14/2017.
 */
public class Rule<I,O> implements _Rule<I,O> {

    protected final String name;
    protected final String group;
    protected final int priority;
    protected final Predicate<I> predicate;
    protected final Function<I,Optional<O>> function;
    protected final String nextGroup;

    public Rule(String name, String group, int priority, Predicate<I> predicate, Function<I,Optional<O>> function, String nextGroup) {
        this.name = name;
        this.group = group;
        this.priority = priority;
        this.predicate = predicate;
        this.function = function;
        this.nextGroup = nextGroup;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getGroup() {
        return group;
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
    public Function<I,Optional<O>> getFunction() {
        return function;
    }

    @Override
    public String getNextGroup() {
        return nextGroup;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Rule<?, ?> that = (Rule<?, ?>) o;

        return name.equals(that.name);
    }

    @Override
    public int hashCode() {
        return name.hashCode();
    }

    @Override
    public int compareTo(_Rule<I, O> other) {
        int priorityCompare = other.getPriority() - this.priority;
        if(priorityCompare == 0) {
            return name.compareTo(other.getName());
        }
        return priorityCompare;

    }

    @Override
    public String toString() {
        return "Rule{" +
                "name='" + name + '\'' +
                ", group='" + group + '\'' +
                ", priority=" + priority +
                ", nextGroup='" + nextGroup + '\'' +
                '}';
    }

    public static class RuleBuilder<I,O> {

        private String name;
        private String group;
        private int priority;
        private Predicate<I> predicate;
        private Function<I,Optional<O>> function;
        private String nextGroup;

        public RuleBuilder(String name, Predicate<I> predicate, Function<I,Optional<O>> function) {
            this.name = name;
            this.group = DEFAULT_GROUP;
            this.priority = DEFAULT_PRIORITY;
            this.predicate = predicate;
            this.function = function;
            this.nextGroup = TERMINAL_GROUP;
        }

        public RuleBuilder<I,O> group(String group) {
            this.group = group;
            return this;
        }

        public RuleBuilder<I,O> priority(int priority) {
            this.priority = priority;
            return this;
        }

        public RuleBuilder<I,O> nextGroup(String nextGroup) {
            this.nextGroup = nextGroup;
            return this;
        }

        public Rule<I,O> build() {
            return new Rule<>(name, group, priority, predicate, function, nextGroup);
        }
    }

}
