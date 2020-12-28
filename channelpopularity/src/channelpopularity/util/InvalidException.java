package channelpopularity.util;

/**
 * Created user defined exception for Validation
 */

public class InvalidException extends Exception {
    public InvalidException(String sentence) {
        super(sentence);
    }

    /**
     * toString() method for debugging purposes
     * @return class name of return type String
     */
    public String toString()
    {
        return "INVALID EXCEPTION CLASS" + "\n" + getClass().getName() + "\n" ;
    }
}

