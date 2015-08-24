package ua.edu.sumdu.handlers.term;

import ua.edu.sumdu.CellHandler;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import ua.edu.sumdu.Config;
/**
 * Handler for number type term.
 */
public class NumberHandler implements TermHandler {


    public int getIntValue(String termValue, String sprSht[][]) {

        Pattern pCellRef = Pattern.compile(Config.DIGIT);
        Matcher mCellRef = pCellRef.matcher(termValue);
        mCellRef.find();

        return Integer.parseInt(mCellRef.group());

    }
}
