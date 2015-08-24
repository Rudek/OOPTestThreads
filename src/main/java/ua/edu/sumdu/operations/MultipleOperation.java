package ua.edu.sumdu.operations;

import ua.edu.sumdu.Term;

/**
 * Multiple operation for term.
 */
public class MultipleOperation implements Operation {

    public Term execute(Term a, Term b) {
        return new Term(a.getIntValueSign()*b.getIntValue());
    }

}
