package ua.edu.sumdu.exceptions;

/**
 *
 */
public class InvalidCellReferenceException extends CellException {

    public InvalidCellReferenceException() {
        super();
    }

    public InvalidCellReferenceException(String message) {
        super(message);
    }

    public InvalidCellReferenceException(Throwable cause) {
        super(cause);
    }

    public InvalidCellReferenceException(String message, Throwable cause) {
        super(message, cause);
    }
}
