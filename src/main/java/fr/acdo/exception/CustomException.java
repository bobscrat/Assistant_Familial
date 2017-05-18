package fr.acdo.exception;

public class CustomException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public CustomException(String msgError) {
		super(msgError);
	}

}
