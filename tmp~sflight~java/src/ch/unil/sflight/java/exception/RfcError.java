package ch.unil.sflight.java.exception;

public class RfcError extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public RfcError() {
		// TODO Auto-generated constructor stub
	}

	public RfcError(String message) {
		super(message);
		// TODO Auto-generated constructor stub
	}

	public RfcError(Throwable t) {
		super(t);
		// TODO Auto-generated constructor stub
	}

	public RfcError(String messge, Throwable t) {
		super(messge, t);
		// TODO Auto-generated constructor stub
	}

}
