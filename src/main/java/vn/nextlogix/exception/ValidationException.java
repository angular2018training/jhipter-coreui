/**
 * 
 */
package vn.nextlogix.exception;

/**
 * @author NHAT LINH
 * 
 */
public class ValidationException extends BusinessException {

	/**
     * 
     */
	private static final long serialVersionUID = -7233411170234987822L;

	/**
	 * Constructor
	 * 
	 */
	public ValidationException() {
		super();
	}

	/**
	 * Constructor
	 * 
	 * @param message
	 * @param cause
	 */
	public ValidationException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * Constructor
	 * 
	 * @param message
	 */
	public ValidationException(String message) {
		super(message);
	}

	/**
	 * Constructor
	 * 
	 * @param cause
	 */
	public ValidationException(Throwable cause) {
		super(cause);
	}

}
