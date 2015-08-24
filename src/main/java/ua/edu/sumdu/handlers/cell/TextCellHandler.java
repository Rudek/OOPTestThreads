package ua.edu.sumdu.handlers.cell;

/**
 * Class handler for string cell.
 */
public class TextCellHandler implements TypeCellHandler {

    public String execute(String sprSht[][], int i, int j)  {
        sprSht[i][j] = sprSht[i][j].substring(1);
        return sprSht[i][j];
    }
}
