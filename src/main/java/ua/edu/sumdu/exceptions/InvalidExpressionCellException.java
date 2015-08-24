package ua.edu.sumdu.exceptions;

/**
 *
 */
public class InvalidExpressionCellException extends CellException {

    public InvalidExpressionCellException() {
        super();
    }

    public InvalidExpressionCellException(String message) {
        super(message);
    }

    public InvalidExpressionCellException(Throwable cause) {
        super(cause);
    }

    public InvalidExpressionCellException(String message, Throwable cause) {
        super(message, cause);
    }
}
