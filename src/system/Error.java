package system;

/**
 * This class represents our game exceptions
 * @author Strift
 *
 */
@SuppressWarnings("serial")
public class Error extends Exception {

	/**
	 * Constructor
	 * @param content : the description of the error
	 */
	public Error(String content) {
		super(content);
	}
}
