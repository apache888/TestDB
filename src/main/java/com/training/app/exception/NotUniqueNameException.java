package com.training.app.exception;

/**
 * Create on 01.04.2017.
 * @author Roman Hayda
 *
 * Class describes Exception when there is not unique Name for database
 */
public class NotUniqueNameException extends DBException {

    public NotUniqueNameException(String message) {
        super(message);
    }

    public NotUniqueNameException(String message, Throwable cause) {
        super(message, cause);
    }
}
