package com.tfr.rulesEngine.rule;

import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

import static com.tfr.rulesEngine.config.Constants.*;

/**
 *
 */
public class Rule<I,O> implements _Rule<I,O> {

    protected final String name;
    protected final String groupName;
    protected final int priority;
    protected final Predicate<I> matchCondition;
    protected final Function<I,Optional<O>> onMatchHandler;
    protected final String nextGroupName;

    public Rule(String name, String groupName, int priority, Predicate<I> matchCondition, Function<I,Optional<O>> onMatchHandler, String nextGroupName) {
        this.name = name;
        this.groupName = groupName;
        this.priority = priority;
        this.matchCondition = matchCondition;
        this.onMatchHandler = onMatchHandler;
        this.nextGroupName = nextGroupName;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getGroupName() {
        return groupName;
    }

    @Override
    public int getPriority() {
        return priority;
    }

    @Override
    public Predicate<I> getMatchCondition() {
        return matchCondition;
    }

    @Override
    public Function<I,Optional<O>> getOnMatchHandler() {
        return onMatchHandler;
    }

    @Override
    public String getNextGroupName() {
        return nextGroupName;
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
        return String.format("Rule [name=%s, groupName=%s, priority=%s, nextGroupName=%s]",
                name, groupName, priority, nextGroupName);
    }

    public static class RuleBuilder<I,O> {

        private final String name;
        private final Predicate<I> matchCondition;
        private String groupName;
        private int priority;
        private Function<I,Optional<O>> onMatchHandler;
        private String nextGroupName;

        public RuleBuilder(String name, Predicate<I> matchCondition) {
            this.name = name;
            this.groupName = DEFAULT_GROUP;
            this.priority = DEFAULT_PRIORITY;
            this.matchCondition = matchCondition;
            this.nextGroupName = TERMINAL_GROUP;
            this.onMatchHandler = (I i) -> Optional.empty();
        }

        public RuleBuilder<I,O> onMatchHandler(Function<I,O> onMatchHandler) {
            this.onMatchHandler = (I i) ->
                    Optional.ofNullable(onMatchHandler.apply(i));
            return this;
        }

        public RuleBuilder<I,O> onMatchHandler(Consumer<I> onMatchHandler) {
            this.onMatchHandler = (I i) -> {
                onMatchHandler.accept(i);
                return Optional.empty();
            };
            return this;
        }

        public RuleBuilder<I,O> groupName(String group) {
            this.groupName = group;
            return this;
        }

        public RuleBuilder<I,O> priority(int priority) {
            this.priority = priority;
            return this;
        }

        public RuleBuilder<I,O> nextGroupName(String nextGroupName) {
            this.nextGroupName = nextGroupName;
            return this;
        }

        public Rule<I,O> build() {
            return new Rule<>(name, groupName, priority, matchCondition, onMatchHandler, nextGroupName);
        }
    }

}
