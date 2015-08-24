package ua.edu.sumdu.handlers.cell;

import ua.edu.sumdu.exceptions.CellException;

/**
 * Interface for handler different type cell.
 */
public interface TypeCellHandler {

    /**
     * Method execute handling cell different type.
     * @param sprSht - spreadsheet
     * @param i - row of cell.
     * @param j - column of cell.
     * @return result of handling.
     * @throws CellException - when incorrect cell occur.
     */
    public String execute(String sprSht[][], int i, int j) throws CellException;
}
