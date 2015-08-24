package ua.edu.sumdu.exceptions;

/**
 *
 */
public class CellException extends Exception {

    public CellException() {
        super();
    }

    public CellException(String message) {
        super(message);
    }

    public CellException(Throwable cause) {
        super(cause);
    }

    public CellException(String message, Throwable cause) {
        super(message, cause);
    }
}
