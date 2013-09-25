package ch.unil.sflight.java.exception;

import ch.unil.sflight.java.jra.Bapiret2;

public class BapiException extends Exception {

	private static final long serialVersionUID = 1L;

	private Bapiret2[] bapiErrors;
	
	public Bapiret2[] getBapiErrors() {
		return bapiErrors;
	}
	
	public BapiException(String message, Throwable t) {
		super(message, t);
		// TODO Auto-generated constructor stub
	}

	public BapiException(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public BapiException(String message, Bapiret2[] bapiErrors) {
		super(message);
		this.bapiErrors = bapiErrors;
	}
	
}
