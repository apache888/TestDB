package com.training.app.exception;

/**
 * Create by Roman Hayda on 29.03.2017.
 */
public class NotUniqueNameException extends DBException {

    public NotUniqueNameException(String message) {
        super(message);
    }

    public NotUniqueNameException(String message, Throwable cause) {
        super(message, cause);
    }
}
