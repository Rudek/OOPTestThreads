package ua.edu.sumdu.operations;

import ua.edu.sumdu.Term;

/**
 * Division Operation.
 */
public class DivisionOperation implements Operation {

    public Term execute(Term a, Term b) {
        return new Term(a.getIntValueSign()/b.getIntValue());
    }

}
