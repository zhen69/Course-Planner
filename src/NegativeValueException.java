/**
 * The NegativeValueException class inherits all properties and behaviors of the Exception class.
 *
 *
 * @author Zhen Wei Liao
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
     * 		Message to display when this exception is thrown
     */
    public NegativeValueException(String error) {
        super(error);
    }
}

