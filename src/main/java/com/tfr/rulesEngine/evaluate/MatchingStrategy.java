package com.tfr.rulesEngine.evaluate;

public enum MatchingStrategy {
    /**
     * Halt at the first match in a group, chaining to the next group if set
     */
    MATCH_FIRST,

    /**
     * Apply all the rules in a group, chaining to all next groups if present
     */
    MATCH_ALL;
}
