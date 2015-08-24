package ua.edu.sumdu;

import ua.edu.sumdu.exceptions.CellException;
import ua.edu.sumdu.exceptions.ErrorCellException;
import ua.edu.sumdu.exceptions.InvalidCellReferenceException;
import ua.edu.sumdu.handlers.cell.TypeCellHandler;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Class handler of cell. This class define type of cell, create appropriate handler for cell and execute his.
 * CellHandler implements interface Callable and allow to create thread.
 */
public class CellHandler implements Callable<String> {
    private String sprSht[][];
    private int i;
    private int j;
    //private static int numberThread = 0;
    private static Map<String,String> mapCellHandlers = new HashMap<String,String>();

    static
    {
        mapCellHandlers.put("=", "ExpressionCellHandler");
        mapCellHandlers.put("'", "TextCellHandler");

    }

    /**
     * Constructor for class CellHandler.
     * @param sprSht - spreadsheet.
     * @param i - row of spreadsheet.
     * @param j - column of spreadsheet.
     */
    public CellHandler(String sprSht[][], int i, int j) {
        this.sprSht = sprSht;
        this.i = i;
        this.j = j;
    }

    /**
     * Method return cell handler in compliance with type of cell.
     * @param type - string of type cell. Depends from first letter of cell string.
     * @return object of appropriate cell handler.
     */
    public TypeCellHandler getCellHandler(String type) {
        try {
            if ( mapCellHandlers.get(type) == null ) return null;
            String name = new StringBuilder().append("ua.edu.sumdu.handlers.cell.")
                                             .append(mapCellHandlers.get(type)).toString();
            Class actionClass = Class.forName(name);
            return (TypeCellHandler) actionClass.newInstance();
        } catch (InstantiationException e) {
            System.out.println(e.getMessage());
            throw new InternalError();
        } catch (IllegalAccessException e) {
            System.out.println(e.getMessage());
            throw new InternalError();
        } catch (ClassCastException e) {
            System.out.println(e.getMessage());
            throw new InternalError();
        } catch (ClassNotFoundException e) {
            System.out.println(e.getMessage());
            throw new InternalError();
        }
    }

    /**
     * Method define type cell, create new handler and execute his.
     * @return return result of handling cell.
     * @throws Exception
     */
    @Override
    public String call() throws Exception {
        //System.out.println("Thread "+ (++numberThread) +" [" + i +"," + j + "] = " + sprSht[i][j]);
        synchronized (sprSht[i][j]) {
            //System.out.println("Thread "+ (++numberThread) +" [" + i +"," + j + "] = " + sprSht[i][j]);
            String firstSymb = sprSht[i][j].substring(0,1);
            if ("#".equals(firstSymb)) {
                throw new ErrorCellException( "#Error in cell " + (char)(j + (int)'A') + (i+1)+"." );
            }
            TypeCellHandler tch = getCellHandler(firstSymb);
            try {
                if ( tch != null ) {
                    tch.execute(sprSht,i,j);
                }

            } catch (CellException ex) {
                sprSht[i][j] = ex.getMessage();
                throw new ErrorCellException("#Error in cell " + (char)((int)'A'+j) + (i+1)+".");
            }
            //System.out.println("Thread "+ numberThread +" [" + i +"," + j + "] = " + sprSht[i][j]);
            return sprSht[i][j];
        }
    }
}
