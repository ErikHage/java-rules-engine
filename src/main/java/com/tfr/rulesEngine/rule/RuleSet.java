package com.tfr.rulesEngine.rule;

import java.util.Set;

/**
 *
 * Created by Erik on 6/14/2017.
 */
public interface RuleSet<R extends Rule> extends Iterable<R> {

    String getName();
    Set<R> getRules();

    boolean add(R rule);
    boolean contains(R rule);
    boolean remove(R rule);

}
