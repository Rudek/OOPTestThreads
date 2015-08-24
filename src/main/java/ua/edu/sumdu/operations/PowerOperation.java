package ua.edu.sumdu.operations;

import ua.edu.sumdu.Term;

/**
 * Power operation for term of expression.
 */
public class PowerOperation implements Operation {

    public Term execute(Term a, Term b) {
        return new Term( (int)Math.pow(a.getIntValueSign(),b.getIntValue()) );
    }

}
