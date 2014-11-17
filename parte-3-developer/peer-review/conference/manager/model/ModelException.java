package conference.manager.model;

public class ModelException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -669115984616341850L;

	/**
	 * Creates a new instance of BusinessException.
	 */
	public ModelException() {
	}

	/**
	 * Create a new instance of BusinessException.
	 * 
	 * @param message
	 *            the message to be shown.
	 */
	public ModelException(String message) {
		super(message);
	}

}
