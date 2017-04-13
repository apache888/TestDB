package com.training.app.exception;

/**
 * Create on 01.04.2017.
 * @author Roman Hayda
 *
 * Class describes Exception when there is not unique ID for database
 */
public class NotUniqueIdException extends DBException {

    public NotUniqueIdException(String message) {
        super(message);
    }

    public NotUniqueIdException(String message, Throwable cause) {
        super(message, cause);
    }
}
