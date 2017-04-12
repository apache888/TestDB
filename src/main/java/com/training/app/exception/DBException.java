package com.training.app.exception;

/**
 * Create by Roman Hayda on 29.03.2017.
 */
public class DBException extends Exception {
    public DBException(String message) {
        super(message);
    }

    public DBException(String message, Throwable cause) {
        super(message, cause);
    }
}
