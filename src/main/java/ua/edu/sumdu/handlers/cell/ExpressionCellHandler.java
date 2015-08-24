package ua.edu.sumdu.handlers.cell;

import ua.edu.sumdu.Config;
import ua.edu.sumdu.Term;
import ua.edu.sumdu.exceptions.CellException;
import ua.edu.sumdu.exceptions.InvalidExpressionCellException;
import ua.edu.sumdu.operations.Operation;
import ua.edu.sumdu.operations.OperationFactory;

import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Class handler for expression cell.
 */
public class ExpressionCellHandler implements TypeCellHandler {

    public String execute(String sprSht[][], int i, int j) throws CellException{

        Pattern pTerm = Pattern.compile(Config.OPERATIONS + "?" + Config.TERM);
        Matcher mTerm = pTerm.matcher(sprSht[i][j]);

        List<Term> lTerm = new LinkedList<Term>();
        while(mTerm.find()) {
            lTerm.add( new Term(mTerm.group(),sprSht) );
        }

        if (lTerm.size() == 0) {
            throw new InvalidExpressionCellException("#Invalid expression in cell.");
        }

        int t = 1;
        while ( t < lTerm.size() ) {

            Operation operation = new OperationFactory().getOperation( lTerm.get(t).getOperation() );
            lTerm.set(0, operation.execute(lTerm.get(t-1), lTerm.get(t)));
            lTerm.remove(1);
        }

        sprSht[i][j] = Integer.toString( lTerm.get(0).getIntValueSign());

        return sprSht[i][j];
    }
}
