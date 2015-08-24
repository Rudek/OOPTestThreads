package ua.edu.sumdu.exceptions;

/**
 *
 */
public class ErrorCellException extends CellException {

    public ErrorCellException() {
        super();
    }

    public ErrorCellException(String message) {
        super(message);
    }

    public ErrorCellException(Throwable cause) {
        super(cause);
    }

    public ErrorCellException(String message, Throwable cause) {
        super(message, cause);
    }
}