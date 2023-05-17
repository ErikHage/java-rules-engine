package com.tfr.rulesEngine.exception;

/**
 *
 */
public class DuplicateRuleException extends RuntimeException {

    public DuplicateRuleException(String s) {
        super(s);
    }

    public DuplicateRuleException(String message, Throwable cause) {
        super(message, cause);
    }
}
