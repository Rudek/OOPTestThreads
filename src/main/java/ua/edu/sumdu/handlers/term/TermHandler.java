package ua.edu.sumdu.handlers.term;

import ua.edu.sumdu.exceptions.CellException;

/**
 * Interface for different term type.
 */
public interface TermHandler {
    int getIntValue(String termValue,String sprSht[][]) throws CellException;
}
