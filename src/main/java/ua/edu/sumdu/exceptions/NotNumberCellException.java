package ua.edu.sumdu.exceptions;

/**
 *
 */
public class NotNumberCellException extends CellException {

    public NotNumberCellException() {
        super();
    }

    public NotNumberCellException(String message) {
        super(message);
    }

    public NotNumberCellException(Throwable cause) {
        super(cause);
    }

    public NotNumberCellException(String message, Throwable cause) {
        super(message, cause);
    }
}

