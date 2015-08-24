package ua.edu.sumdu.handlers.term;

import ua.edu.sumdu.CellHandler;
import ua.edu.sumdu.Config;
import ua.edu.sumdu.exceptions.CellException;
import ua.edu.sumdu.exceptions.InvalidCellReferenceException;
import ua.edu.sumdu.exceptions.NotNumberCellException;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Handler for cell reference type term.
 */
public class CellReferenceHandler implements TermHandler {

    /**
     * Method return int value of cell by her reference.
     * @param termValue - cell reference
     * @param sprSht - spreadsheet.
     * @return int value of the cell.
     * @throws CellException when incorrect cell occur. For example incorrect cell reference.
     */
    public int getIntValue(String termValue, String sprSht[][]) throws CellException {

        Pattern pCellRef = Pattern.compile(Config.LETTER);
        Matcher mCellRef = pCellRef.matcher(termValue);
        mCellRef.find();
        int column = (int)mCellRef.group().charAt(0)-(int)'A';

        pCellRef = Pattern.compile(Config.DIGIT);
        mCellRef = pCellRef.matcher(termValue);
        mCellRef.find();
        int row = Integer.parseInt( mCellRef.group()) - 1;

        if ( row > sprSht.length-1 || column > sprSht[0].length-1) {
            throw new InvalidCellReferenceException("#Invalid cell reference \'" + termValue +"\'");
        }

        CellHandler cellHandler = new CellHandler(sprSht, row, column);
        FutureTask<String> task =  new FutureTask<String>(cellHandler);
        Thread cellThread = new Thread(task);
        cellThread.start();
        String result = null;
        try {
            //cellThread.join();
            result = task.get();
        } catch (InterruptedException ex) {
            throw new CellException("#Thread for cell " + termValue + " is interrupted.");
        } catch (ExecutionException ex) {
            ex.printStackTrace();
            throw new CellException(ex);
        }

        pCellRef = Pattern.compile("^\\-?"+Config.DIGIT+"$");
        mCellRef = pCellRef.matcher(result);
        if ( !mCellRef.find() ) {
            throw new NotNumberCellException("#Not number in cell " + termValue);
        }
        return Integer.parseInt(result);

    }
}
