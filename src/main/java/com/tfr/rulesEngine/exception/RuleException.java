package com.tfr.rulesEngine.exception;

/**
 *
 */
public class RuleException extends RuntimeException {

    public RuleException(String s) {
        super(s);
    }

    public RuleException(String message, Throwable cause) {
        super(message, cause);
    }
}
