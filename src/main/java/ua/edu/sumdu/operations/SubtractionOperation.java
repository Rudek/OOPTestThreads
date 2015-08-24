package ua.edu.sumdu.operations;

import ua.edu.sumdu.Term;

/**
 * Subtraction operation for term of expression.
 */
public class SubtractionOperation implements Operation {

    public Term execute(Term a, Term b) {
        return new Term(a.getIntValueSign()-b.getIntValue());
    }

}
