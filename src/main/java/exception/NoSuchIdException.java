package exception;

/**
 * Create by Roman Hayda on 01.04.2017.
 */
public class NoSuchIdException extends DBException {

    public NoSuchIdException(String message) {
        super(message);
    }

    public NoSuchIdException(String message, Throwable cause) {
        super(message, cause);
    }
}
