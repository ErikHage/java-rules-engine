package com.tfr.rulesEngine.data;

public record Knowledge<O>(O value) {

    public String toAuditString() {
        return value.toString();
    }
}
