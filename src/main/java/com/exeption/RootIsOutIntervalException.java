package com.exeption;

public class RootIsOutIntervalException extends RuntimeException {

    public RootIsOutIntervalException() {
    }

    public RootIsOutIntervalException(String message) {
        super(message);
    }

    public RootIsOutIntervalException(String message, Throwable cause) {
        super(message, cause);
    }

    public RootIsOutIntervalException(Throwable cause) {
        super(cause);
    }

    public RootIsOutIntervalException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}