package com.bookbecho.infrastructure.core.exception;


public class PlatformRequestBodyItemLimitValidationException extends RuntimeException {

    public PlatformRequestBodyItemLimitValidationException(String message) {
        super(message);
    }
}
