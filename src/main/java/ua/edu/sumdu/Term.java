package ua.edu.sumdu;

import ua.edu.sumdu.exceptions.CellException;
import ua.edu.sumdu.handlers.term.TermHandler;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Class define term of expression.
 */
public class Term {
    private String operation;
    private int intValue;


    /**
     * Constructor of class Term.
     * @param intValue - integer value of term.
     */
    public Term (int intValue ) {


        this.intValue =  intValue;
        this.operation = "+";

        if (intValue < 0) {
            this.intValue = -1*this.intValue;
            this.operation = "-";
        }
    }

    /**
     * Constructor of class Term.
     * @param t - String value of term.
     * @param sprSht - spreadsheet
     * @throws CellException - when error occur in cell.
     */
    public Term( String t, String sprSht[][] ) throws CellException {
        String term = t;
        operation = "+";
        Pattern pTerm = Pattern.compile(Config.OPERATIONS + "{1}");
        Matcher mTerm = pTerm.matcher(t);
        if (mTerm.find())  {
            term = t.substring(mTerm.end());
            operation = mTerm.group();
        }
        TermHandlerFactory thf = new TermHandlerFactory();
        TermHandler th = thf.handler(term);
        intValue = th.getIntValue(t,sprSht);

    }

    /**
     * Return string value of operation.
     * @return operation of term.
     */
    public String getOperation() {
        return operation;
    }

    /**
     * Return integer value without sign.
     * @return integer value of term.
     */
    public int getIntValue () {
        return intValue;
    }


    /**
     * Return integer value of term with sign.
     * @return integer value with + or - sign.
     */
    public  int getIntValueSign() {
        return "-".equals(operation) ? -1*intValue : intValue;
    }

}
