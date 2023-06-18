/**
 * The NegativeValueException class inherit all properties and behaviors from the Exception class.
 *
 *
 * @author Zhen Wei Liao
 * 		Date: 9/6/22
 **/
public class NegativeValueException extends Exception{
    /**
     * Constructor method calls the constructor of the Exception class
     */
    public NegativeValueException() {
        super();
    }

    /**
     * Constructor calls the constructor of the Exception class and changes the display error message
     * to the given error variable.
     *
     * @param error
     * 		message to display when this exception is throw
     */
    public NegativeValueException(String error) {
        super(error);
    }
}

