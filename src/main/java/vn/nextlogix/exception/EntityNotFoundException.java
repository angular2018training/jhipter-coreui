package vn.nextlogix.exception;

public class EntityNotFoundException extends BusinessException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5574590940011415420L;

	public EntityNotFoundException() {
		super();
	}

	public EntityNotFoundException(String message) {
		super(message);
	}
}
