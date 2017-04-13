package com.training.app.exception;
/**
 * Create on 29.03.2017.
 * @author Roman Hayda
 *
 * Class describes common Exception for this application
 */
public class DBException extends Exception {
    public DBException(String message) {
        super(message);
    }

    public DBException(String message, Throwable cause) {
        super(message, cause);
    }
}
