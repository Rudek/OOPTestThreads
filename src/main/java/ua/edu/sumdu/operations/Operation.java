package ua.edu.sumdu.operations;

import ua.edu.sumdu.Term;

/**
 * Operation interface.
 */
public interface Operation {

    /**
     * Execute operation.
     * @param a - first operand
     * @param b - second operand
     * @return
     */
    Term execute(Term a, Term b);

}
