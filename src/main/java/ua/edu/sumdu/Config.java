package ua.edu.sumdu;

/**
 * Class of configs app.
 */
public class Config {
    /**
     * Regexp of term.
     */
    public static final String TERM = "([A-Z]{1}[0-9]+|[0-9]+)";
    /**
     * Regexp of letter in cell reference.
     */
    public static final String LETTER = "[A-Z]{1}";
    /**
     * Regexp of digit in cell reference.
     */
    public static final String DIGIT = "[0-9]+";
    /**
     * Regexp of possible operations in expression.
     */
    public static final String OPERATIONS = "[*|/|+|\\-|^]";
}
