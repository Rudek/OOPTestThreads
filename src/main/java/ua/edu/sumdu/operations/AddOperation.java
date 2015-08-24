package ua.edu.sumdu.operations;

import ua.edu.sumdu.Term;

/**
 * Class for add operation.
 */
public class AddOperation implements Operation {

    public Term execute(Term a, Term b) {
        return new Term(a.getIntValueSign()+b.getIntValue());
    }

}
