package com.training.app.exception;

/**
 * Create on 01.04.2017.
 * @author Roman Hayda
 *
 * Class describes Exception when there is no such ID in database
 */
public class NoSuchIdException extends DBException {

    public NoSuchIdException(String message) {
        super(message);
    }

    public NoSuchIdException(String message, Throwable cause) {
        super(message, cause);
    }
}
