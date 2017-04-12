package com.training.app.exception;

/**
 * Create by Roman Hayda on 30.03.2017.
 */
public class NotUniqueIdException extends DBException {

    public NotUniqueIdException(String message) {
        super(message);
    }

    public NotUniqueIdException(String message, Throwable cause) {
        super(message, cause);
    }
}
