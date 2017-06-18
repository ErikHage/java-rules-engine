package com.tfr.rulesEngine.exception;

/**
 *
 * Created by Erik Hage on 6/17/2017.
 */
public class RuleException extends RuntimeException {

    public RuleException(String s) {
        super(s);
    }

    public RuleException(String message, Throwable cause) {
        super(message, cause);
    }
}
